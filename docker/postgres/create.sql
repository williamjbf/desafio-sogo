CREATE TABLE IF NOT EXISTS status
(
    id bigint  NOT NULL,
    descricao character varying(255),
    CONSTRAINT status_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tarefa
(
    id bigint NOT NULL,
    frequencia integer NOT NULL,
    prioridade integer NOT NULL,
    titulo character varying(255) NOT NULL,
    minutos_necessario integer NOT NULL,
    dia_agendado date,
    status_id bigint default 1,
    CONSTRAINT tarefa_pkey PRIMARY KEY (id),
    CONSTRAINT status FOREIGN KEY (status_id)
        REFERENCES status (id) MATCH SIMPLE
        ON DELETE CASCADE
);