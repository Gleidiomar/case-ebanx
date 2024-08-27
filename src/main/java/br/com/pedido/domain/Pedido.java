package br.com.pedido.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "num_Controle")
	private Long numControle;
	
	@Column(name = "name_Produto")
	private String nameProduto;
	
	@Column(name = "valor_Uni_Produto")
	private Long valorUniProduto;
	
	@Column(name = "qdt_Produto")
	private Long qdtProduto;
	
	@Column(name = "cod_Cliente")
	private Long codCliente;
	
	@Column(name = "valor_Total_Produto")
	private double valorTotalProduto;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@Column(name = "dt_Cadastro")
	private Date dtCadastro;
}
