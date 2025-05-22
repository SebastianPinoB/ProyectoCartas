package ProyectoCartas.controller;

import ProyectoCartas.model.TipoCarta;
import ProyectoCartas.service.TipoCartaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo_cartas")
public class TipoCartaController {
    @Autowired
    private TipoCartaService serviceC;

    @GetMapping
    public List<TipoCarta> listarTipoCarta(){
        return serviceC.listar();
    }

    @PostMapping
    public TipoCarta guardarTipoCarta(@RequestBody TipoCarta tipoCartaC){
        return serviceC.crear(tipoCartaC);
    }
}
