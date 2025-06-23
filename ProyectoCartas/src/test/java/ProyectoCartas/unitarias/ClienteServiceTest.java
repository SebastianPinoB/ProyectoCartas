package ProyectoCartas.unitarias;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.repository.ClienteRepository;
import ProyectoCartas.service.ClienteService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
    @Autowired
    private ClienteService clienteService;

    @MockitoBean
    private ClienteRepository clienteRepository;

    // El test funciona siempre y cuando el tamaño de la lista sea mayor a 0
    @Test
    public void testFindAll(){
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(1, "21951243", "Benjamin")));

        List<Cliente> clientes = clienteService.listarClientes();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        // assertEquals funciona con un argumento en la posicion 1, el cual es el esperado y el otro es el actual el cual esta siendo usado
    }

    @Test
    public void testFindById(){
        Integer codigo = 1;
        Cliente cliente = new Cliente(codigo, "21951243", "Otro nombre");
        when(clienteRepository.findById(codigo)).thenReturn(Optional.of(cliente));

        Cliente encontrado = clienteService.encontrarClientePorId(codigo);

        assertNotNull(encontrado);
        assertEquals(codigo, encontrado.getIdCliente()); // si la variable codigo y el id del cliente coincide entonces termina correctamente
    }

    @Test
    public void testSaveCliente(){
        Cliente cliente = new Cliente(3, "21951243", "Otro nombre");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente guardado = clienteService.guardarCliente(cliente);
        assertNotNull(guardado);
        assertEquals("asdasdd", guardado.getNombre()); // el primer argumento coincide con el siguiente osea, el nombre del cliente guardado finaliza corretamente
    }

}
