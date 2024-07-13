CREATE TABLE CATEGORIA_PRODUTO (
        id BIGSERIAL ,
        nome varchar(255) NOT NULL,
        ativo BOOLEAN NOT NULL DEFAULT TRUE,
        created_by varchar(255) NOT NULL,
        updated_by varchar(255) NOT NULL,
        created_at timestamp NOT NULL,
        updated_at timestamp NOT NULL,

        PRIMARY KEY(id)
);

CREATE INDEX idx_nome ON CATEGORIA_PRODUTO(nome);


insert into
    CATEGORIA_PRODUTO(nome, created_at, updated_at, created_by, updated_by)
values
    ('Eletrônicos', now(), now(), 'Gustavo', 'Gustavo'),
    ('Alimentos e Bebidas', now(), now(), 'Gustavo', 'Gustavo'),
    ('Roupas e Acessórios', now(), now(), 'Gustavo', 'Gustavo'),
    ('Saúde e Beleza', now(), now(), 'Gustavo', 'Gustavo'),
    ('Esporte e Lazer', now(), now(), 'Gustavo', 'Gustavo');
