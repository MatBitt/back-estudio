create table produtos(

    id BIGSERIAL not null,
    titulo varchar(255) not null UNIQUE,
    descricao varchar(255),
    imagem varchar(255),
    preco decimal(5,2) not null,
    categoria varchar(255),
    primary key(id)
);