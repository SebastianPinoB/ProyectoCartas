package ProyectoCartas.unitarias;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.modelo.Cliente;

import ProyectoCartas.repository.CompraRepository;
import ProyectoCartas.service.CompraService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class CompraServiceTest {

    @Autowired
    private CompraService compraService;

    @MockitoBean
    private CompraRepository compraRepository;

    @Test
    public void testFindAll(){
        Compra compra = new Compra();
        when(compraRepository.findAll()).thenReturn(List.of(compra));

        List<Compra> compras = compraService.listarCompras();
        assertNotNull(compras);
        assertEquals(1, compras.size());
    }

    @Test
    public void testFindById(){
        Integer id = 1;
        Compra compra = new Compra();
        when(compraRepository.findById(id)).thenReturn(Optional.of(compra));

        Compra encontrado = compraService.findById(id);
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
    }

    // Toma el metodo de esta clase para poder guardad una compra y verificar su funcionalidad
    @Test
    public void testSave(){
        Compra compra = crearCompra();
        when(compraRepository.save(compra)).thenReturn(compra);

        Compra guarda = compraService.guardarCompra(compra);
        assertNotNull(guarda);
        assertEquals(1, guarda.getCliente()); // pueden jugar con el equals
    }

    @Test
    public void testDeleteById(){
        Integer id = 1;
        Compra compra = new Compra(); // esto simplemente un objeto vacia, no afecta en nada al ser un test
        doNothing().when(compraRepository).deleteById(id);

        compraService.eliminarCompra(id);
        verify(compraRepository, times(1)).deleteById(id);
    }

    // Se crea un metodo de crear para probar el metodo de guardar compra
    private Compra crearCompra(){
        Carta carta = new Carta();
        carta.setIdCarta(1);
        carta.setNombre("Dragon de ojos azules");
        carta.setCodigoExp("COD-01");
        carta.setPrecio(100000);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setRun("2197421-2");
        cliente.setNombre("Armando Casas");

        Compra compra = new Compra();
        compra.setId(1);
        compra.setCarta(carta);
        compra.setCliente(cliente);
        compra.setFecha(LocalDate.now());

        Boleta boleta = new Boleta();
        boleta.setId(1);
        boleta.setCompra(compra);

        return compra;
    }
}
