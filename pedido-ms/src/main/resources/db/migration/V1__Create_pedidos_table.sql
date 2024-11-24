CREATE SEQUENCE pedidos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;
CREATE TABLE pedidos
(
    id               BIGINT DEFAULT nextval('pedidos_id_seq'),
    uuid             UUID           NOT NULL,
    cliente_uuid     UUID         NOT NULL,
    status           VARCHAR(255)   NOT NULL,
    valor_total      NUMERIC(19, 2) NOT NULL,
    data_criacao     TIMESTAMP      NOT NULL,
    data_atualizacao TIMESTAMP NULL,
    CONSTRAINT pk_pedidos PRIMARY KEY (id),
    CONSTRAINT uk_pedidos_uuid UNIQUE (uuid)
);