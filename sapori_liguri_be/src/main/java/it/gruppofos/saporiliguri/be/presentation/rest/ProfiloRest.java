package it.gruppofos.saporiliguri.be.presentation.rest;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import it.gruppofos.saporiliguri.be.db.HibernateUtil;
import it.gruppofos.saporiliguri.be.db.entity.ProfiloEntity;
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
public class ProfiloRest {
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
	public ProfiloEntity emailPresente(ProfiloEntity p) {
		Integer idemail = p.getId();

		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			ProfiloEntity result = session.find(ProfiloEntity.class, idemail);//.contains(profilo);
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
	public List<ProfiloEntity> listaProfili() {		
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ProfiloEntity> criteria = builder.createQuery(ProfiloEntity.class);
			criteria.from(ProfiloEntity.class);
			List<ProfiloEntity> data = session.createQuery(criteria).getResultList();
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
	public ProfiloEntity aggiungiProfilo(ProfiloEntity p) {
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
	public ProfiloEntity modificaProfilo(ProfiloEntity p) {
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
	public ProfiloEntity cancellaProfilo(ProfiloEntity p) {
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
