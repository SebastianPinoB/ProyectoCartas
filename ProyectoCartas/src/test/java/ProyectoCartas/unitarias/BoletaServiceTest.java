package ProyectoCartas.unitarias;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.repository.BoletaRepository;
import ProyectoCartas.service.BoletaService;

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
public class BoletaServiceTest {
    @Autowired
    private BoletaService service;

    @MockitoBean
    private BoletaRepository boletaRepository;

    @Test
    public void testFindAll(){
        Boleta boleta = new Boleta();
        when(boletaRepository.findAll()).thenReturn(List.of(boleta));

        List<Boleta> boletas = service.listarBoletas();
        assertNotNull(boletas);
        assertEquals(1, boletas.size());
    }

    @Test
    public void testFindById(){
        Integer id = 1;
        Boleta boleta = new Boleta();
        when(boletaRepository.findById(id)).thenReturn(Optional.of(boleta));

        Boleta encontrado = service.encontrarPorId(id);
        assertNotNull(encontrado);
        assertEquals(id, encontrado.getId());
    }

    @Test
    public void testSave(){
        Boleta boleta = crearBoleta();
        when(boletaRepository.save(boleta)).thenReturn(boleta);

        Boleta guardar = service.guardarBoleta(boleta);

        assertNotNull(guardar);
        assertEquals("Fabio", guardar.getCompra().getCliente().getNombre());
    }

    @Test
    public void testDeleteById(){
        Integer id = 1;
        Boleta boleta = new Boleta();

        doNothing().when(boletaRepository).deleteById(id);
        service.eliminarBoletaPorId(id);
        verify(boletaRepository, times(1)).deleteById(id);
    }

    private Boleta crearBoleta(){
        Carta carta = new Carta();
        carta.setNombre("Mew Plateado");
        carta.setCodigoExp("COD-0122");
        carta.setPrecio(120853);

        Cliente cliente = new Cliente();
        cliente.setRun("1294721-2");
        cliente.setNombre("Fabio");

        Compra compra = new Compra();
        compra.setCarta(carta);
        compra.setCliente(cliente);
        compra.setFecha(LocalDate.now());

        Boleta boleta = new Boleta(null, compra);

        return boleta;
    }
}
