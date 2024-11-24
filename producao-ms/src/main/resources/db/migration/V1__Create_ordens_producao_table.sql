CREATE SEQUENCE ordens_producao_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;
CREATE TABLE ordens_producao
(
    id               BIGINT DEFAULT nextval('ordens_producao_id_seq'),
    id_externo       UUID         NOT NULL,
    pedido_id        UUID         NOT NULL,
    status           VARCHAR(255) NOT NULL,
    data_criacao     TIMESTAMP    NOT NULL,
    data_atualizacao TIMESTAMP NULL,
    CONSTRAINT ordens_producao_pk PRIMARY KEY (id),
    CONSTRAINT ordens_producao_uuid_uk UNIQUE (id_externo)
);