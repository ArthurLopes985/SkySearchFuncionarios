create database if not exists SkySearchFuncionarios;
use SkySearchFuncionarios;

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
endereco varchar(100) not null);

DELIMITER //
CREATE PROCEDURE Cadastrar(
	IN nome Varchar(50),
	IN senha Varchar(20),
	IN cpf Varchar(11),
	IN email varchar(30),
	IN telefone varchar(11),
	IN genero varchar(50),
	IN data_nasc date,
	IN data_admissao date,
	IN setor varchar(50),
	IN cargo varchar(50),
	IN salario decimal(7, 2),
	IN endereco varchar(100)
)
BEGIN
    INSERT INTO Funcionario (nome, senha, cpf, email, telefone, genero, data_nasc, data_admissao, setor, cargo, salario, endereco)
    VALUES (nome, senha, cpf, email, telefone, genero, data_nasc, data_admissao, setor, cargo, salario, endereco);
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE Apagar(
    IN p_Id INT
)
BEGIN
    DELETE FROM Funcionario WHERE Id = p_Id;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE Editar(
	IN p_Id int,
	IN nome Varchar(50),
	IN senha Varchar(20),
	IN email varchar(30),
	IN telefone varchar(11),
	IN genero varchar(50),
	IN data_nasc date,
	IN data_admissao date,
	IN setor varchar(50),
	IN cargo varchar(50),
	IN salario decimal(7, 2),
	IN endereco varchar(100)
)
BEGIN
    UPDATE Funcionario
    SET nome = nome,
    senha = senha,
    email = email,
    telefone = telefone,
    genero = genero,
    data_nasc = data_nasc,
    data_admissao = data_admissao,
    setor = setor,
    cargo = cargo,
    salario = salario,
    endereco = endereco
    WHERE Id = p_Id;
END;
//
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
    'Rua Recanto Junior Paz, 35, Suzano.'
);