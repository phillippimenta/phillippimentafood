CREATE SEQUENCE clientes_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;
CREATE TABLE clientes
(
    id               BIGINT PRIMARY KEY DEFAULT nextval('clientes_id_seq'),
    id_externo       UUID         NOT NULL,
    cpf              VARCHAR(11) NULL,
    nome             VARCHAR(255) NOT NULL,
    email            VARCHAR(255) NULL,
    anonimo          BOOLEAN      NOT NULL,
    data_criacao     TIMESTAMP    NOT NULL,
    data_atualizacao TIMESTAMP NULL,
    CONSTRAINT clientes_id_externo_unique UNIQUE (id_externo),
    CONSTRAINT clientes_cpf_unique UNIQUE (cpf)
);
CREATE INDEX clientes_data_criacao_idx ON clientes (data_criacao);
CREATE INDEX clientes_cpf_idx ON clientes (cpf);