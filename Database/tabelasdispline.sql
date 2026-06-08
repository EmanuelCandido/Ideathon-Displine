CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(30) NOT NULL,
    data_cadastro DATE DEFAULT CURRENT_DATE
);

CREATE TABLE aluno (
    id BIGINT PRIMARY KEY,
    matricula VARCHAR(50) NOT NULL UNIQUE,
    turma VARCHAR(50) NOT NULL,
    curso VARCHAR(100) NOT NULL,

    CONSTRAINT fk_aluno_usuario
        FOREIGN KEY (id)
        REFERENCES usuario(id)
        ON DELETE CASCADE
);

CREATE TABLE professor (
    id BIGINT PRIMARY KEY,
    materia VARCHAR(100) NOT NULL,
    turma VARCHAR(50) NOT NULL,

    CONSTRAINT fk_professor_usuario
        FOREIGN KEY (id)
        REFERENCES usuario(id)
        ON DELETE CASCADE
);

CREATE TABLE evento (
    id_evento BIGSERIAL PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    data_evento DATE NOT NULL,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    horario_inicio TIME NOT NULL,
    horario_fim TIME NOT NULL,
    tipo_evento VARCHAR(50) NOT NULL,
    prioridade VARCHAR(30) NOT NULL,
    materia_relacionada VARCHAR(100),

    CONSTRAINT fk_evento_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuario(id)
        ON DELETE CASCADE
);
