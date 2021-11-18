# Projeto
Exemplo de API básica com diversas funções implementadas com `Spring`.

## Spring security
A parte de segurança da API foi desenvolvida utilizando o `Spring security`. Foi criado uma tabela para Usuários e roles no postgres e as permissões para cada rota da api foram definidas no bean de configuração. O modo de autenticação utilizado foi:  
* [http Basic]
* [OAuth - branch separada]
* [JWT - branch separada]
* [OICD - branch separada]

O modo implementado de http Basic utiliza as credenciais cadastradas no arquivo property.

## Dev
O ambiente de dev para executar deve conter:
* Java 11
* Gradle 7.1
* Spring Boot 2.5.6
* Postgres 10 com banco: sample-api
* MongoDB

## Execução
O ambiente precisa conter os bancos postgres e mongodb. Foi criado o arquivo `docker-compose-dbs.yml` para criar este ambiente para o dev executar facilmente a API localmente. Contudo, este arquivo depende de algumas configurações de ambiente, você deve criar um arquivo `.env` contendo:
```shell
PG_PASS=senhaparausuario
MONGO_PASS=senhaparausuario
```
O docker-compose irá ler automaticamente este arquivo para setar as variáveis de ambiente, como o binário executa como root, as variaveis de ambiente manuais devem ser criadas como root. Desse modo, o `.env` é mais conveniente.

Com o arquivo `.env` criado, execute `docker-compose -f docker-compose-dbs.yml up -d` para criar o ambiente dos bancos. Após executar, teste o ambiente. O Postgres pode ser testado criando uma conexão com o PGAdmin para localhost:5432 e o MongoExpress para localhost:8081 que usa a conexão localhost:27017 dentro do docker.

Após os bancos estarem executando, execute a api e teste com o `Insomnia` com localhost:8080. Execute a API com `gradle bootRun`.

## Build
Execute `gradle bootJarMainClassName`. Será gerado a pasta build/libs. Execute como um Jar normal

## Deploy Cloud Native
Este projeto contém arquivos para facilitar no deploy em ambientes cloud. Existe o arquivo `Dockerfile` que descreve como cria uma imagem para containers docker da API. Existe o arquivo `docker-compose.yml` que descreve como criar um ambiente com containers para serviço de api e todas dependencias da api, no cenário simples, os bancos de dados.

**Ambiente testado**

- Docker: 20.10.10
- Docker-compose: 1.29.2
- postgres: 14
- mongo: 5.0.4

**Variáveis de ambiente**

O banco pode ser configurado com a criação de uma arquivo `.env` contendo:
```shell
PG_PASS=senhaparausuario
MONGO_PASS=senhaparausuario
```

**Deploy**

Execute o seguite comando:
```shell
cd dir_of_project
sudo docker-compose up -d
```
Irá criar todos containers necessários para executar os serviços descritos, no nosso caso a api de produtos com o banco. O `-d` é uma configuração para indicar que queremos rodar os containers de forma detached, ou seja, em background, não importando com logs em foreground.

Uma observação, o compose implementado, está com o banco sem volume fixo, ele recria o banco toda vez que inicializa outro container

**Debug deploy**

Utilize os camandos abaixo para verificar os erros:
- compose logs <nome-servico>: mostra os ultimos logs da aplicação no container
- compose down: remove todos serviços
- compose top: mostra aplicações no container
- compose config: mostra como está o arquivo de compose com interpolacoes de variaveis

Mais informações sobre docker-compose em [documentação oficial](https://docs.docker.com/compose/).

[http Basic]: https://github.com/alexferreiradev/tecnologias_java/tree/spring/Spring
[OAuth - branch separada]: https://github.com/alexferreiradev/tecnologias_java/tree/spring_oauth/Spring
[JWT - branch separada]: https://github.com/alexferreiradev/tecnologias_java/tree/spring_jwt/Spring
[OICD - branch separada]: https://github.com/alexferreiradev/tecnologias_java/tree/spring_oicd/Spring