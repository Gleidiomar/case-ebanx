package br.com.pedido.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class RetornoPedidoDTO {
	
	private List<String> criticas = new ArrayList<String>();
	private boolean temErro = false;
	private String msg;
}
