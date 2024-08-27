package br.com.pedido.service;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.pedido.domain.Pedido;
import br.com.pedido.dto.PedidoDTO;
import br.com.pedido.dto.RetornoPedidoDTO;

public interface PedidoService extends GenericService<Pedido, Long>{

	ResponseEntity<RetornoPedidoDTO> createPedido(@Valid List<PedidoDTO> listPedido);

	List<Pedido> getAllPedido(String todos, Long numPedido, String dtCadastro);
}
