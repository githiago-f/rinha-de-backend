CREATE EXTENSION IF NOT EXISTS pg_trgm;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE OR REPLACE FUNCTION pessoas_gen_fulltext(_nome VARCHAR, _apelido VARCHAR, _stack VARCHAR[]) RETURNS TEXT AS $$ BEGIN RETURN _nome || _apelido || array_to_string(_stack, ', ', '*'); END; $$ LANGUAGE plpgsql IMMUTABLE;
CREATE TABLE IF NOT EXISTS pessoas ( "id" UUID DEFAULT uuid_generate_v4(), "nome" VARCHAR NOT NULL,"apelido" VARCHAR UNIQUE NOT NULL,"nascimento" DATE NOT NULL,"stack" VARCHAR[],"searchable" TEXT GENERATED ALWAYS AS (pessoas_gen_fulltext(nome, apelido, stack)) STORED);
CREATE INDEX IF NOT EXISTS pessoas_fulltext_idx ON public.pessoas USING gist("searchable" public.gist_trgm_ops);
CREATE UNIQUE INDEX IF NOT EXISTS pessoas_apelido_idx ON public.pessoas USING btree(apelido);
