package it.gruppofos.sapori_liguri_be.rest;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import it.gruppofos.sapori_liguri_be.backend.db.HibernateUtil;
import it.gruppofos.sapori_liguri_be.modelli.Pesto;
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
public class ServiziRest {
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
	public Pesto scaricaIngrediente(Pesto ingrediente) {
		Integer idIngrediente = ingrediente.getId();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Pesto result = session.find(Pesto.class, idIngrediente);
			System.out.println("idIngrediente: " + idIngrediente);
			session.getTransaction().commit();
			session.close();
			return result;
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}

	@GET
	@Path("/pestoligure/listapesto")
	@Produces("application/json")
	public List<Pesto> scaricaListaIngredienti() {		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Pesto> criteria = builder.createQuery(Pesto.class);
			criteria.from(Pesto.class);
			List<Pesto> data = session.createQuery(criteria).getResultList();
			session.getTransaction().commit();
			session.close();

			return data;
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}

	@POST
	@Path("/pestoligure/")
	@Produces("application/json")
	@Consumes("application/json")
	public Pesto aggiungiIngrediente(Pesto p) {
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(p);
			session.getTransaction().commit();
			session.close();

			return p;
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}
	
	@PUT
	@Path("/pestoligure/modifica")
	@Produces("application/json")
	@Consumes("application/json")
	public Pesto modificaIngrediente(Pesto p) {
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
	public Pesto cancellaIngrediente(Pesto p) {
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
