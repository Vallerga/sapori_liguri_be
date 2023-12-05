package it.gruppofos.saporiliguri.be.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import it.gruppofos.saporiliguri.be.db.HibernateUtil;
import it.gruppofos.saporiliguri.be.db.entity.RicettaEntity;
import it.gruppofos.saporiliguri.be.presentation.model.PestoModel;
import jakarta.ws.rs.core.Response;

public class RicetteBusiness {

	private RicetteBusiness() {

	}

	public static PestoModel singoloIngrediente(Integer id) {
		Session session = null;
		RicettaEntity dbOutput = null;
		PestoModel result = null;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		dbOutput = session.find(RicettaEntity.class, id);
		if (dbOutput != null) {
			result = new PestoModel.PestoModelBuilder().setIndice(dbOutput.getIndice())
					.setIngrediente(dbOutput.getIngrediente()).setPrezzo(dbOutput.getPrezzo())
					.setQuantita(dbOutput.getQuantita()).setDescrizione(dbOutput.getDescrizione())
					.setImgUrl(dbOutput.getImgUrl()).build();

			session.getTransaction().commit();
		}
		return result;
	}

	public static List<PestoModel> listaPestoModel() {
		Session session = null;
		List<RicettaEntity> allTableMember = null;
		List<PestoModel> result = new ArrayList<>();
		PestoModel pesto = null;

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<RicettaEntity> criteria = builder.createQuery(RicettaEntity.class);
		criteria.from(RicettaEntity.class);
		allTableMember = session.createQuery(criteria).getResultList();

		if (allTableMember != null) {
			for (RicettaEntity member : allTableMember) {

				pesto = new PestoModel.PestoModelBuilder().setIndice(member.getIndice())
						.setIngrediente(member.getIngrediente()).setPrezzo(member.getPrezzo())
						.setQuantita(member.getQuantita()).setDescrizione(member.getDescrizione())
						.setImgUrl(member.getImgUrl()).build();
				result.add(pesto);
			}
			session.getTransaction().commit();
		}
		return result;
	}

	public static void inserisciIngrediente(PestoModel pojoParam) {
		Session session = null;
		RicettaEntity nuovoIngrediente = new RicettaEntity.RicettaEntityBuilder().setIndice(pojoParam.getIndice())
				.setIngrediente(pojoParam.getIngrediente()).setPrezzo(pojoParam.getPrezzo())
				.setQuantita(pojoParam.getQuantita()).setDescrizione(pojoParam.getDescrizione())
				.setImgUrl(pojoParam.getImgUrl()).build();
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();		
		session.beginTransaction();
		session.save(nuovoIngrediente);
		session.getTransaction().commit();
	}
}
