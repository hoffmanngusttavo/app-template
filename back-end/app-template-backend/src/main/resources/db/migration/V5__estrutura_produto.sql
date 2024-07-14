CREATE TABLE PRODUTO (
        id BIGSERIAL ,
        nome varchar(255) NOT NULL,
        ativo BOOLEAN NOT NULL DEFAULT TRUE,
        cod_categoria BIGINT NOT NULL,
        created_by varchar(255) NOT NULL,
        updated_by varchar(255) NOT NULL,
        created_at timestamp NOT NULL,
        updated_at timestamp NOT NULL,

        PRIMARY KEY(id),
        FOREIGN KEY (cod_categoria) REFERENCES CATEGORIA_PRODUTO(id)
);

CREATE INDEX idx_nome_produto ON PRODUTO(nome);
CREATE INDEX idx_cod_categoria_produto ON PRODUTO(cod_categoria);
