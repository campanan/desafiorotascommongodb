# Desafio Rotas 

Esse repositório é meu código do desenvolvimento do desafio da milenio capital. Inicialmente o código deverá ser inicializado utilizando o Maven.

O código utiliza como banco de dados o MongoDB, um banco de dados não relacional, devido a estrutura utilizada para a criações dos Grafos, podendo incluir junto com o ID, um Array onde contem o objeto Data, que seria a classe onde encontramos os parametros Source, Target e Distance.

Para a execução utilizamos a porta 8080, assim verificar os endpoints da nossa API, você pode verificar na documentação feita com Swagger após iniciar a aplicação através do link:

As funcionalidades do desafio eram previstas como um CRUD, de grafos, onde esse grafo teria a representação de uma rota de uma cidade A até uma cidade B, e distancia N, com a opção de Salvar, recuperar grafos, recuperar grafos por ID, e após esses, teriamos mais 2 métodos que serão descritos abaixo:

Para exemplificar, esse seria um grafo salvo no CRUD: 
```javascript
{​
  "id" : 1,
  "data": [
    {​
      "source": "A", "target": "B", "distance": 6
    }​,
    {​
      "source": "A", "target": "E", "distance": 4
    }​,
    {​
      "source": "B", "target": "A", "distance": 6
    }​,
    {​
      "source": "B", "target": "C", "distance": 2
    }​,
    {​
      "source": "B", "target": "D", "distance": 4
    }​,
    {​
      "source": "C", "target": "B", "distance": 3
    }​,
    {​
      "source": "C", "target": "D", "distance": 1
    }​,
    {​
      "source": "C", "target": "E", "distance": 7
    }​,
    {​
      "source": "D", "target": "B", "distance": 8
    }​,
    {​
      "source": "E", "target": "B", "distance": 5
    }​,
    {​
      "source": "E", "target": "D", "distance": 7
    }​
  ]
}​
```

• Encontrar todas rotas disponíveis dada uma cidade de origem e outra de destino em um grafo salvo anteriormente
  um grafo salvo anteriormente, esse endpoint deverá calcular todas as rotas disponíveis de uma cidade origem para outra de destino, dado um número máximo de paradas. Se não existirem rotas possíveis, o resultado deverá ser uma lista vazia. Se o parâmetro "maxStops" não for definido, você deverá listar todas as rotas possíveis. Se o grafo não existir, deverá retornar HTTP NOT FOUND.
  Exemplo: No grafo (AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7), as possíveis rotas de A para C com máximo de 3 paradas seriam: ["ABC", "ADC", "AEBC"]
```javascript  
  Endpoint: http://localhost:8080/routes/<graphId>/from/<town1>/to/<town2>?maxStops=<maxStops>
  HTTP Method: POST
  HTTP Success Response Code: OK (200)
  HTTP Error Response Code: NOT FOUND (404)
```
• Utilizando um grafo salvo anteriormente, esse endpoint deverá determinar a rota cuja distância seja a mínima possível entre duas cidades. Se as cidades de origem e destino forem iguais, o resultado deverá ser zero. Se não exitir rota possível entre as duas cidades, então o resultado deverá ser -1. Se o grafo não existir, deverá retornar HTTP NOT FOUND.

```javascript
  Endpoint: http://localhost:8080/distance/<graphId>/from/<town1>/to/<town2>
  HTTP Method: POST
  HTTP Success Response Code: OK (200)
  HTTP Error Response Code: NOT FOUND (404)
```


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
docker run -d --name desafiorotas-com-mongodb -p 8080:8080 springapplication-on-docker

Após isso, teremos a inicialização dos containers do Banco de Dados e da aplicação, sendo possível acessar os endpoints pelo link da documentação Swagger.

http://localhost:8080/swagger-ui/index.html