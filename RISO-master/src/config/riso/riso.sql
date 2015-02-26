DROP TABLE documentos;
create table documentos(

palavrasMarcadas text,
vetorParagrafo text,
nomeArquivo varchar(255),
PRIMARY KEY(nomeArquivo)

);

DROP TABLE termoEnriquecido;
create table termoEnriquecido(
id serial NOT NULL,
termo varchar(255),
vetoresTematicos text,
PRIMARY KEY(id)
);

create table topicos(
termo varchar(255),
topico varchar(255),
PRIMARY KEY(termo,topico)
);