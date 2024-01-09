CREATE TABLE IF NOT EXISTS member_profile (
    id                      UUID                            DEFAULT uuid_generate_v4(),
    account_id              VARCHAR(255),
    username                VARCHAR(255),
    nickname                VARCHAR(255),
    gender                  VARCHAR(1) DEFAULT 'MALE',
    birth                   TIMESTAMP,
    status                  VARCHAR(255) DEFAULT 'PENDING',
    created_at              TIMESTAMP                       DEFAULT now(),
    updated_at              TIMESTAMP,

    CONSTRAINT pk_account PRIMARY KEY (id),
    CONSTRAINT uq_account_username UNIQUE (username),
    CONSTRAINT valid_status CHECK ( status IN ('PENDING', 'ACTIVE', 'PROTECTED', 'SUSPENDED', 'SLEPT', 'REMOVED') ),
    CONSTRAINT uq_account_nickname UNIQUE (nickname),
    CONSTRAINT valid_gender CHECK ( gender IN ('MALE', 'FEMALE') )
);