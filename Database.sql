create database if not exists SkySearchFuncionarios
    default character set utf8mb4
    collate utf8mb4_unicode_ci;
use SkySearchFuncionarios;

CREATE TABLE IF NOT EXISTS Endereco (
    Id INT PRIMARY KEY AUTO_INCREMENT,
    cep VARCHAR(10) NOT NULL,
    rua VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

create table if not exists Funcionario(
Id int primary key auto_increment,
nome Varchar(50) not null,
senha Varchar(20) not null,
cpf Varchar(11) not null,
email varchar(30) not null,
telefone varchar(11) not null,
genero varchar(50) not null,
data_nasc date not null,
data_admissao date not null,
setor varchar(50) not null,
cargo varchar(50) not null,
salario decimal(7, 2) not null,
endereco_id int unique,
periculosidade boolean not null default false,
noturno boolean not null default false,
constraint fk_endereco foreign key (endereco_id) references Endereco(id)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DELIMITER //

CREATE PROCEDURE Cadastrar(
    IN p_nome VARCHAR(50),
    IN p_senha VARCHAR(20),
    IN p_cpf VARCHAR(11),
    IN p_email VARCHAR(30),
    IN p_telefone VARCHAR(11),
    IN p_genero VARCHAR(50),
    IN p_data_nasc DATE,
    IN p_data_admissao DATE,
    IN p_setor VARCHAR(50),
    IN p_cargo VARCHAR(50),
    IN p_salario DECIMAL(7,2),
    IN p_periculosidade boolean,
    IN p_noturno boolean,

    IN p_cep VARCHAR(10),
    IN p_rua VARCHAR(100),
    IN p_bairro VARCHAR(100),
    IN p_cidade VARCHAR(100)
)
BEGIN

    DECLARE v_endereco_id INT;

    INSERT INTO Endereco (cep, rua, bairro, cidade)
    VALUES (p_cep, p_rua, p_bairro, p_cidade);

    SET v_endereco_id = LAST_INSERT_ID();

    INSERT INTO Funcionario (
        nome, senha, cpf, email, telefone, genero,
        data_nasc, data_admissao, setor, cargo, salario, endereco_id, periculosidade, noturno
    )
    VALUES (
        p_nome, p_senha, p_cpf, p_email, p_telefone, p_genero,
        p_data_nasc, p_data_admissao, p_setor, p_cargo, p_salario,
        v_endereco_id, p_periculosidade, p_noturno
    );

END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE Apagar(
    IN p_Id INT
)
BEGIN
    DECLARE v_endereco_id INT;

    SELECT endereco_id INTO v_endereco_id
    FROM Funcionario
    WHERE Id = p_Id;

    DELETE FROM Funcionario WHERE Id = p_Id;

    DELETE FROM Endereco WHERE Id = v_endereco_id;

END//

DELIMITER ;

DELIMITER //

CREATE PROCEDURE Editar(
    IN p_Id INT,
    IN p_nome VARCHAR(50),
    IN p_senha VARCHAR(20),
    IN p_email VARCHAR(30),
    IN p_telefone VARCHAR(11),
    IN p_genero VARCHAR(50),
    IN p_data_nasc DATE,
    IN p_data_admissao DATE,
    IN p_setor VARCHAR(50),
    IN p_cargo VARCHAR(50),
    IN p_salario DECIMAL(7,2),
    IN p_periculosidade boolean,
    IN p_noturno boolean,

    IN p_cep VARCHAR(10),
    IN p_rua VARCHAR(100),
    IN p_bairro VARCHAR(100),
    IN p_cidade VARCHAR(100)
)
BEGIN
    DECLARE v_endereco_id INT;

    SELECT endereco_id INTO v_endereco_id
    FROM Funcionario
    WHERE Id = p_Id;

    UPDATE Funcionario
    SET
        nome = p_nome,
        senha = p_senha,
        email = p_email,
        telefone = p_telefone,
        genero = p_genero,
        data_nasc = p_data_nasc,
        data_admissao = p_data_admissao,
        setor = p_setor,
        cargo = p_cargo,
        salario = p_salario,
        periculosidade = p_periculosidade, 
        noturno = p_noturno
    WHERE Id = p_Id;

    UPDATE Endereco
    SET
        cep = p_cep,
        rua = p_rua,
        bairro = p_bairro,
        cidade = p_cidade
    WHERE Id = v_endereco_id;

END//

DELIMITER ;

CALL Cadastrar(
    'Anne',
    'admin',
    '5670678065',
    'AnneSteinbrecher@gmail.com',
    '1196545678',
    'Feminino',
    '2002-05-26',
    '2025-02-24',
    'Administração',
    'Administradora',
    12000.00,
    FALSE, 
    FALSE,

    '08735-000',
    'Rua Recanto Junior Paz, 35',
    'Centro',
    'Suzano'
);

CREATE TABLE registro_ponto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_funcionario INT NOT NULL,
    data DATE NOT NULL,
    hora_entrada TIME,
    hora_saida TIME,

    CONSTRAINT unique_dia_funcionario
        UNIQUE (id_funcionario, data)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DELIMITER $$

CREATE PROCEDURE RegistrarEntrada(
    IN p_id_funcionario INT,
    IN p_data DATE,
    IN p_hora TIME
)
BEGIN

    IF EXISTS (
        SELECT 1 FROM registro_ponto
        WHERE id_funcionario = p_id_funcionario
          AND data = p_data
    ) THEN

        UPDATE registro_ponto
        SET hora_entrada = p_hora
        WHERE id_funcionario = p_id_funcionario
          AND data = p_data;

    ELSE

        INSERT INTO registro_ponto (id_funcionario, data, hora_entrada)
        VALUES (p_id_funcionario, p_data, p_hora);

    END IF;

END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE RegistrarSaida(
    IN p_id_funcionario INT,
    IN p_data DATE,
    IN p_hora TIME
)
BEGIN

    UPDATE registro_ponto
    SET hora_saida = p_hora
    WHERE id_funcionario = p_id_funcionario
      AND data = p_data;

END $$

DELIMITER ;