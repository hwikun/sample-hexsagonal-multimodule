CREATE TABLE IF NOT EXISTS password_history (
    id                      UUID                            DEFAULT uuid_generate_v4(),
    account_id              VARCHAR(255),
    password                VARCHAR(255),

    created_at              TIMESTAMP                       DEFAULT now(),
    updated_at              TIMESTAMP,

    CONSTRAINT pk_pw_history PRIMARY KEY (id),
    CONSTRAINT uq_pw_history_password UNIQUE (password)
);