CREATE TABLE Usuario (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL CHECK (tipo_usuario IN ('Aluno', 'Professor')),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Turma (
    id_turma SERIAL PRIMARY KEY,
    nome_turma VARCHAR(50) NOT NULL UNIQUE,
    ano_letivo INT NOT NULL
);

CREATE TABLE Aluno (
    id_usuario INT PRIMARY KEY REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    matricula VARCHAR(20) UNIQUE NOT NULL,
    curso VARCHAR(100) NOT NULL,
    id_turma INT REFERENCES Turma(id_turma) ON DELETE SET NULL
);

CREATE TABLE Professor (
    id_usuario INT PRIMARY KEY REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    materia VARCHAR(100) NOT NULL
);

CREATE TABLE Professor_Turma (
    id_usuario INT REFERENCES Professor(id_usuario) ON DELETE CASCADE,
    id_turma INT REFERENCES Turma(id_turma) ON DELETE CASCADE,
    PRIMARY KEY (id_usuario, id_turma)
);

CREATE TABLE Evento (
    id_evento SERIAL PRIMARY KEY,
    id_usuario INT REFERENCES Usuario(id_usuario) ON DELETE CASCADE,
    id_turma INT REFERENCES Turma(id_turma) ON DELETE CASCADE,
    data_evento DATE NOT NULL,
    titulo VARCHAR(100) NOT NULL,             
    descricao TEXT,                             
    horario_inicio TIME NOT NULL,                        
    horario_fim TIME NOT NULL,                           
    tipo_evento VARCHAR(30) DEFAULT 'Estudo' NOT NULL,   
    prioridade VARCHAR(10) DEFAULT 'Baixa' NOT NULL,          
    materia_relacionada VARCHAR(100) NOT NULL
);

CREATE TABLE Post (
    id_post SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL REFERENCES Professor(id_usuario) ON DELETE CASCADE,
    titulo VARCHAR(150) NOT NULL,
    conteudo TEXT NOT NULL,
    data_publicacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Comentario (
    id_comentario SERIAL PRIMARY KEY,
    id_post INT NOT NULL REFERENCES Post(id_post) ON DELETE CASCADE,
    id_usuario INT NOT NULL REFERENCES Aluno(id_usuario) ON DELETE CASCADE,
    conteudo TEXT NOT NULL,
    data_comentario TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);