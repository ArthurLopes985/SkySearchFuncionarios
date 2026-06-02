# SkySearchFuncionarios
Projeto desenvolvido para fins acadêmicos, com foco na aplicação de conceitos de Programação Orientada a Objetos, Design Patterns, MVC, JDBC e desenvolvimento web com Java.

## 📌 Sobre
Sistema web para gerenciamento de funcionários desenvolvido em Java utilizando JSP, Servlets, padrão MVC, DAO, Command, Factory, Builder e Decorator.
O sistema permite o cadastro, edição, remoção e consulta de funcionários, além do controle de ponto e cálculo automático de salários considerando horas extras, horas em débito, adicional noturno e adicional de periculosidade.

## 🛠 Tecnologias
- Java
- JSP
- Servlets
- Apache Tomcat 9
- MySQL
- JDBC
- HTML5
- CSS3
- JavaScript
- Padrão MVC

## 🏛️ Arquitetura e Padrões Utilizados
        JSP
        ↓
        Controller (Front Controller)
        ↓
        Factory
        ↓
        Command
        ↓
        Service
        ↓
        DAO
        ↓
        MySQL

## 🎯 Padrões Utilizados
- Command Pattern

    Responsável pelo tratamento das operações do sistema através do Controller principal.
    Exemplos:
    EntradaCommand
    SaidaCommand
    EntrarCommand
    REGISTRARPONTOCommand
    Factory Pattern
    Utilizado para criar os comandos dinamicamente a partir das requisições recebidas pelo Controller.

-Builder Pattern

    Utilizado para construção dos objetos complexos do sistema.
    Exemplo:
    Funcionarios funcionario =
        new Funcionarios.FuncionariosBuilder()
            .comNome(nome)
            .comCpf(cpf)
            .comEmail(email)
            .build();

- Decorator Pattern

    Utilizado para cálculo do salário final do funcionário.
    Decoradores implementados:
    HorasExtras
    HorasDevendo
    Periculosidade
    Noturno
    Exemplo:
    ISalario salario = new SalarioBase(valor);
    salario = new HorasExtras(salario, horasExtras);
    salario = new HorasDevendo(salario, horasDevendo);
    salario = new Periculosidade(salario);
    salario = new Noturno(salario);

## ⚡ Funcionalidades
- Login
    - Autenticação por ID e senha.
    - Controle de sessão.
    - Validação de credenciais.

- Cadastro de Funcionários
    - Nome
    - Senha
    - CPF
    - E-mail
    -Telefone
    - Gênero
    -Data de nascimento
    -Data de admissão
    - Setor
    - Cargo
    - Salário
    - Adicional de periculosidade
    - Adicional noturno
    - Endereço

- Consulta de Funcionários

Permite:
    - Listar todos os funcionários
    - Buscar funcionário por ID

Informações exibidas:
    - ID
    - Nome
    - CPF
    - E-mail
    - Telefone
    - Gênero
    - Data de nascimento
    - Data de admissão
    - Setor
    - Cargo
    - Salário
    - Endereço
    - Periculosidade
    - Trabalho noturno

- Edição de Funcionários
Permite alterar:
    - Dados pessoais
    - Dados profissionais
    - Salário
    - Endereço
    - Adicionais trabalhistas
    - Validação por senha para confirmação.

- Exclusão de Funcionários
    - Exibe todos os dados cadastrados antes da exclusão.
    - Requer confirmação da senha atual para garantir segurança.

- Registro de Ponto
Controle diário de jornada.
Funções:
    - Registrar entrada
    - Registrar saída
    - Verificar atrasos
    - Calcular horas trabalhadas
    - Calcular horas extras
    - Calcular horas em débito
    - Cálculo Automático de Salário

O salário é recalculado considerando:
    - Salário base
    - Horas extras
    - Horas não trabalhadas
    - Adicional noturno
    - Adicional de periculosidade

