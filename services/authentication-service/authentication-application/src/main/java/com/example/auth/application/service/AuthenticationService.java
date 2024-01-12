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
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        log.debug("account: {}",account);
        if (accountRepository.findByUsername(account.username).isPresent()) {
            throw AuthenticationErrorCode.USER_CONFLICT.defaultException();
        }

        Account newAccount = accountRepository.save(
                Account.builder()
                        .username(account.username)
                        .password(passwordEncoder.encode(account.password))
                        .status(account.status)
                        .build()
        );

        String salt = BCrypt.gensalt();
        passwordHistoryRepository.save(newAccount.getId(), BCrypt.hashpw(account.password, salt));
        saltRepository.save(newAccount.getId(), salt);
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

        // TODO save refresh token to redis
        refreshTokenRepository.save(refreshToken, username);

        return Tokens.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(AuthenticationErrorCode.USER_NULL::defaultException);

        Salt salt = saltRepository.findByAccountId(account.getId())
                .orElseThrow(AuthenticationErrorCode.USER_NULL::defaultException);
        String encodedPassword = BCrypt.hashpw(newPassword, salt.salt);

        if (passwordHistoryRepository.findByPassword(encodedPassword).isEmpty()) {
            passwordHistoryRepository.save(account.getId(), encodedPassword);
            return true;
        }

        return false;
    }
}
