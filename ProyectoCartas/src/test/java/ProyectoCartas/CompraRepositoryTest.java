package ProyectoCartas;

import ProyectoCartas.modelo.Compra;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.modelo.Boleta;

import ProyectoCartas.repository.CompraRepository;
import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.repository.ClienteRepository;
import ProyectoCartas.repository.BoletaRepository;

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
    private Compra compraGuardada;

    @Autowired
    private CartaRepository cartaRepository;
    private Carta cartaGuardada;

    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente clienteGuardada;

    @Autowired
    private BoletaRepository boletaRepository;
    private Boleta boletaGuardada;

    // Registro real
    @BeforeEach
    void seed(){
        cartaGuardada = new Carta(null, "Charmander", "COD-002", 1200);
        cartaRepository.save(cartaGuardada);

        clienteGuardada = new Cliente(null, "21458421", "Dola");
        clienteRepository.save(clienteGuardada);

        compraGuardada = new Compra(null, cartaGuardada, clienteGuardada, LocalDate.now());
        compraRepository.save(compraGuardada);

        boletaGuardada = new Boleta(null, compraGuardada);
        boletaRepository.save(boletaGuardada);
    }

    @Test
    void testById_registroReal(){
        Optional<Compra> compras = compraRepository.findById(compraGuardada.getId());
        assertTrue(compras.isPresent());
        assertEquals("Dola", compras.get().getCliente().getNombre());
    }

    @Test
    void testDeleteById_registroReal(){
        compraRepository.deleteById(compraGuardada.getId());
    }
}
