package ProyectoCartas.unitarias;

import ProyectoCartas.repository.BoletaRepository;
import ProyectoCartas.service.BoletaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@ActiveProfiles("test")
public class BoletaServiceTest {
    @Autowired
    private BoletaService service;

    @MockitoBean
    private BoletaRepository boletaRepository;



}
