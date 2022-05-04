# Desafio Milenio Capital Dev Jr

Esse repositório é meu código do desenvolvimento do desafio da milenio capital. Inicialmente o código deverá ser inicializado utilizando o Maven.

O código utiliza como banco de dados o MongoDB, um banco de dados não relacional, devido a estrutura utilizada para a criações dos Grafos, podendo incluir junto com o ID, um Array onde contem o objeto Data, que seria a classe onde encontramos os parametros Source, Target e Distance.

Para a execução utilizamos a porta 8080, assim verificar os endpoints da nossa API, você pode verificar na documentação feita com Swagger após iniciar a aplicação através do link:

http://localhost:8080/swagger-ui/index.html

## Notas sobre o desenvolvimento:

Inicialmente, como pode ser visto nos primeiros commits, tentei utilizar o banco MySql, após analisar vi a opção de utilizar o mongo para facilitar o acesso aos dados, através da utilização de documentos para os dados.
Não tendo muita familiriadidade com o MongoDB, deixei para realizar a conteinerização junto com a API ao fim, pois também estou iniciando os estudos com Docker. Inicialmente o MySql estava conteinerizado, já com o MongoDB realizei de forma manual, configurando e instalando localmente o MongoDB, e utilizando a porta padrão 27017.

Foi criado a database mileniocapitalmongodb , onde temos a colleection graph e data.

A API foi criada utilizando as layers Controllers, Service e Repository. Os testes foram feitos utilizando JUnit5 e Mockito.

Após o fim do desenvolvimento, foi incluido nos containers a fim de executar através do docker.


### Utilização
Primeiramente para utilização se deve fazer o clone do repositório

```javascript
cd "diretorio de sua preferencia"

git clone https://gitlab.com/netocampana/desafiomileniocapital
```
### Construção

Para construir o projeto com o Maven, executar os comando abaixo no seu terminal, primeiramente iremos criar o container com o MongoDB:

```javascript
docker pull mongo:latest  

docker run -d --name mongo-on-docker -p 27017:27017 mongo

mvn clean install
```
O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.
Após executar o código do maven, criar o container da aplicação API com o seguinte código:

```javascript
docker build -t springapplication-on-docker .
```
### Execução

Para executar utilizaremos o comando:

```javascript
docker run -d --name springapplication-on-docker -p 8080:8080 mileniocapitaldesafiomongodb
```

Após isso, teremos a inicialização dos containers do Banco de Dados e da aplicação, sendo possível acessar os endpoints pelo link da documentação Swagger.

http://localhost:8080/swagger-ui/index.html