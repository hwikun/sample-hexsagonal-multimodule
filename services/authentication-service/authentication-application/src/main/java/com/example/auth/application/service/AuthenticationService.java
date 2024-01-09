package com.example.auth.application.service;

import com.example.auth.application.data.Tokens;
import com.example.auth.application.repository.AccountRepository;
import com.example.auth.application.repository.ProfileClientRepository;
import com.example.auth.application.repository.RefreshTokenRepository;
import com.example.auth.application.usecase.SignInUseCase;
import com.example.auth.application.usecase.SignUpUseCase;
import com.example.auth.domain.Account;
import com.example.auth.domain.types.AccountStatus;
import com.example.auth.exception.AuthenticationErrorCode;
import com.example.auth.readmodels.AccountReadModels.AccountAuthenticationReadModel;
import com.example.core.random.service.SecuredRandom;
import com.example.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public final class AuthenticationService implements SignUpUseCase, SignInUseCase {

    // repositories
    private final AccountRepository accountRepository;
    private final ProfileClientRepository profileClientRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    // services
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final SecuredRandom securedRandom;


    @Override
    public Account signUp(Account account) {
        log.debug(account.username);
        if (accountRepository.findByUsername(account.username).isPresent()) {
            throw AuthenticationErrorCode.USER_CONFLICT.defaultException();
        }
        Account newAccount = Account.builder()
                .id(account.getId())
                .username(account.username)
                .password(passwordEncoder.encode(account.password))
                .status(account.status)
                .build();
        Account authAccount = accountRepository.save(newAccount);
        profileClientRepository.createProfile(authAccount);
        return authAccount;
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
}
