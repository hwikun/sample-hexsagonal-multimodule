CREATE TABLE IF NOT EXISTS salt (
    id                      UUID                            DEFAULT uuid_generate_v4(),
    account_id              VARCHAR(255),
    salt                    VARCHAR(255),

    created_at              TIMESTAMP                       DEFAULT now(),
    updated_at              TIMESTAMP,

    CONSTRAINT pk_salt PRIMARY KEY (id),
    CONSTRAINT uq_salt_salt UNIQUE (salt)
);