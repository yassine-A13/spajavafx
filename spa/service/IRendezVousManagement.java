package spa.service;

import spa.RendezVous;
import java.util.List;

public interface IRendezVousManagement {

	List<RendezVous> getAll() throws Exception;

	void create(RendezVous r) throws Exception;

	void deletebyid(int id) throws Exception;

	void updatebyid(int id, RendezVous updatedr) throws Exception;


}
