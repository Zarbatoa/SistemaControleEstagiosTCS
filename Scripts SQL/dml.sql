-- drop database dbestagios;
-- create database dbestagios;

USE DBESTAGIOS;

# DML PARA TESTES

INSERT INTO INSTITUICAO_ENSINO (ID, RAZAOSOCIAL, CNPJ, EMAIL, REPRESENTANTE, TELEFONEFIXO, TELEFONECEL, INFORELEVANTE, 
  ENDRUA, ENDNUMERO, ENDCOMPLEMENTO, ENDBAIRRO, ENDCEP, ENDCIDADE, ENDSIGLAESTADO)
	VALUES (1, 'Senac Prainha', '11222333444455', 'senac@prainha.com', 'Luciano Original', '(48)3333-4545', NULL, NULL,
		'Rua Silva Jardim', '360', NULL, 'Centro', '88020-200', 'Florianópolis', 'SC' );
INSERT INTO INSTITUICAO_ENSINO (ID, RAZAOSOCIAL, CNPJ, EMAIL, REPRESENTANTE, TELEFONEFIXO, TELEFONECEL, INFORELEVANTE, 
  ENDRUA, ENDNUMERO, ENDCOMPLEMENTO, ENDBAIRRO, ENDCEP, ENDCIDADE, ENDSIGLAESTADO)
	VALUES (2, 'Senac Palhoça', '22666559984783', 'senac@palhoca.com', 'Luciano Palhoçense', '(48)3332-8844', NULL, NULL,
		'Rua João Pereira dos Santos', '303', NULL, 'Pte. do Imaruim', '88130-475', 'Palhoça', 'SC' );


INSERT INTO USUARIO (ID, EMAIL, LOGIN, SENHA, ID_INSTITUICAO_ENSINO)
	VALUES (1, 'lucas@lucas.com', 'lucas', 'lucas', 1);
INSERT INTO USUARIO (ID, EMAIL, LOGIN, SENHA, ID_INSTITUICAO_ENSINO)
	VALUES (2, 'matheus@matheus.com', 'matheus', 'matheus', 2);

INSERT INTO CURSO (ID, MODALIDADE, NOME, TURNO, ID_INSTITUICAO_ENSINO)
	VALUES (1, 'mod1', 'Análise e Desenvolvimento de Sistemas', 'Noturno', 1);
INSERT INTO CURSO (ID, MODALIDADE, NOME, TURNO, ID_INSTITUICAO_ENSINO)
	VALUES (2, 'mod2', 'Gestão da Tecnologia da Informação', 'Matutino', 1);

INSERT INTO ESTAGIARIO (ID, NOME, EMAIL, DATANASCIMENTO, CPF, TELEFONEFIXO, TELEFONECEL, NOMEDOPAI, NOMEDAMAE, MATRICULADO, 
  NUMEROMATRICULA, REPRESENTANTELEGAL, RGREPLEGAL, ENDRUA, ENDNUMERO, ENDCOMPLEMENTO, ENDBAIRRO, ENDCEP, ENDCIDADE, ENDSIGLAESTADO, ID_CURSO)
    VALUES (1, 'Maria Pereira', 'maria@maria.com',  '1997-10-17', '46678810144', '(48)3244-7985', NULL, 'João Pereira', 'Claudete Pereira', 0,
    '3429764', NULL, NULL, 'Rua das Algas', '1103', NULL, 'jurerê', '88053-505', 'Florianópolis', 'SC', 1);
INSERT INTO ESTAGIARIO (ID, NOME, EMAIL, DATANASCIMENTO, CPF, TELEFONEFIXO, TELEFONECEL, NOMEDOPAI, NOMEDAMAE, MATRICULADO, 
  NUMEROMATRICULA, REPRESENTANTELEGAL, RGREPLEGAL, ENDRUA, ENDNUMERO, ENDCOMPLEMENTO, ENDBAIRRO, ENDCEP, ENDCIDADE, ENDSIGLAESTADO, ID_CURSO)
    VALUES (2, 'João da Silva', 'joao@joao.com',  '1988-03-09', '74481304417', '(47)3233-7744', NULL, 'Paulo da Silva', 'Clara da Silva', 1,
    '3429764', NULL, NULL, 'Rua das Algas', '1103', NULL, 'jurerê', '88053-505', 'Florianópolis', 'SC', 2);

INSERT INTO UNIDADE_CONCEDENTE (ID, RAZAOSOCIAL, CNPJ, EMAIL, TELEFONEFIXO, REPRESENTANTE, CargoDoRepresentante, CPFREPRESENTANTE, EHAGENCIAINTEGRADORA,
  ENDCEP, ENDRUA, ENDNUMERO, ENDBAIRRO, ENDCIDADE, ENDSIGLAESTADO, ENDCOMPLEMENTO)
	VALUES (1, 'Digitro', '84997586104122', 'digitro@email.org', '(48)3344-8754', 'Antonio', 'CEO', '84755864701', 0, 
    '88040-400', 'Avenida Desembargador Vítor Lima', '77', 'Trindade', 'Florianópolis', 'SC', 'Quadra');



-- SELECT * FROM ESTAGIARIO;
-- SELECT * FROM CURSO;
-- SELECT * FROM USUARIO;
-- SELECT * FROM INSTITUICAO_ENSINO;
-- SELECT * FROM UNIDADE_CONCEDENTE;
SELECT * FROM ESTAGIO;

UPDATE FROM ESTAGIO
 SET STATUS = 'INATIVO'