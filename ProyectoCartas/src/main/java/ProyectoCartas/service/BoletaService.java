package ProyectoCartas.service;

import ProyectoCartas.modelo.Boleta;
import ProyectoCartas.repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Boleta> listarBoletas() { return boletaRepository.findAll();}

    public ResponseEntity<?> buscarBoletaPorId(Integer id) {

        Boleta boleta = boletaRepository.findById(id).orElse(null);

        if (boleta == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se encontró la boleta :(");
        }
        return ResponseEntity.ok(
                "ID boleta: " + boleta.getId() +
                        "\nID compra: " + boleta.getCompra().getId() +
                        "\nID carta: " + boleta.getCompra().getCarta().getIdCarta() +
                        "\nNombre carta: " + boleta.getCompra().getCarta().getNombre() +
                        "\nPrecio carta: " + boleta.getCompra().getCarta().getPrecio() +
                        "\nID cliente: " + boleta.getCompra().getCliente().getIdCliente() +
                        "\nRun cliente: " + boleta.getCompra().getCliente().getRun() +
                        "\nNombre cliente: " + boleta.getCompra().getCliente().getNombre()

        );
    }

    // alternativo -- para las pruebas unitarias
    public Boleta encontrarPorId(Integer id){
        return boletaRepository.findById(id).orElse(null);
    }

}
