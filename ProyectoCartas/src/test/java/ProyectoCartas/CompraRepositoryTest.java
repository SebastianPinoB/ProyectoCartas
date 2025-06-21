package ProyectoCartas;

import ProyectoCartas.modelo.Compra;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.repository.CompraRepository;
import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Commit
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

class CompraRepositoryTest {
    @Autowired
    private CompraRepository compraRepository;
    private Compra compra;
    @Autowired
    private CartaRepository cartaRepository;
    private Carta carta;
    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    // Registro real
    @BeforeEach
    void seed(){
        Carta carta = new Carta(null, "Charmander", "COD-002", 1200);
        cartaRepository.save(carta);

        Cliente cliente = new Cliente(null, "21458421", "Dola");
        clienteRepository.save(cliente);

        Compra compra = new Compra(null, carta, cliente, LocalDate.now());
        compraRepository.save(compra);
    }

    @Test
    void testById_registroReal(){
        Optional<Compra> compras = compraRepository.findById(compra.getId());
        assertTrue(compras.isPresent());
        assertEquals("Dola", compras.get().getCliente().getNombre());
    }

    @Test
    void testDeleteById_registroReal(){
        compraRepository.deleteById(compra.getId());
    }
}
