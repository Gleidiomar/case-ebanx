Version: 1.0.0

API Endpoint

Endereço do gateway para execução de testes com a API:
http://localhost:8081/api/swagger-ui.html
Existe 5 pedidos ja cadastro 

Criação de pedido
Exemplo json
[
  {
    "codCliente": 61,
    "dtCadastro": "08/04/2022",
    "nameProduto": "Moto",
    "numControle": 9068,
    "qdtProduto": 1,
    "valorUniProduto": 400
  },
    {
    "codCliente": 1,
    "dtCadastro": "09/04/2023",
    "nameProduto": "Carro",
    "numControle": 7850,
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
