package ProyectoCartas;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Stock;

import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.repository.StockRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Commit
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

class CartaRepositoryTest {
    @Autowired
    private CartaRepository cartaRepository;
    private Carta cartaGuardada;

    @Autowired
    private StockRepository stockRepository;
    private Stock stockGuardada;

    @BeforeEach
    void seed(){
        cartaRepository.deleteAll();
        stockRepository.deleteAll();

        cartaGuardada = new Carta();
        cartaGuardada.setIdCarta(1);
        cartaGuardada.setNombre("Lucario Plateado");
        cartaGuardada.setCodigoExp("COD-0100");
        cartaGuardada.setPrecio(235000);

        cartaRepository.save(cartaGuardada);

        stockGuardada = new Stock();
        stockGuardada.setIdStock(1);
        stockGuardada.setCarta(cartaGuardada);

        stockRepository.save(stockGuardada);
    }

    @Test
    void testFindByIdCarta_registroReal(){
        Optional<Carta> cartas = cartaRepository.findById(cartaGuardada.getIdCarta());

        assertTrue(cartas.isPresent());
        assertEquals("COD-0100", cartas.get().getCodigoExp());
    }

    @Test
    void testDeleteByIdCarta_registroReal(){
        cartaRepository.deleteById(cartaGuardada.getIdCarta());
    }
}
