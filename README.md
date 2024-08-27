Version: 1.0.0

API Endpoint

Endereço do gateway para execução de testes com a API:
http://localhost:8081/api/swagger-ui.html
Existe 5 pedidos ja cadastro 

Criação de pedido
Exemplo json
[
  {
    "codCliente": 51,
    "dtCadastro": "08/04/2024",
    "nameProduto": "Carro",
    "numControle": 7868,
    "qdtProduto": 1,
    "valorUniProduto": 200
  },
    {
    "codCliente": 1,
    "dtCadastro": "09/04/2024",
    "nameProduto": "Carro",
    "numControle": 7869,
    "qdtProduto": 2,
    "valorUniProduto": 200
  }
]

Listagem/filtragem 
lista todos usar o parametro "todos"
Formato para pesquisar por data "dd/MM/yyyy"

Endereço do gateway par aacessar o banco h2
http://localhost:8081/pedido/api/h2-console

JDBC URL=jdbc:h2:mem:pedido-service
username=sa
password=
