package br.com.pedido.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.pedido.domain.Pedido;
import br.com.pedido.dto.PedidoDTO;
import br.com.pedido.factory.CreatePedidoDTOTest;
import br.com.pedido.repository.PedidoRepository;
import br.com.pedido.service.impl.PedidoServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class PedidoServiceTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
    @InjectMocks
    private PedidoServiceImpl service;
    
    @Mock
	private PedidoRepository associadoRepository;
    
    ModelMapper model = new ModelMapper();

}
