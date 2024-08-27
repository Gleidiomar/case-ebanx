package br.com.pedido.factory;

import br.com.pedido.dto.PedidoDTO;

public class CreatePedidoDTOTest {
    public static PedidoDTO get(Long id, String name, String cpf){
        return new PedidoDTO(){
            {
                setId(id);
            }
        };
    }
}
