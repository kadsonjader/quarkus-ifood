package com.github.kadson.ifood.cadastro;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.kadson.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.kadson.ifood.cadastro.dto.RestauranteMapper;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

	@Inject
	RestauranteMapper restauranteMapper;

    @GET
    @Tag(name = "Restaurantes")
    public List<Restaurante> buscar() {
        return Restaurante.listAll();
    }
    
    @POST
    @Tag(name = "Restaurantes")
    @Transactional
    public Response adicionar(AdicionarRestauranteDTO dto) {
		Restaurante restaurante = restauranteMapper.toRestaurante(dto);
		restaurante.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Tag(name = "Restaurantes")
    @Transactional
    public void adicionar(@PathParam("id") Long id, Restaurante dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante  = restauranteOp.get();
    	restaurante.nome =  dto.nome;
    	restaurante.persist(); 
    }
    
    @DELETE
    @Path("{id}")
    @Tag(name = "Restaurantes")
    @Transactional
    public void adicionar(@PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	
    	restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {throw new NotFoundException();});
    }
    
    
    
   //pratos
    
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "Pratos")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante){
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	return Prato.list("restaurante", restauranteOp.get());
    }
    
    
    @POST
    @Path("{idRestaurante}/pratos")
    @Tag(name = "Pratos")
    @Transactional
    public Response adicionarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Prato prato = new Prato();
    	prato.nome =  dto.nome;
    	prato.descricao = dto.descricao;
    	
    	prato.preco = dto.preco;
    	prato.restaurante = restauranteOp.get();
    	prato.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Tag(name = "Pratos")
    @Transactional
    public void atualizar(@PathParam("idRestaurante") Long idRestaurante, Long id ,Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato não existe");
    	}
    	Prato prato = pratoOp.get();
    	prato.preco = dto.preco;
    	prato.persist();
    }
    
    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Tag(name = "Pratos")
    @Transactional
    public void delete(@PathParam("idRestaurante") Long idRestaurante, Long id, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	pratoOp.ifPresentOrElse(Prato::delete, () -> {
    		throw new NotFoundException();
    	});
    }
    
}