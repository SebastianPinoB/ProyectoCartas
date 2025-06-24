package ProyectoCartas.controller;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/boletas")
@Tag(name = "Boletas", description = "Generación de boletas")
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    @Operation(summary = "Obtener las boletas generadas", description = "Obtener todas las boletas generadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "DB Vacia")
    })
    public List<Boleta> listarBoletas() {return boletaService.listarBoletas();}

    @GetMapping("/{id}")
    @Operation(summary = "Buscar boleta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada")
    })
    public ResponseEntity<Boleta> buscarBoleta(@PathVariable Integer id){
        Boleta boleta = boletaService.encontrarPorId(id);

        if (boleta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boleta, HttpStatus.OK);
    }

    @GetMapping("/compra/{idCompra}")
    @Operation(summary = "Buscar compra por ID por medio de la boleta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Compra no encontrada en la boleta")
    })
    public ResponseEntity<Boleta> buscarCompraPorId(@PathVariable Integer idCompra){
        Boleta boleta = boletaService.buscarCompraPorId(idCompra);
        if (boleta == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(boleta, HttpStatus.OK);
    }
}
