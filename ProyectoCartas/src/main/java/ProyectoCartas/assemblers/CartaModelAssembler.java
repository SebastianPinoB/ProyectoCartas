package ProyectoCartas.assemblers;

import ProyectoCartas.modelo.Carta;
import ProyectoCartas.controller.CartaControllerV2;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CartaModelAssembler implements  RepresentationModelAssembler<Carta,EntityModel<Carta>> {
    @Override
    public EntityModel<Carta> toModel(Carta carta) {
        return EntityModel.of(carta,
                linkTo(methodOn(CartaControllerV2.class).buscarCartaPorID(carta.getIdCarta())).withSelfRel(),
                linkTo(methodOn(CartaControllerV2.class).listaCartas()).withRel("cartas"));
    }
}
