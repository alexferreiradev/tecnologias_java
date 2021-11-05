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
O ambiente de dev para executar dev conter:
* Java 11
* Gradle 7.1
* Spring Boot 2.5.6
* Postgres 10 com banco: sample-api
* Keycloak server x.x.x

## Execução
Execute o banco de dados, a api e teste com o `Insomnia`. Execute a API com `gradle bootRun`.

## Build
Execute `gradle bootJarMainClassName`. Será gerado a pasta target|build. Execute como um Jar normal

[http Basic]: https://github.com/alexferreiradev/tecnologias_java/tree/spring/Spring
[OAuth - branch separada]: https://github.com/alexferreiradev/tecnologias_java/tree/spring/Spring
[JWT - branch separada]: https://github.com/alexferreiradev/tecnologias_java/tree/spring/Spring
[OICD - branch separada]: https://github.com/alexferreiradev/tecnologias_java/tree/spring/Spring