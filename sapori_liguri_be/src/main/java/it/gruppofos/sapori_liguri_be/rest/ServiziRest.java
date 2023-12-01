package it.gruppofos.sapori_liguri_be.rest;

import org.hibernate.Session;

import it.gruppofos.sapori_liguri_be.modelli.HibernateUtil;
import it.gruppofos.sapori_liguri_be.modelli.Pesto;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/menu")
public class ServiziRest {

	@GET
	@Path("pestoligure/{test}")
	@Produces("application/json")
	public Response testConnessione(@PathParam("test") String test) {
		return Response.ok().status(200).build();
	}
	
	@POST
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public Pesto servizioPost2(Pesto p) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		HibernateUtil.getSessionFactory().close();
		
		return p;
	}
}
