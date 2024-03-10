# Projeto de Avaliação Java Reskilling 

Esse é um projeto feito em java, para fazer o cadastro e gerenciamento de pessoas e seus respectivos contatos.

---

### 📦 Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- H2-Console
- Swagger
- JWT Authentication

---

### 🛠️ Configuração e Instalação

A configuração e instalação dos projetos requerem conhecimentos básicos em Maven e Spring Framework. É necessário ter o Java e o Maven instalados na sua máquina.

Para rodar qualquer a aplicação, siga os passos abaixo:

1. Clone o repositório para sua máquina local.
2. Navegue até a pasta do projeto para executar.
3. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

---

### 📚 Documentação e Suporte

- **Swagger:**  Todos os endpoints das aplicações RESTful são documentados utilizando o Swagger, acessível via http://localhost:8080/swagger-ui.html no navegador após a aplicação estar rodando.

- **Autenticação:** A aplicação AppContatos utiliza autenticação JWT. 
Para acessa-lá basta criar um endpoint em uma aplicação para testar APIS (Insomnia por exemplo) e usar o link de exemplo a seguir para gerar um token http://localhost:8080/token?username=Felipe
O Token tem o tempo padrão de duração de 1 hora, ao gerar, você deve incluir o token nos endpoints que serão mostrados a seguir, para obter permissão de usa-los.

---

### 🚀 Como executar a aplicação:

#### Cadastrar uma nova pessoa:
- Para realizar essa ação, basta colocar o seguinte link no endpoint http://localhost:8080/api/pessoas
- Em seguida coloque os dados em formato JSON, para realizar o cadastro da pessoa como o exemplo abaixo:

{
		"nome": "Fulano da silva",
		"endereco": "Rua Alvin, 40",
		"cep": "87654321",
		"cidade": "Fortaleza",
		"uf": "ce"
}

#### Buscar todas as pessoas cadastradas:
- Para realizar essa busca, basta colocar o seguinte link no endpoint.
http://localhost:8080/api/pessoas


#### Buscar uma pessoa específica
- Para realizar essa busca, basta colocar o seguinte link no endpoint, e alterar o último digito do ID conforme desejar.
http://localhost:8080/api/pessoas/1


#### Atualizar os dados de uma pessoa
- Para realizar essa ação, basta colocar o seguinte link no endpoint
http://localhost:8080/api/pessoas
- Em seguida coloque os dados em formato JSON, para realizar a atualização:

 {
		"id": 20,
		"nome": "Fulano da silva",
		"endereco": "Rua Alvin, 200",
		"cep": "87654321",
		"cidade": "São Paulo",
		"uf": "sp"
}


#### Deletar uma pessoa no banco de dados
- Para realizar essa ação, basta colocar o seguinte link no endpoint, e alterar o último digito do ID conforme desejar.
http://localhost:8080/api/pessoas/2


#### Realizar busca no formato Mala direta
- Para realizar essa busca, basta colocar o seguinte link no endpoint, e alterar o último digito do ID conforme desejar.
http://localhost:8080/api/pessoas/findMalaDireta/1


#### Cadastrar um novo contato:
- Para realizar essa ação, basta colocar o seguinte link no endpoint http://localhost:8080/api/contatos/save
- Em seguida coloque os dados em formato JSON, para realizar o cadastro da pessoa como o exemplo abaixo, alterando o ID da pessoa conforme desejar.

{
	"id": 0,
	"tipoContato": 0,
	"contato": "222222",
	"pessoa": {
		"id": 1
	}
}


#### Buscar todos os contatos cadastradas:
- Para realizar essa busca, basta colocar o seguinte link no endpoint.
http://localhost:8080/api/contatos/all


#### Buscar um contato específico
- Para realizar essa busca, basta colocar o seguinte link no endpoint, e alterar o último digito do ID conforme desejar.
http://localhost:8080/api/contatos/1


#### Atualizar os dados de contato de uma pessoa
- Para realizar essa ação, basta colocar o seguinte link no endpoint
http://localhost:8080/api/contatos/update
- Em seguida coloque os dados em formato JSON, para realizar a atualização, alterando os IDS de pessoa e contato conforme desejar.


{
	"id": 1,
	"tipoContato": 2,
	"contato": "000000",
	"pessoa": {
		"id": 2,
		"nome": "Fulano da silva",
		"endereco": "Rua Alvin, 40",
		"cep": "87654321",
		"cidade": "Fortaleza",
		"uf": "ce"
	}
}


#### Deletar um contato no banco de dados
- Para realizar essa ação, basta colocar o seguinte link no endpoint, e alterar o último digito do ID conforme desejar.
http://localhost:8080/api/contatos/delete/1

