# Intruções para uso da aplicação.

Sempre que na URL houver {{id}}, deve ser substituido pelo id do pagamento. 

exemplo:

antes:
http://localhost:8080/payments/{{id}}

depois:
http://localhost:8080/payments/1


Foram instanciados em Config.java 3 agendamentos para exemplificação, porém existe um método abaixo para criação via JSON.

## Efetuar busca de todos os pagamentos registrados - GET

http://localhost:8080/payments/

## Efetuar busca por id e ter todos os dados do pagamento - GET

http://localhost:8080/payments/{{id}}

## Efetuar busca por id e ter o status do pagamento. Haverá o retorno PENDING | PAID - GET

http://localhost:8080/payments/status/{{id}}

## registrar agendamento - POST

É necessário informar apenas o valor, data e horario do pagamento como no exemplo a seguir utilizando o metodo POST.

O agendamento é registrado, com status PENDING e a requisição retorna o seu ID.

    {
        "valueOfPayment": 2000.0,
        "date": "2022-07-28 12:00:00"
    }


http://localhost:8080/payments/create

## Efetuar alteraçã de status do pagamento para PAID - PUT

http://localhost:8080/payments/pay/{{id}}

Ao alterar a o Status de pagamento para PAID, não será possível excluir o agendamento ou alterar sua data e hora.

## Efetuar exclusão de agendamento - só é possivel Deletar agendamentos com status PENDING - DELETE

http://localhost:8080/payments/delete/{{id}}


## Efetuar alteração da data ou hora - só é possivel atualizar agendamentos com status PENDING - PUT

informar na URL o ID e passar nova data e hora via JSON como no exemplo abaixo.

    	{
        "date": "2022-03-12 22:00:00",
    	}

http://localhost:8080/payments/UpdateDateOrHour/{{id}}



<p align="center">
 <img src="https://github.com/jsantos-examples/flexpag-desafio-backend/blob/main/contents/flexpag.png" width="600" alt="Banner Flexpag">
</p>

# 🚀 Desafio backend

Bem-vindo(a). Este é o desafio Back end!

O objetivo deste desafio é avaliar suas habilidades em programação.
Quando concluir o desafio, basta responder o e-mail onde recebeu o link do repositório.
Em seguida, enviaremos o feedback e as instruções dos próximos passos!

Caso tenha alguma dúvida, nós estamos disponíveis para tirá-las.
Bom desafio!

> ⚠️ **É importante que o seu repo esteja público, caso contrário não iremos conseguir avaliar sua resposta**

---

- [🧠 Contexto](#-contexto)
  - [🚰 Fluxo esperado](#-fluxo-esperado)
- [✔️ Critérios de Avaliação](#️-critérios-de-avaliação)
- [:rocket: Instruções](#rocket-instruções)
  - [:notebook: To-do list](#notebook-to-do-list)

# 🧠 Contexto

A Flexpag é uma empresa de tecnologia especializada em soluções digitais de pagamento. Sabendo disso, montamos um desafio que consiste em implementar um serviço de pagamento agendando.

### 🚰 Fluxo esperado

- Quando um agendamento é enviado deve ser registrado como `pending` e retornado o id;
- O usuário deve conseguir consultar o status do agendamento `pending`|`paid`;
- :warning: **Se o pagamento ainda não foi realizado o usuário pode**;
  - Excluir o agendamento;
  - Atualizar a data:hora do agendamento;
  
## ✔️ Critérios de Avaliação

Além dos requisitos levantados acima, iremos olhar para os seguintes critérios durante a correção do desafio:

- Eficiência e simplicidade;

## :rocket: Instruções

Chegou a hora de colocar a mão na massa!

### Aplicação

A aplicação já está com o pre setup default. 

**dependências:**
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-devtools
- h2
- lombok

| componente | porta |
| --------- | ----------- |
| Aplicação  | `8080` |

aplication.yaml foi configurado para apontar para o banco local h2
```
spring:
  datasource:
    driverClassName: org.h2.Driver
    url: jdbc:h2:mem:payment-scheduler
    username: admin
    password: admin
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        format_sql: true
        show_sql: true
```

### :notebook: To-do list
- [ ] Fazer o fork do projeto
- [ ] Implementar solução
- [ ] Enviar link do projeto

:information_source: _Sinta-se livre para incluir quaisquer observações que achar necessário_

---

_O desafio acima foi cuidadosamente construído para propósitos de avaliação apenas._
