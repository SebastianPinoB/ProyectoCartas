package ProyectoCartas.assemblers;

import ProyectoCartas.controller.BoletaController;
import ProyectoCartas.modelo.Compra;
import ProyectoCartas.controller.CompraControllerV2;
import ProyectoCartas.controller.CartaControllerV2;
import ProyectoCartas.controller.ClienteControllerV2;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CompraModelAssembler implements RepresentationModelAssembler<Compra, EntityModel<Compra>> {
    @Override
    public EntityModel<Compra> toModel(Compra compra){
        return EntityModel.of(compra,
                linkTo(methodOn(CompraControllerV2.class).buscarCompra(compra.getId())).withSelfRel(),
                linkTo(methodOn(CompraControllerV2.class).listaCompras()).withRel("compras"),

                linkTo(methodOn(CartaControllerV2.class).buscarCartaPorID(compra.getCarta().getIdCarta())).withRel("carta"),

                linkTo(methodOn(ClienteControllerV2.class).buscarClientePorId(compra.getCliente().getIdCliente())).withRel("cliente"),

                linkTo(methodOn(BoletaController.class).buscarCompraPorId(compra.getId())).withRel("boleta")
        );
    }
}
