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

    //Poblar sin hacer Endpoint
    @PostConstruct
    public void poblarCarta(){
        this.clienteRepo.save(new Cliente(null,"29382365-1","Jorge Pereira"));
        this.clienteRepo.save(new Cliente(null,"26382368-7","John Doe"));
        this.clienteRepo.save(new Cliente(null,"23882361-2","Eduardo Fonsalba"));
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
