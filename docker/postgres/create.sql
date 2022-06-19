-- CREATE TABLE IF NOT EXISTS status
-- (
--     id bigint  NOT NULL,
--     descricao character varying(255),
--     CONSTRAINT status_pkey PRIMARY KEY (id)
-- );
--
-- CREATE TABLE IF NOT EXISTS projeto
-- (
--     id bigint NOT NULL,
--     titulo character varying(255),
--     CONSTRAINT projeto_pkey PRIMARY KEY (id)
-- );
--
-- CREATE TABLE IF NOT EXISTS tarefa
-- (
--     id bigint NOT NULL,
--     frequencia integer NOT NULL,
--     prioridade integer NOT NULL,
--     titulo character varying(255) NOT NULL,
--     minutos_necessario integer NOT NULL,
--     dia_agendado date,
--     status_id bigint DEFAULT 1,
--     projeto_id bigint,
--     CONSTRAINT tarefa_pkey PRIMARY KEY (id),
--     CONSTRAINT projeto_id FOREIGN KEY (projeto_id)
--         REFERENCES projeto (id) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE CASCADE,
--     CONSTRAINT status FOREIGN KEY (status_id)
--         REFERENCES status (id) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE CASCADE
-- );