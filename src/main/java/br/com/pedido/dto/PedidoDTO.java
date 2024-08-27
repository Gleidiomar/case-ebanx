package br.com.pedido.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.pedido.domain.Pedido;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PedidoDTO {

	private Long id;
	
	private Long numControle;
	
	private String nameProduto;
	
	private Long valorUniProduto;
	
	private Long qdtProduto;
	
	private Long codCliente;
	
	private double valorTotalProduto;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dtCadastro;
}
