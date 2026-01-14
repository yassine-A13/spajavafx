package spa.service;

import spa.Services;
import spa.ServicesDAO;
import java.util.List;

public class ServiceManagement implements IServiceManagement {

	@Override
	public List<Services> getAll() throws Exception {
		return ServicesDAO.getAll();
	}

	@Override
	public void ajouterService(Services s) throws Exception {
		ServicesDAO.ajouterService(s);
	}

	@Override
	public void supprimerService(String nom) throws Exception {
		ServicesDAO.supprimerService(nom);
	}
}
