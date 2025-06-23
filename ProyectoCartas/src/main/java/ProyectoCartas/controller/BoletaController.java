package ProyectoCartas.controller;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.service.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public List<Boleta> listarBoletas() {return boletaService.listarBoletas();}

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> buscarBoleta(@PathVariable Integer id){
        Boleta boleta = boletaService.encontrarPorId(id);

        if (boleta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boleta, HttpStatus.OK);
    }

    @GetMapping("/compra/{idCompra}")
    public ResponseEntity<Boleta> buscarCompraPorId(@PathVariable Integer idCompra){
        Boleta boleta = boletaService.buscarCompraPorId(idCompra);
        if (boleta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boleta, HttpStatus.OK);
    }
}
