package br.com.pedido.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pedido.domain.Pedido;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {

	Pedido findByNumControle(Long numControle);

	List<Pedido> findAllById(Long numPedido);

	List<Pedido> findAllByDtCadastro(Date dtCadastro);

}
