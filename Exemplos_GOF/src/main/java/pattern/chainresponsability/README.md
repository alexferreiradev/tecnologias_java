# Chain of Responsability
Padrão de projeto utilizado para criar uma hierarquia de responsaabilidades.

## Exemplo de uso
- Aprovação de valores para comprar algo para empresa

## Objetos
- Classe pai de responsabilidade (PurchaseRequest)
- SubClasses de responsabilidade
- Requisitador

## Objetivo
Criar uma hierarquia com diferentes regras, sendo que cada uma é maior que a outra;

## Desvantagens
- Alteração de regras: caso precise alterar uma regra entre duas, terá que alterar todas regras acima da nova regra
- 
