package com.example.auth.application.service;

import com.example.auth.application.data.Tokens;
import com.example.auth.application.repository.AccountRepository;
import com.example.auth.application.repository.PasswordHistoryRepository;
import com.example.auth.application.repository.ProfileClientRepository;
import com.example.auth.application.repository.RefreshTokenRepository;
import com.example.auth.application.repository.SaltRepository;
import com.example.auth.application.usecase.ChangePwUseCase;
import com.example.auth.application.usecase.SignInUseCase;
import com.example.auth.application.usecase.SignUpUseCase;
import com.example.auth.domain.Account;
import com.example.auth.domain.Salt;
import com.example.auth.domain.types.AccountStatus;
import com.example.auth.exception.AuthenticationErrorCode;
import com.example.auth.readmodels.AccountReadModels.AccountAuthenticationReadModel;
import com.example.core.random.service.DefaultSecuredRandom;
import com.example.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public final class AuthenticationService implements SignUpUseCase, SignInUseCase, ChangePwUseCase {

    // repositories
    private final AccountRepository accountRepository;
    private final ProfileClientRepository profileClientRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordHistoryRepository passwordHistoryRepository;
    private final SaltRepository saltRepository;

    // services
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final DefaultSecuredRandom securedRandom;


    @Override
    public Account signUp(Account account) {
        // Check account
        log.debug("account: {}",account);
        if (accountRepository.existsByUsername(account.username)) {
            throw AuthenticationErrorCode.USER_CONFLICT.defaultException();
        }
        // Create and save new account
        Account newAccount = accountRepository.save(
                Account.builder()
                        .username(account.username)
                        .password(passwordEncoder.encode(account.password))
                        .status(account.status)
                        .build()
        );
        // Create salt
//        String salt = BCrypt.gensalt(); // Bcrypt 버전
        String salt = Base64.getEncoder().encodeToString(securedRandom.generate(22).getBytes()); // PasswordEncoder 버전

        // Save salt
        saltRepository.save(newAccount.getId(), salt);

        // Encode Password
//        String encodedPassword = BCrypt.hashpw(account.password, salt); // Bcrypt 버전
        String encodedPassword = passwordEncoder.encode(salt + account.password); // PasswordEncoder 버전

        // Save Password History
        passwordHistoryRepository.save(newAccount.getId(), encodedPassword);

        // Feign Client Call
        profileClientRepository.createProfile(newAccount);
        return newAccount;
    }

    @Override
    public Tokens signIn(String username, String rawPassword) {
        AccountAuthenticationReadModel authenticationReadModel =
                accountRepository
                        .findAuthenticationByUsername(username)
                        .orElseThrow(AuthenticationErrorCode.USER_NULL::defaultException);

        AccountStatus status = authenticationReadModel.status();
        String dbPassword = authenticationReadModel.password();
        boolean isMatched;
        Instant now = Instant.now();

        if (!status.canSignIn()) {
            throw AuthenticationErrorCode.CANNOT_SIGN_IN.defaultException();
        }

        isMatched = passwordEncoder.matches(rawPassword, dbPassword);

        if (!isMatched) {
            throw AuthenticationErrorCode.ID_PW_MISMATCHED.defaultException();
        }

        // ... always one of ... <<< use method
        Map<String, Object> map = Map.of("authroty", "USER");
        String accessToken = jwtProvider.generateJwt(username, map, now);
        String refreshToken = securedRandom.generate(30);

        // save refresh token to redis
        refreshTokenRepository.save(refreshToken, username);

        return Tokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        // User check
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(AuthenticationErrorCode.USER_NULL::defaultException);

        // Get salt
        Salt salt = saltRepository.findByAccountId(account.getId())
                .orElseThrow(AuthenticationErrorCode.USER_NULL::defaultException);

        // Encode password
//        String encodedPassword = BCrypt.hashpw(newPassword, salt.salt); // Bcrypt 버전
        String encodedPassword = passwordEncoder.encode(salt + newPassword); // PasswordEncoder 버전

        // Check new password from history
        if (passwordHistoryRepository.existsByPassword(encodedPassword)) {
            log.debug("이전에 설정한 비밀번호로 설정할 수 없습니다.");
            return false;
        }
        if (passwordHistoryRepository.countAllByPassword(encodedPassword) > 10) {
            log.debug("이전에 설정한 비밀번호로 설정할 수 없습니다.");
            return false;
        }
        passwordHistoryRepository.save(account.getId(), encodedPassword);
        return true;
    }
}
