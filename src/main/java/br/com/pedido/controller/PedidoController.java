package br.com.pedido.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedido.domain.Pedido;
import br.com.pedido.dto.PedidoDTO;
import br.com.pedido.dto.RetornoPedidoDTO;
import br.com.pedido.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "Pedido Controller")
@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {
    
	private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
    
    @PostMapping(value = "criar")
    @ApiOperation(value = "Criar novo Pedido")
    public ResponseEntity<RetornoPedidoDTO> createPedido(
            @ApiParam(value = "Objeto pedido", required = true)
            @RequestBody @Valid List<PedidoDTO> listPedido) {
    	
    	return service.createPedido(listPedido);
    }
    
    @GetMapping(value = "listagem")
    @ApiOperation(value = "Listar todo os Pedidos by numPedido or dtCadastro")
    public ResponseEntity<List<PedidoDTO>> getAllPaginated(
    		@RequestParam(value = "todos", required = false) String todos,
            @RequestParam(value = "numPedido", required = false) Long numPedido,
            @RequestParam(value = "dtCadastro", required = false) String dtCadastro){
    	
        return service.getAllPedido(todos, numPedido, dtCadastro);
    }   
}
