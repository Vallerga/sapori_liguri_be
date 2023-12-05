package it.gruppofos.saporiliguri.be.presentation.rest;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import it.gruppofos.saporiliguri.be.business.RicetteBusiness;
import it.gruppofos.saporiliguri.be.db.HibernateUtil;
import it.gruppofos.saporiliguri.be.db.entity.RicettaEntity;
import it.gruppofos.saporiliguri.be.presentation.model.PestoModel;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
	public PestoModel scaricaIngrediente(Integer param) {
		return RicetteBusiness.singoloIngrediente(param);
	}

	@GET
	@Path("/pestoligure/listapesto")
	@Produces("application/json")
	public List<PestoModel> scaricaListaIngredienti() {
		return RicetteBusiness.listaPestoModel();
	}

	@POST
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public Response aggiungiIngrediente(PestoModel p) {
		RicetteBusiness.inserisciIngrediente(p);		
		return Response.ok().status(201).entity(p).build();
	}

	@PUT
	@Path("/pestoligure/modifica")
	@Produces("application/json")
	@Consumes("application/json")
	public RicettaEntity modificaIngrediente(RicettaEntity p) {
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.update(p);
			session.getTransaction().commit();
			session.close();

			return p;
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}

	@DELETE
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public RicettaEntity cancellaIngrediente(RicettaEntity p) {
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(p);
			session.getTransaction().commit();
			session.close();

			return p;
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}
}