## 📁 Estrutura do Projeto
src
│
├── controller
│   ├── EntrarCommand
│   ├── EntradaCommand
│   ├── SaidaCommand
│   ├── REGISTRARPONTOCommand
│   └── ...
│
├── dao
│   ├── FuncionariosDAO
|   |  ├── Atualizar
|   |  ├── Cadastar
|   |  └── ...
│   └── RegistroPontoDAO
|      ├── BuscaPorMes
|      ├── EntradaRegistrada
|      └── ...
│
├── model
│   ├── Funcionarios
│   ├── RegistroPonto
│   ├── Endereco
│   ├── SalarioBase
│   ├── HorasExtras
│   ├── HorasDevendo
│   ├── Periculosidade
│   └── Noturno
│
├── service
│   ├── FuncionariosService
|   |  ├── Apagar
|   |  ├── Cadastro
|   |  └── ...
│   └── RegistroPontoService
|      ├── RegistraEntrada
|      └── RegistraSaida
│
└── util
    └── ConnectionFactory

Banco de Dados

- Banco utilizado: MySQL

## 🗄️ Principais tabelas:
- funcionario
    - id
    - nome
    - senha
    - cpf
    - email
    - telefone
    - genero
    - data_nascimento
    - data_admissao
    - setor
    - cargo
    - salario
    - periculosidade
    - noturno
    - endereco
        - cep
        - rua
        - bairro
        - cidade
    - registro_ponto
        - id
        - id_funcionario
        - data
        - hora_entrada
        - hora_saida

## 🖥️ Telas do Sistema
- Login: Tela inicial de autenticação do sistema.
- Cadastro: Formulário completo para inclusão de novos funcionários.
- Listagem: Visualização de todos os funcionários cadastrados.
- Edição: Atualização dos dados cadastrais.
- Exclusão: Confirmação e remoção de funcionários.
- Registro de Ponto: Controle de entrada, saída e cálculo salarial.

## 📜 Regras de Negócio
- Jornada padrão: 08:00 às 17:00
- Tolerância: 10 minutos
- Hora Extra: Acréscimo de 50%
- Horas Devendo: Desconto proporcional ao valor da hora
- Adicional de Periculosidade: 30% sobre o salário base
- Adicional Noturno: 20% sobre o salário base

## 🚀 Como Executar o Projeto

### Pré-requisitos

Antes de executar o sistema, é necessário ter instalado:

- Java JDK 23 (ou versão compatível)
- Apache Tomcat 9
- MySQL Server
- NetBeans IDE (recomendado)

### Instalação

1. Faça o download do projeto em formato ZIP ou clone o repositório.
    git clone https://github.com/ArthurLopes985/skysearchfuncionarios.git
cd skysearchfuncionarios.
2. Extraia os arquivos em uma pasta de sua preferência.
3. Abra o projeto no NetBeans.
4. Crie o banco de dados MySQL utilizando os scripts SQL disponibilizados no projeto.
5. Configure as credenciais de acesso ao banco de dados na classe `ConnectionFactory`.

```java
String URL = "jdbc:mysql://localhost:3306/SkySearchFuncionarios";
String USER = "seu_usuario";
String PASSWORD = "sua_senha";

6. Compile o projeto.
7. Execute a aplicação utilizando o Apache Tomcat.
8. Acesse o sistema pelo navegador:
    http://localhost:8080/skysearchfuncionarios

### Primeiro Acesso
- O sistema já possui um usuário administrador cadastrado para permitir o gerenciamento inicial dos funcionários. Após o login, novos usuários poderão ser cadastrados através da interface do sistema. / ID: 1 / Senha: admin

## 👨‍💻 Autores
Arthur Lopes de Oliveira                                                                                                                                                                    
GitHub: https://github.com/ArthurLopes985  
LinkedIn: https://linkedin.com/in/arthurlopes985

Bruno da Silva Negy                                                                                                                                                                         
GitHub: https://github.com/BrunoNegy  
LinkedIn: https://linkedin.com/in/bruno-da-silva-negy-8884132a6

## 📄 Licença
Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.
