package it.gruppofos.saporiliguri.be.presentation.rest;

import java.util.List;

import org.hibernate.Session;

import it.gruppofos.saporiliguri.be.business.RicetteBusiness;
import it.gruppofos.saporiliguri.be.db.entity.EntityRicetta1;
import it.gruppofos.saporiliguri.be.presentation.model.PojoRicetta;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/menu")
public class RicettaRest {
	static Session session = null;

	@GET
	@Path("pestoligure/{test}")
	@Produces("application/json")
	public Response testConnessione(@PathParam("test") String test) {
		return Response.ok().status(200).build();
	}

	@GET
	@Path("/pestoligure/ingrediente")
	@Produces("application/json")
	@Consumes("application/json")
	public PojoRicetta scaricaIngrediente(Integer param) {
		return RicetteBusiness.singoloIngrediente(param);
	}

	@GET
	@Path("/pestoligure/listapesto")
	@Produces("application/json")
	public List<PojoRicetta> scaricaListaIngredienti() {
		return RicetteBusiness.listaPestoModel();
	}

	@POST
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public Response aggiungiIngrediente(PojoRicetta p) {
		RicetteBusiness.inserisciIngrediente(p);
		return Response.ok().status(201).entity(p).build();
	}

	@PUT
	@Path("/pestoligure/modifica")
	@Produces("application/json")
	@Consumes("application/json")
	public Response modificaIngrediente(PojoRicetta p) {
		RicetteBusiness.modificaIngrediente(p);
		return Response.ok().status(201).entity(p).build();
	}

	@DELETE
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public Response cancellaIngrediente(PojoRicetta p) {
		RicetteBusiness.eliminaIngrediente(p);
		return Response.ok().status(200).entity(p).build();
	}
	
	@DELETE 
	@Path("/pestoligure2/{indice}")
	@Produces("application/json")
	@Consumes("application/json")

	public Response cancellaIngredientebyId(@QueryParam("indice") Integer paramId) {
		System.out.println("paramId: " + paramId);
		EntityRicetta1 ingredienteEliminato = RicetteBusiness.eliminaIngredienteId(paramId);
		return Response.ok().status(201).entity(ingredienteEliminato).build();
	}
}

