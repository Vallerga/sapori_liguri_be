package it.gruppofos.saporiliguri.be.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import it.gruppofos.saporiliguri.be.db.HibernateUtil;
import it.gruppofos.saporiliguri.be.db.entity.EntityRicetta1;
import it.gruppofos.saporiliguri.be.db.entity.EntityRicetta1Builder;
import it.gruppofos.saporiliguri.be.presentation.model.PojoRicetta;
import it.gruppofos.saporiliguri.be.presentation.model.PojoRicettaBuilder;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response.Status;

public class RicetteBusiness {

	private RicetteBusiness() {

	}

	// Prendo un ingrediente usando l'id nel db <-- NON IN USO
	public static PojoRicetta singoloIngrediente(Integer id) {
		Session session = null;
		EntityRicetta1 dbOutput = null;
		PojoRicetta result = null;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		dbOutput = session.find(EntityRicetta1.class, id);

		if (dbOutput != null) {
			result = new PojoRicettaBuilder().setIndice(dbOutput.getIndice()).setIngrediente(dbOutput.getIngrediente())
					.setPrezzo(dbOutput.getPrezzo()).setQuantita(dbOutput.getQuantita()).setImgUrl(dbOutput.getImgUrl())
					.build();

			session.getTransaction().commit();
		}
		return result;
	}

	// Prendo un ingrediente usando l'indice passato dal frontend
	public static PojoRicetta prendiConIndice(Integer indice) {
		EntityRicetta1 dbOutput = null;
		PojoRicetta result = null;
		Session session = null;
		CriteriaBuilder builder;
		CriteriaQuery<EntityRicetta1> criteria;
		Root<EntityRicetta1> myQuery;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		builder = session.getCriteriaBuilder();
		criteria = builder.createQuery(EntityRicetta1.class);
		myQuery = criteria.from(EntityRicetta1.class);
		criteria.select(myQuery).where(builder.equal(myQuery.get("indice"), indice));
		dbOutput = session.createQuery(criteria).getSingleResult();

		if (dbOutput != null) {
			result = new PojoRicettaBuilder().setIndice(dbOutput.getIndice()).setIngrediente(dbOutput.getIngrediente())
					.setPrezzo(dbOutput.getPrezzo()).setQuantita(dbOutput.getQuantita())
					.setDescrizione(dbOutput.getDescrizione()).setImgUrl(dbOutput.getImgUrl()).build();

			session.getTransaction().commit();
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return result;
	}

	// prendo la lista di tutti i membri della tabella ricetta1
	public static List<PojoRicetta> listaPestoModel() {
		Session session = null;
		List<EntityRicetta1> allTableMember = null;
		List<PojoRicetta> result = new ArrayList<>();
		PojoRicetta ingrediente = null;
		CriteriaBuilder builder;
		CriteriaQuery<EntityRicetta1> criteria;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		builder = session.getCriteriaBuilder();
		criteria = builder.createQuery(EntityRicetta1.class);
		criteria.from(EntityRicetta1.class);
		allTableMember = session.createQuery(criteria).getResultList();
		session.getTransaction().commit();
		if (allTableMember != null) {
			for (EntityRicetta1 member : allTableMember) {

				ingrediente = new PojoRicettaBuilder().setIndice(member.getIndice())
						.setIngrediente(member.getIngrediente()).setPrezzo(member.getPrezzo())
						.setQuantita(member.getQuantita()).setDescrizione(member.getDescrizione())
						.setImgUrl(member.getImgUrl()).build();
				result.add(ingrediente);
			}
		} else {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return result;
	}

	// inserisco un ingrediente alla lista di ingredienti presenti
	public static void inserisciIngrediente(PojoRicetta pojoIngrediente) {
		Session session = null;
		EntityRicetta1 nuovoIngrediente = null;

		nuovoIngrediente = new EntityRicetta1Builder().setIndice(pojoIngrediente.getIndice())
				.setIngrediente(pojoIngrediente.getIngrediente()).setPrezzo(pojoIngrediente.getPrezzo())
				.setQuantita(pojoIngrediente.getQuantita()).setDescrizione(pojoIngrediente.getDescrizione())
				.setImgUrl(pojoIngrediente.getImgUrl()).build();

		if (nuovoIngrediente != null) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			session.save(nuovoIngrediente);
			session.getTransaction().commit();
		} else {
			throw new WebApplicationException(Status.NOT_IMPLEMENTED);
		}
	}

	// modifico un ingrediente specifico dalla lista di ingredienti basandomi
	// sull'indice
	public static PojoRicetta modificaIngrediente(PojoRicetta pojoParam, Integer indice) {
		Session session = null;
		EntityRicetta1 entityNonModificata = null;
		PojoRicetta ingrediente = null;
		CriteriaBuilder builder;
		CriteriaQuery<EntityRicetta1> criteria;
		Root<EntityRicetta1> myQuery;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		builder = session.getCriteriaBuilder();
		criteria = builder.createQuery(EntityRicetta1.class);
		myQuery = criteria.from(EntityRicetta1.class);
		criteria.select(myQuery).where(builder.equal(myQuery.get("indice"), indice));
		entityNonModificata = session.createQuery(criteria).getSingleResult();

		session.getTransaction().commit();

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		entityNonModificata = new EntityRicetta1Builder().setId(entityNonModificata.getId())
				.setIndice(entityNonModificata.getIndice()).setIngrediente(pojoParam.getIngrediente())
				.setPrezzo(pojoParam.getPrezzo()).setQuantita(pojoParam.getQuantita())
				.setDescrizione(pojoParam.getDescrizione()).setImgUrl(pojoParam.getImgUrl()).build();

		if (entityNonModificata != null) {
			session.update(entityNonModificata);
			session.getTransaction().commit();

			ingrediente = new PojoRicettaBuilder().setIndice(entityNonModificata.getIndice())
					.setIngrediente(entityNonModificata.getIngrediente()).setPrezzo(entityNonModificata.getPrezzo())
					.setQuantita(entityNonModificata.getQuantita()).setDescrizione(entityNonModificata.getDescrizione())
					.setImgUrl(entityNonModificata.getImgUrl()).build();

			return ingrediente;
		} else {
			throw new WebApplicationException(Status.NOT_ACCEPTABLE);
		}
	}

	public static EntityRicetta1 eliminaConIndice(Integer paramId) {
		EntityRicetta1 dbOutput = null;
		Session session = null;
		CriteriaBuilder builder;
		CriteriaQuery<EntityRicetta1> criteria;
		Root<EntityRicetta1> myQuery;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		builder = session.getCriteriaBuilder();
		criteria = builder.createQuery(EntityRicetta1.class);
		myQuery = criteria.from(EntityRicetta1.class);
		criteria.select(myQuery).where(builder.equal(myQuery.get("indice"), paramId));
		dbOutput = session.createQuery(criteria).getSingleResult();

		session.delete(dbOutput);
		session.getTransaction().commit();

		return dbOutput;
	}
}
