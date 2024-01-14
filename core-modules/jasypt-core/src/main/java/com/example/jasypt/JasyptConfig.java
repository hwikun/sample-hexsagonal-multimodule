package com.example.jasypt;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"dev", "local"})
public class JasyptConfig {


    @Value("${jasypt.encryptor.key}")  // 환경변수 사용 시 주석 처리된 부분 이용
    private String password;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword(password); // 암호화키
        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setPoolSize("1"); // 인스턴스 pool

//        config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스
//        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
//        config.setStringOutputType("base64"); //인코딩 방식

        encryptor.setConfig(config);
        return encryptor;
    }
}

// 트러블 슈팅
// 문제가 의심되는 줄을 찾아 주석처리를 하며 어느 곳이 문제인지 알아내고, 에러문 기록하여 디버깅
