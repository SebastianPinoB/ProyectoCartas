package ProyectoCartas.service;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.repository.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;

    public List<Cliente> listarClientes(){
        return clienteRepo.findAll();
    }

    public Cliente guardarCliente(Cliente cliente){
        return clienteRepo.save(cliente);
    }

    public Cliente encontrarClientePorId(Integer id){
        return clienteRepo.findById(id).orElse(null);
    }

    public void eliminarCliente(Integer id){
        clienteRepo.deleteById(id);
    }

}
