CREATE SEQUENCE produtos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;
CREATE TABLE produtos
(
    id               BIGINT DEFAULT nextval('produtos_id_seq'),
    id_externo       UUID           NOT NULL,
    categoria        VARCHAR(50)    NOT NULL,
    nome             VARCHAR(255)   NOT NULL,
    descricao        TEXT,
    preco            DECIMAL(10, 2) NOT NULL,
    data_criacao     TIMESTAMP      NOT NULL,
    data_atualizacao TIMESTAMP NULL,
    CONSTRAINT produtos_id_externo_uk UNIQUE (id_externo),
    CONSTRAINT produtos_pk PRIMARY KEY (id)
);
CREATE INDEX produtos_data_criacao_idx ON produtos (data_criacao);