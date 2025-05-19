package ProyectoCartas.service;

import ProyectoCartas.modelo.Compra;
import ProyectoCartas.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> listarCompras(){
        return compraRepository.findAll();
    }
    /*
    public List<Compra> listarComprasPorCliente(Integer idCliente){
        return compraRepository.findAllById(idCliente);
    }
     */
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
