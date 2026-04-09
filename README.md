# 🚀 EBANX Case - API de Transações

## 📌 Descrição
API REST desenvolvida para simular operações financeiras básicas:

- Depósito
- Saque
- Transferência entre contas
- Consulta de saldo

O projeto foi desenvolvido com foco em **consistência de estado**, **clareza** e **simplicidade**, conforme requisitos do desafio.

---

## 🛠 Tecnologias utilizadas

- Java 8
- Spring Boot
- Maven
- Docker
- Swagger (documentação)

---

## ⚙️ Arquitetura

A aplicação segue uma arquitetura simples em camadas:

- **Controller** → exposição dos endpoints HTTP  
- **Service** → regras de negócio  
- **Domain** → modelo de conta  

Os dados são mantidos **em memória**, conforme especificação do desafio.

---

## 🔄 Funcionalidades

### ➤ Resetar estado
```http
POST /reset

Consultar saldo
GET /balance?account_id=100

Eventos
Depósito
POST /event
{
  "type": "deposit",
  "destination": "100",
  "amount": 10
}

Saque
{
  "type": "withdraw",
  "origin": "100",
  "amount": 5
}

Transferência
{
  "type": "transfer",
  "origin": "100",
  "destination": "200",
  "amount": 15
}

---

## Regras de negócio
-Contas são criadas automaticamente no depósito
-Saques e transferências validam saldo disponível
-Transferências são atômicas e seguras contra concorrência
-Operações inválidas retornam 404 com body 0

---

## Testes
A aplicação possui testes unitários cobrindo:
-Depósito
-Saque
-Transferência
-Casos de erro (saldo insuficiente, conta inexistente)

mvn clean install
java -jar target/*.jar

Local
http://localhost:8080/swagger-ui.html

Produção (Render)
https://case-ebanx.onrender.com/swagger-ui.html

---

## Autor
-Desenvolvido por Gleidiomar Freitas da Silva


