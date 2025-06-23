package ProyectoCartas;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.service.CartaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class CartaServiceTest {

    @Autowired
    private CartaService cartaService;

    @MockitoBean
    private CartaRepository cartaRepository;

    @Test
    public void testFindAll(){
        when(cartaRepository.findAll()).thenReturn(List.of(new Carta(1, "Pikachu Dorado", "COD-012", 150000)));

        List<Carta> cartas = cartaService.listarCartas();

        assertNotNull(cartas);
        assertEquals(1, cartas.size());
    }

    @Test
    public void testFindById(){
        Integer idCarta = 1;
        Carta carta = new Carta(idCarta, "Pikachu Plateado", "COD-020", 175000);
        when(cartaRepository.findById(idCarta)).thenReturn(Optional.of(carta));

        Carta encontrado = cartaService.findById(idCarta);
        assertNotNull(encontrado);
        assertEquals(idCarta, encontrado.getIdCarta());
    }

    @Test
    public void testSaveCarta(){
        Carta carta = new Carta(2, "Pikachu Opal", "COD-017", 120000);
        when(cartaRepository.save(carta)).thenReturn(carta);

        Carta guardado = cartaService.guardarCarta(carta);
        assertNotNull(guardado);
        assertEquals("COD-017", guardado.getCodigoExp());
    }
}
