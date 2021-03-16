ALTER TABLE public.usuario ADD COLUMN nome character varying(500);
ALTER TABLE public.usuario ADD COLUMN telefone character varying(500);

create SEQUENCE serialuser
INCREMENT 1
MINVALUE 1
MAXVALUE 9223372036854775807
START 82
CACHE 1;

ALTER TABLE serialuser OWNER TO postgres;

delete from usuario

alter table usuario add column id bigint;

alter table usuario alter column id set not null;

alter table usuario alter column id set default nextval('serialuser'::regclass);


   
 CREATE SEQUENCE produtosequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 8
  CACHE 1;
ALTER TABLE produtosequence
  OWNER TO postgres;
	      

CREATE TABLE produto
(
  idProduto bigint NOT NULL DEFAULT nextval('produtosequence'::regclass),
  nomeProduto character varying(500),
  quantidadeProduto numeric(10,4),
  valorProduto numeric(10,4),
  CONSTRAINT produto_pkey PRIMARY KEY (idProduto)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO postgres;