package ProyectoCartas.assemblers;

import ProyectoCartas.modelo.Cliente;
import ProyectoCartas.controller.ClienteControllerV2;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {
    @Override
    public EntityModel<Cliente> toModel(Cliente cliente) {
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteControllerV2.class).buscarClientePorId(cliente.getIdCliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).listarClientes()).withRel("clientes"));
    }
}
