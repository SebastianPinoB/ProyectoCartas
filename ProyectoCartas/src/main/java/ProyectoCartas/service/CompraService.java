package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.repository.CompraRepository;
import ProyectoCartas.repository.CartaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public ResponseEntity<?> crearCompra(@RequestBody Compra compra) {
        Integer idCarta = compra.getCarta().getIdCarta();

        Carta carta = cartaRepository.findByIdCarta(idCarta);

        if (carta == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la carta :(");
        }
        compra.setCarta(carta);
        this.guardarCompra(compra);
        return ResponseEntity.ok("Carta existe :)");
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
