package ProyectoCartas.service;

import ProyectoCartas.model.Rareza;
import ProyectoCartas.repository.RarezaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RarezaService {
    @Autowired
    private RarezaRepository rarezaRepo;

    public List<Rareza> listar(){
        return rarezaRepo.findAll();
    }

    public Rareza crearRareza(Rareza rarezaCreada){
        return rarezaRepo.save(rarezaCreada);
    }

    public Rareza encontrar(Integer id){
        return rarezaRepo.findById(id).orElse(null);
    }

    public void eliminar(Integer id){
        rarezaRepo.deleteById(id);
    }
}
