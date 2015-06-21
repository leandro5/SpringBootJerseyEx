package net.leandroabreu.springbootjerseyex;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

@Component
@Path("/contato")
@Produces(MediaType.APPLICATION_JSON)
public class ContatoController {
	
	@Inject
	private ContatoRepository repo;
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	public List<Contato> getAll() {
		return (List<Contato>) repo.findAll();
	}
	
	@GET
	@Path("{id}")
	public Contato findOne(@PathParam("id") long id) {
		return repo.findOne(id);
	}
	
	@POST
	public Response save(Contato contato){
		contato = repo.save(contato);
		
		URI location = uriInfo.getAbsolutePathBuilder()
				.path("{id}")
				.resolveTemplate("id", contato.getId())
				.build();
				return Response.created(location).build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		
		repo.delete(id);
		return Response.accepted().build();
	}
	
	@PUT
	@Path("{id}")
	public Contato update(@PathParam("id") long id, Contato contato){
		Contato update = repo.findOne(id);
		
		update.setNome(contato.getNome());
		update.setEmail(contato.getEmail());
		
		return repo.save(update);
	}
	
}


