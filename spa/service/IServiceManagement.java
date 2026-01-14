package spa.service;

import spa.Services;
import java.util.List;

public interface IServiceManagement {

	List<Services> getAll() throws Exception;

	void ajouterService(Services s) throws Exception;

	void supprimerService(String nom) throws Exception;
}
