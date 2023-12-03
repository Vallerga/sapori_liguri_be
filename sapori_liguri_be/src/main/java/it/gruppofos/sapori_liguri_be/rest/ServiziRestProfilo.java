package it.gruppofos.sapori_liguri_be.rest;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import it.gruppofos.sapori_liguri_be.backend.db.HibernateUtil;
import it.gruppofos.sapori_liguri_be.modelli.Profilo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
@Path("/home")
public class ServiziRestProfilo {
	static Session session = null;

	@GET
	@Path("login/{test}")
	@Produces("application/json")
	public Response testConnessione(@PathParam("test") String test) {
		return Response.ok().status(200).build();
	}

	@GET
	@Path("/login/profilo/")
	@Produces("application/json")
	@Consumes("application/json")
	public Profilo emailPresente(Profilo p) {
		Integer idemail = p.getId();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Profilo result = session.find(Profilo.class, idemail);//.contains(profilo);
			System.out.println("idemail: " + idemail);
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
	@Path("/login/listaprofili/")
	@Produces("application/json")
	public List<Profilo> listaProfili() {		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Profilo> criteria = builder.createQuery(Profilo.class);
			criteria.from(Profilo.class);
			List<Profilo> data = session.createQuery(criteria).getResultList();
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
	@Path("/iscriviti/")
	@Produces("application/json")
	@Consumes("application/json")
	public Profilo aggiungiProfilo(Profilo p) {
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
	@Path("/iscriviti/")
	@Produces("application/json")
	@Consumes("application/json")
	public Profilo modificaProfilo(Profilo p) {
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
	@Path("/iscriviti/")
	@Produces("application/json")
	@Consumes("application/json")
	public Profilo cancellaProfilo(Profilo p) {
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.delete(p);
			session.getTransaction().commit();
			session.close();

			return p;//Response.ok().build();
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}
}
