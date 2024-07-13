CREATE TABLE PRODUTO (
        id BIGSERIAL ,
        nome varchar(255) NOT NULL,
        ativo BOOLEAN NOT NULL DEFAULT TRUE,
        cod_categoria BIGINT NOT NULL,
        created_by varchar(255) NOT NULL,
        updated_by varchar(255) NOT NULL,
        created_at timestamp NOT NULL,
        updated_at timestamp NOT NULL,

        PRIMARY KEY(id)
);

CREATE INDEX idx_nome_produto ON PRODUTO(nome);
