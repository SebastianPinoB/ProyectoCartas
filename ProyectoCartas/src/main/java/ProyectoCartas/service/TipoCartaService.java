package ProyectoCartas.service;

import ProyectoCartas.model.TipoCarta;
import ProyectoCartas.repository.TipoCartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCartaService {
    @Autowired
    private TipoCartaRepository tipoCartaRepo;

    public List<TipoCarta> listar(){
        return tipoCartaRepo.findAll();
    }

    public TipoCarta crear(TipoCarta tipoCarta){
        return tipoCartaRepo.save(tipoCarta);
    }

    public TipoCarta encontrarPorId(Integer id){
        return tipoCartaRepo.findById(id).orElse(null);
    }

    public void eliminar(Integer id){
        tipoCartaRepo.deleteById(id);
    }
}
