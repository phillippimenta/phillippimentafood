CREATE SEQUENCE itens_pedido_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;
CREATE TABLE itens_pedido
(
    id         BIGINT DEFAULT nextval('itens_pedido_id_seq'),
    quantidade INTEGER        NOT NULL,
    descricao  VARCHAR(255)   NOT NULL,
    valor      NUMERIC(19, 2) NOT NULL,
    pedido_id  BIGINT         NOT NULL,
    CONSTRAINT pk_itens_pedido PRIMARY KEY (id),
    CONSTRAINT fk_itens_pedido_pedidos FOREIGN KEY (pedido_id) REFERENCES pedidos (id)
);
