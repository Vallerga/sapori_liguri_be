package it.gruppofos.saporiliguri.be.business;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import it.gruppofos.saporiliguri.be.db.HibernateUtil;
import it.gruppofos.saporiliguri.be.db.entity.RicettaEntity;
import it.gruppofos.saporiliguri.be.presentation.model.PestoModel;

public class RicetteBusiness {

	private RicetteBusiness() {

	}

	public static PestoModel singoloIngrediente(Integer id) {
		Session session = null;

		RicettaEntity ingrediente = null;
		PestoModel result = null;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		ingrediente = session.find(RicettaEntity.class, id);
		if (ingrediente != null) {
			result = new PestoModel.PestoModelBuilder().build();

//					, ingrediente.getIndice(),
//					ingrediente.getIngrediente(), ingrediente.getPrezzo(), ingrediente.getQuantita(),
//					ingrediente.getDescrizione(), ingrediente.getImgUrl()).build();
			session.getTransaction().commit();
		}

		return result;

	}

	public static List<PestoModel> listaPestoModel() {
		Session session = null;
		try {
			List<RicettaEntity> data = null;
			List<PestoModel> result = null;
			PestoModel pesto = null;

			session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RicettaEntity> criteria = builder.createQuery(RicettaEntity.class);
			criteria.from(RicettaEntity.class);
			data = session.createQuery(criteria).getResultList(); // <-- creare builder pattern
			session.getTransaction().commit();
			session.close();

			return result;
		} finally {
			// terminate session factory, otherwise program won't end
			if (session != null)
				session.close();
		}
	}
}
