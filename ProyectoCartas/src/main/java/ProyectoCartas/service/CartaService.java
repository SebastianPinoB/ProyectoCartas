package ProyectoCartas.service;

import ProyectoCartas.model.Carta;
import ProyectoCartas.model.TipoCarta;
import ProyectoCartas.service.TipoCartaService;
import ProyectoCartas.model.Rareza;
import ProyectoCartas.service.RarezaService;
import ProyectoCartas.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaService {
    @Autowired
    private CartaRepository cartaRepo;
    @Autowired
    private TipoCartaService tipoCartaServ;
    @Autowired
    private RarezaService rarezaServ;


    public List<Carta> listar(){
        return cartaRepo.findAll();
    }

    public Carta guardarCarta(Carta cartsa, Integer idTipoCarta, Integer idRareza){
        TipoCarta tipoCartaB = tipoCartaServ.encontrarPorId(idTipoCarta); // revisar esta cosa
        Rareza rarezaB = rarezaServ.encontrar(idRareza);

        if (tipoCartaB != null && rarezaB != null){
            cartsa.setRarezaCarta(rarezaB);
            cartsa.setTipoDeCarta(tipoCartaB);
            return cartaRepo.save(cartsa);
        } else {
            return null;
        }
    }

}
