package dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import model.Car;

public class CarDAO  {

	private EntityManager manager;

	public CarDAO(EntityManager manager) {
		this.manager = manager;
	}
	
	@SuppressWarnings("unchecked")
	public List<Car> buscarCarrosZero() {
		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);

		return manager.createQuery("from Carro c where c.anoFabricacao = :ano")
					.setParameter("ano", anoAtual)
					.getResultList();		
	}
	
	@SuppressWarnings("unchecked")
	public List<Car> buscarCarrosComIdadeInferiorA(Integer ano) {
		return manager.createQuery("from Carro c where c.anoFabricacao >= :ano")
					.setParameter("ano", ano)
					.getResultList();		
	}
	
}