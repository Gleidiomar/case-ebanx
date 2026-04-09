Version: 1.0.0

API Endpoint

Endereço do gateway para execução de testes com a API:
http://localhost:8081/api/swagger-ui.html
Existe 5 contas ja cadastra 

Criação da conta
Exemplo json
{ "numConta": 234, "saldo":180.37}

Criação da transacao
Exemplo json
{"formaPagamento":"D", "numConta": 234, "valor":10}

Pesquisar a conta
{"numConta": 234, "saldo": 170.07}

Endereço do gateway par aacessar o banco h2
http://localhost:8081/api/h2-console

JDBC URL=jdbc:h2:mem:gestao-service
username=sa
password=
