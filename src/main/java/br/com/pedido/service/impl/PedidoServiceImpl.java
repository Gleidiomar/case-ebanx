package br.com.pedido.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.pedido.domain.Pedido;
import br.com.pedido.domain.Cliente;
import br.com.pedido.dto.PedidoDTO;
import br.com.pedido.dto.RetornoPedidoDTO;
import br.com.pedido.mapper.PedidoMapper;
import br.com.pedido.repository.ClienteRepository;
import br.com.pedido.repository.PedidoRepository;
import br.com.pedido.service.PedidoService;

@Service
public class PedidoServiceImpl  extends GenericServiceImpl<Pedido, Long> implements PedidoService {

	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);
	
	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private ClienteRepository repositoryCliente;
	
	ModelMapper model = new ModelMapper();
	
	private final PedidoMapper pedidoMapper = new PedidoMapper();	

	@Override
	public ResponseEntity<RetornoPedidoDTO> createPedido(@Valid List<PedidoDTO> listPedido) {

		RetornoPedidoDTO retorno = new RetornoPedidoDTO();
		
		List<String> criticas = validarCamposPedido(listPedido);
		
		boolean possuiCriticas = !criticas.isEmpty();
		
		if(!possuiCriticas) {
			
			validarAlgunsCasos(listPedido);
			retorno.setMsg("Cadastro dos pedidos realizado com sucesso");
			return ResponseEntity.ok(retorno);
		}
		
		if(possuiCriticas) {
			retorno.setTemErro(true);
			retorno.setCriticas(criticas);
			retorno.setMsg("Erro no cadastro do pedido");
			
			return ResponseEntity.ok(retorno);
		}
		return ResponseEntity.ok(retorno);
	}

	private List<String> validarCamposPedido(@Valid List<PedidoDTO> listPedido) {
		
		List<String> criticasPedido = new ArrayList<>();
		
		criticasPedido = validarLimite(listPedido, criticasPedido);
		
		criticasPedido = validarNumControle(listPedido, criticasPedido);
		
		criticasPedido = validarCliente(listPedido, criticasPedido);
		
		return criticasPedido;
	}

	private List<String> validarLimite(@Valid List<PedidoDTO> listPedido, List<String> criticasPedido) {
		
		if(listPedido.size() > 10) {
			criticasPedido.add("Não pode ultrapassar limite de 10 pedidos");
		}
		return criticasPedido;
	}

	private List<String> validarNumControle(@Valid List<PedidoDTO> listPedido, List<String> criticasPedido) {
		
		for(int i=0; i < listPedido.size(); i++) {
			
			Pedido exitPedido = repository.findByNumControle(listPedido.get(i).getNumControle());
			if(exitPedido != null) {
				criticasPedido.add("Numero controle "+exitPedido.getNumControle()+" já cadastrado");
			}
		}
		
		return criticasPedido;
	}	

	private List<String> validarCliente(@Valid List<PedidoDTO> listPedido, List<String> criticasPedido) {
		
		for(int i=0; i < listPedido.size(); i++) {
			
			Cliente exitCliente = repositoryCliente.findFirstById(listPedido.get(i).getCodCliente());
			if(exitCliente == null) {
				criticasPedido.add("Codigo cliente "+listPedido.get(i).getCodCliente()+" não cadastrado");
			}
		}
		
		return criticasPedido;
	}

	private void validarAlgunsCasos(@Valid List<PedidoDTO> listPedido) {

		for(int i=0; i < listPedido.size(); i++) {
			
			listPedido.get(i).setDtCadastro(listPedido.get(i).getDtCadastro() != null ? listPedido.get(i).getDtCadastro() : new Date());
			listPedido.get(i).setQdtProduto(listPedido.get(i).getQdtProduto() != null ? listPedido.get(i).getQdtProduto() : 1);
			
			double vlTotal = Double.parseDouble(listPedido.get(i).getValorUniProduto().toString()) * Double.parseDouble(listPedido.get(i).getQdtProduto().toString());
			
			if(listPedido.get(i).getQdtProduto() > 5 && listPedido.get(i).getQdtProduto() <= 10) {
				double vlPercentual = 5;
				double vlrDesconto = (vlPercentual * vlTotal)/100;
				vlTotal =  vlTotal - vlrDesconto;
			}
			
			if(listPedido.get(i).getQdtProduto() > 10) {
				double vlPercentual = 10;
				double vlrDesconto = (vlPercentual * vlTotal)/100;
				vlTotal =  vlTotal - vlrDesconto;
			}
			
			listPedido.get(i).setValorTotalProduto(vlTotal);
		}
		
		saveNaBase(listPedido);
	}
	
	private void saveNaBase(@Valid List<PedidoDTO> listPedido) {

		List<Pedido> listEntity = pedidoMapper.convertToListEntity(listPedido);		
		
		repository.saveAll(listEntity);
	}	

	@Override
	public ResponseEntity<List<PedidoDTO>> getAllPedido(String todos, Long numPedido, String dtCadastro) {
        
		List<Pedido> listEntity = null;
		List<PedidoDTO> listDTO= null;
		
		if(numPedido != null) {
			listEntity = repository.findAllById(numPedido);
			listDTO = pedidoMapper.convertToListDTO(listEntity);
		}
		
		if(dtCadastro != null) {
			
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Date dataCadastro;
			try {
				dataCadastro = sdf.parse(dtCadastro);
				listEntity = repository.findAllByDtCadastro(dataCadastro);
				listDTO = pedidoMapper.convertToListDTO(listEntity);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		if(numPedido == null && dtCadastro == null) {
			listEntity = repository.findAll();
			listDTO = pedidoMapper.convertToListDTO(listEntity);
		}
				
		return ResponseEntity.ok(listDTO);
	}
}