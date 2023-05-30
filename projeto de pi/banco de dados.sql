CREATE DATABASE empresa_portaria;

USE empresa_portaria;

CREATE TABLE funcionarios (
  id INT PRIMARY KEY,
  nome VARCHAR(100),
  cargo VARCHAR(100),
  rgf VARCHAR(100),
  rg VARCHAR(100),
  cpf VARCHAR(100)
);

CREATE TABLE empresas_solicitantes (
  id INT PRIMARY KEY,
  CNPJ VARCHAR(100),
  nome VARCHAR(100),
  endereco VARCHAR(100),
  ie VARCHAR(100),
  CEP VARCHAR(100)
);