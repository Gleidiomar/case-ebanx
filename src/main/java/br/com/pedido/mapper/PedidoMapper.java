package br.com.pedido.mapper;

import br.com.pedido.domain.Pedido;
import br.com.pedido.dto.PedidoDTO;

public class PedidoMapper implements GenericMapper<Pedido, PedidoDTO>{

	@Override
	public PedidoDTO convertToDTO(Pedido entity) {
		PedidoDTO dto = new PedidoDTO();
		dto.setId(entity.getId());
		dto.setNumControle(entity.getNumControle());
		dto.setNameProduto(entity.getNameProduto());
		dto.setValorUniProduto(entity.getValorUniProduto());
		dto.setQdtProduto(entity.getQdtProduto());
		dto.setCodCliente(entity.getCodCliente());
		dto.setDtCadastro(entity.getDtCadastro());
		dto.setValorTotalProduto(entity.getValorTotalProduto());
		
		return dto;
	}

	@Override
	public Pedido convertToEntity(PedidoDTO dto) {
		Pedido entity = new Pedido();
		entity.setId(dto.getId());
		entity.setNumControle(dto.getNumControle());
		entity.setNameProduto(dto.getNameProduto());
		entity.setValorUniProduto(dto.getValorUniProduto());
		entity.setQdtProduto(dto.getQdtProduto());
		entity.setCodCliente(dto.getCodCliente());
		entity.setDtCadastro(dto.getDtCadastro());
		entity.setValorTotalProduto(dto.getValorTotalProduto());
		
		return entity;
	}

}
