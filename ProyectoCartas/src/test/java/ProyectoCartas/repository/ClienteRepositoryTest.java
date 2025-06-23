package ProyectoCartas.repository;

import ProyectoCartas.modelo.Cliente;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class ClienteRepositoryTest {
    @Autowired
    private ClienteRepository clienteRepository;
    private Cliente clienteGuardado;

    @BeforeEach
    void seed(){
        clienteGuardado = clienteRepository.save(new Cliente(null, "21458421", "Pablo"));
    }

    @Test
    void findbyId(){
        Optional<Cliente> cliente = clienteRepository.findById(clienteGuardado.getIdCliente());

        assertTrue(cliente.isPresent());
        assertEquals("Pablo", cliente.get().getNombre());
    }

    @Test
    void deletebyId(){
        clienteRepository.deleteById(clienteGuardado.getIdCliente());
    }
}
