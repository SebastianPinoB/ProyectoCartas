package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.repository.CompraRepository;
import ProyectoCartas.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CartaRepository cartaRepository;

    public List<Compra> listarCompras(){
        return compraRepository.findAll();
    }

    public Compra crearCompra(Compra compra) {
        Integer idCarta = compra.getCarta().getIdCarta();

        Carta carta = cartaRepository.findByIdCarta(idCarta);

        if (carta.getIdCarta() == null) {
            throw new RuntimeException("Carta no encontrada");
        }

        compra.setCarta(carta);
        return compraRepository.save(compra);
    }

    public Compra guardarCompra(Compra compra){
        return compraRepository.save(compra);
    }

    public Compra findById(Integer id){
        return compraRepository.findById(id).get();
    }

    public void eliminarCompra(Integer id){
        compraRepository.deleteById(id);
    }
}
