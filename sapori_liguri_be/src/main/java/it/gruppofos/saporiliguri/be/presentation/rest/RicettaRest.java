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
import jakarta.ws.rs.WebApplicationException;
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

//	@GET
//	@Path("/pestoligure/ingrediente")
//	@Produces("application/json")
//	@Consumes("application/json")
//	public PojoRicetta scaricaIngrediente(Integer param) {
//		return RicetteBusiness.singoloIngrediente(param);
//	}

	@GET
	@Path("/pestoligure/ingrediente")
	@Produces("application/json")
	@Consumes("application/json")
	public Response scaricaIngrediente(Integer indiceIngrediente) {
		try {
			PojoRicetta result = RicetteBusiness.prendiConIndice(indiceIngrediente);
			return Response.ok(result).status(200).build();
		} catch (WebApplicationException e) {
			return Response.ok().status(404, "ELEMENT NOT FOUND").build();
		}
	}

	@GET
	@Path("/pestoligure/listapesto")
	@Produces("application/json")
	public Response scaricaListaIngredienti() {
		try {
			List<PojoRicetta> lista = RicetteBusiness.listaPestoModel();
			return Response.ok(lista).status(200).build();
		} catch (WebApplicationException e) {
			return Response.ok().status(404, "LIST NOT FOUND").build();
		}
	}

	@POST
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public Response aggiungiIngrediente(PojoRicetta ingredienteNuovo) {
		try {
			RicetteBusiness.inserisciIngrediente(ingredienteNuovo);
			return Response.ok(ingredienteNuovo).status(201).build();
		} catch (WebApplicationException e) {
			return Response.ok().status(406, "NOT ACCEPTABLE").build();
		}
	}

	@PUT
	@Path("/pestoligure/modifica/{indice}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response modificaIngrediente(PojoRicetta modificaIngrediente, @QueryParam("indice") Integer paramId) {
		try {
			PojoRicetta result = RicetteBusiness.modificaIngrediente(modificaIngrediente, paramId);
			return Response.ok(result).status(201).build();
		} catch (WebApplicationException e) {
			return Response.ok().status(e.getResponse().getStatus(), "NOT MODIFIED").build();
		}
	}

	@DELETE
	@Path("/pestoligure2/{indice}")
	@Produces("application/json")
	@Consumes("application/json")

	public Response cancellaIngredienteConIndice(@QueryParam("indice") Integer paramId) {
		System.out.println("paramId: " + paramId);
		EntityRicetta1 ingredienteEliminato = RicetteBusiness.eliminaConIndice(paramId);
		return Response.ok().status(201).entity(ingredienteEliminato).build();
	}
}
