# Teste Codegroup Project-Management

Para este teste foi usado:
- **DOCKER**
- **SPRING BOOT**
- **MAVEN**
- **BOOTSTRAP 5**
- **JAVA 17**

O projeto se conecta a um banco PostgreSQL local, que foi iniciado utilizando Docker com a imagem do PostgreSQL:
[PostgreSQL Docker Image](https://hub.docker.com/_/postgres)

## Configuração do Banco de Dados

Para executar a aplicação, é necessário um banco PostgreSQL com os seguintes dados de conexão:
- **URL**: `jdbc:postgresql://localhost:5432/prjmngmtdb`
- **Username**: `codegroup`
- **Password**: `codegroup123`


## Comandos para execução do container

Após baixar a imagem do PostgreSQL, execute os seguintes comandos:

```bash
docker run --name prj-mngmt-postgres -e POSTGRES_USER=codegroup -e POSTGRES_PASSWORD=codegroup123 -e POSTGRES_DB=prjmngmtdb -p 5432:5432 -d postgres
docker exec -it prj-mngmt-postgres psql -U codegroup -d prjmngmtdb
```

## Scripts para Criação das Tabelas

Para configurar o banco de dados, execute os seguintes scripts SQL:

```sql
CREATE TABLE members (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(500) NOT NULL,
	cargo VARCHAR(500) NOT NULL
);

CREATE TABLE project_management (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(500) NOT NULL,
    dt_inicio DATE NOT NULL,
    gerente_responsavel VARCHAR(200) NOT NULL,
    dt_prv_termino DATE NOT NULL,
    dt_real_termino DATE NULL,
	orcamento_total NUMERIC(10,2) NULL,
	descricao VARCHAR(500) NULL,
	status VARCHAR(200) NULL,
	risco VARCHAR(200) NULL,
	member_id BIGINT NULL,
	CONSTRAINT fk_member
        FOREIGN KEY (member_id) 
        REFERENCES members(id)
        ON DELETE SET NULL
);
```
## Regras de negócio

As regras de negócio criadas foram:
- **Projetos com status: iniciado, em andamento ou encerrado não podem ser excluídos (na lista não é exibido o ícone de exclusão para projetos com esses status)**:
- **Existem 2 controllers no projeto: Um controller Rest que disponibiliza uma API, com endpoints para adição e listagem dos membros. E um controller MVC que não disponiliza endpoints e atua em conjunto com o jsp.**:
- **Ao criar um novo projeto são disponibilizados no "select" membros apenas membros com o cargo de "funcionario"**

## Demais informações

- **O projeto contém testes unitários que podem ser executados via linha de comando com: mvn test**:
- **O projeto utiliza Lombok e Log4J.**:
- **O projeto não apresenta issues no SonarLint**:
