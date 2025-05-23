package ProyectoCartas.service;

import ProyectoCartas.model.Carta;
import ProyectoCartas.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaService {
    @Autowired
    private CartaRepository cartaRepo;

    public List<Carta> listar(){
        return cartaRepo.findAll();
    }

}
