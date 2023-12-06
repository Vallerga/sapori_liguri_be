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

public class RicetteBusiness {

	private RicetteBusiness() {

	}

	public static PojoRicetta singoloIngrediente(Integer id) {
		Session session = null;
		EntityRicetta1 dbOutput = null;
		PojoRicetta result = null;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		dbOutput = session.find(EntityRicetta1.class, id);
		if (dbOutput != null) {
			result = new PojoRicettaBuilder().setIndice(dbOutput.getIndice()).setIngrediente(dbOutput.getIngrediente())
					.setPrezzo(dbOutput.getPrezzo()).setQuantita(dbOutput.getQuantita())
					.setImgUrl(dbOutput.getImgUrl()).build();

			session.getTransaction().commit();
		}
		return result;
	}

	public static List<PojoRicetta> listaPestoModel() {
		Session session = null;
		List<EntityRicetta1> allTableMember = null;
		List<PojoRicetta> result = new ArrayList<>();
		PojoRicetta pesto = null;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<EntityRicetta1> criteria = builder.createQuery(EntityRicetta1.class);
		criteria.from(EntityRicetta1.class);
		allTableMember = session.createQuery(criteria).getResultList();

		if (allTableMember != null) {
			for (EntityRicetta1 member : allTableMember) {

				pesto = new PojoRicettaBuilder().setIndice(member.getIndice()).setIngrediente(member.getIngrediente())
						.setPrezzo(member.getPrezzo()).setQuantita(member.getQuantita())
						.setDescrizione(member.getDescrizione()).setImgUrl(member.getImgUrl()).build();
				result.add(pesto);
			}
			session.getTransaction().commit();
		}
		return result;
	}

	public static void inserisciIngrediente(PojoRicetta pojoParam) {
		Session session = null;
		EntityRicetta1 nuovoIngrediente = new EntityRicetta1Builder().setIndice(pojoParam.getIndice())
				.setIngrediente(pojoParam.getIngrediente()).setPrezzo(pojoParam.getPrezzo())
				.setQuantita(pojoParam.getQuantita()).setDescrizione(pojoParam.getDescrizione())
				.setImgUrl(pojoParam.getImgUrl()).build();

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(nuovoIngrediente);
		session.getTransaction().commit();
	}

	public static void modificaIngrediente(PojoRicetta pojoParam) {
		Session session = null;
		EntityRicetta1 nuovoIngrediente = new EntityRicetta1Builder().setId(pojoParam.getId())
				.setIndice(pojoParam.getIndice()).setIngrediente(pojoParam.getIngrediente())
				.setPrezzo(pojoParam.getPrezzo()).setQuantita(pojoParam.getQuantita())
				.setDescrizione(pojoParam.getDescrizione()).setImgUrl(pojoParam.getImgUrl()).build();

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.update(nuovoIngrediente);
		session.getTransaction().commit();
	}

	public static void eliminaIngrediente(PojoRicetta pojoParam) {
		Session session = null;
		EntityRicetta1 nuovoIngrediente = new EntityRicetta1Builder().setId(pojoParam.getId()).build();

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.delete(nuovoIngrediente);
		session.getTransaction().commit();
	}

	public static void eliminaIngredienteId(Integer paramId) {
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
		System.out.println("dbOutput: " + dbOutput.toString());
		if (dbOutput != null) {
			session.delete(dbOutput);
			session.getTransaction().commit();
		} else {
			System.out.println("dimmelo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		}
	}
}
