package ProyectoCartas.service;

import ProyectoCartas.model.Carta;
import ProyectoCartas.model.TipoCarta;
import ProyectoCartas.model.Rareza;
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

    public Carta guardarCarta(Carta carta){
        Rareza rareza = rarezaServ.encontrar(carta.getRarezaCarta().getIdRareza());
        TipoCarta tipoCarta = tipoCartaServ.encontrarPorId(carta.getTipoDeCarta().getIdTipoCarta());

        carta.setTipoDeCarta(tipoCarta);
        carta.setRarezaCarta(rareza);
        return cartaRepo.save(carta);
    }
}
