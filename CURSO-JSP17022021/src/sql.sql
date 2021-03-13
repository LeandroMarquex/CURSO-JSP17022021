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