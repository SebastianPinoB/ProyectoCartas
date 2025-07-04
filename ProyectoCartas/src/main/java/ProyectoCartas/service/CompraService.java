package ProyectoCartas.service;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.repository.BoletaRepository;
import ProyectoCartas.modelo.Stock;
import ProyectoCartas.repository.ClienteRepository;
import ProyectoCartas.repository.CompraRepository;
import ProyectoCartas.repository.CartaRepository;
import ProyectoCartas.repository.StockRepository;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Compra> listarCompras(){
        return compraRepository.findAll();
    }

    // Crear compra forma 1
    public ResponseEntity<?> crearCompra(@RequestBody Compra compra, Cliente cliente) {
        Integer idCarta = compra.getCarta().getIdCarta();
        Integer idCliente = compra.getCliente().getIdCliente();

        Carta carta = cartaRepository.findByIdCarta(idCarta);
        cliente = clienteRepository.findById(idCliente).orElse(null);
        Stock stock = stockRepository.findByCarta(carta);

        if (carta == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la carta :(");
        }
        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró el cliente :(");
        }

        if (stock.getCantidad() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay stock de la carta :(");
        }
        compra.setCarta(carta);
        compra.setCliente(cliente);
        stock.setCantidad(stock.getCantidad() - 1); // posiblemente esto tenga que tener una cantidad especifica y no solo uno.


        this.guardarCompra(compra);
        Boleta boleta = new Boleta();
        boleta.setCompra(compra);
        boletaRepository.save(boleta);

        return ResponseEntity.ok("Compra realizada :). " + "\n Compra ID: " + compra.getId() + "\n Carta comprada: " + compra.getCarta().getNombre()
                + "\n Carta Código Exp: " + compra.getCarta().getCodigoExp() + "\n Cliente ID: " + compra.getCliente().getIdCliente()) ;
    }

    // Crear compra forma 2
    public Compra agregarCompra(Compra compra, Cliente cliente){
        Integer idCarta = compra.getCarta().getIdCarta();
        Integer idCliente = compra.getCliente().getIdCliente();

        Carta carta = cartaRepository.findByIdCarta(idCarta);
        cliente = clienteRepository.findById(idCliente).orElse(null);
        Stock stock = stockRepository.findByCarta(carta);

        if (carta == null) {
            throw new RuntimeException("No se encontro la carta :(");
        }
        if (cliente == null) {
           throw new RuntimeException("No se encontro el cliente :(");
        }

        if (stock.getCantidad() <= 0) {
            throw new RuntimeException("No hay stock de la carta :(");
        }

        compra.setCarta(carta);
        compra.setCliente(cliente);

        stock.setCantidad(stock.getCantidad() - 1);
        stockRepository.save(stock); // posiblemente esto tenga que tener una cantidad especifica y no solo uno.

        this.guardarCompra(compra);

        Boleta boleta = new Boleta();
        boleta.setCompra(compra);
        boletaRepository.save(boleta);

        return compra;
    }

    // Metodo auxiliar para guardar la compra
    public Compra guardarCompra(Compra compra){
        return compraRepository.save(compra);
    }

    public Compra findById(Integer id){
        return compraRepository.findById(id).get();
    }

    public void eliminarCompra(Integer id){
        Compra eliminar = compraRepository.findById(id).orElseThrow(null);
        Boleta boleta = boletaRepository.findBoletaByCompra(eliminar);

        if (boleta != null) {
            boletaRepository.delete(boleta);
        }

        compraRepository.delete(eliminar);
    }
}
