package spa.service;

import spa.RendezVous;
import spa.RendezVousDAO;
import java.util.List;

public class RendezVousManagement implements IRendezVousManagement {

	@Override
	public List<RendezVous> getAll() throws Exception {
		return RendezVousDAO.getAll();
	}

	@Override
	public void create(RendezVous r) throws Exception {
		RendezVousDAO.create(r);
	}

	@Override
	public void deletebyid(int id) throws Exception {
		RendezVousDAO.deletebyid(id);
	}

	@Override
	public void updatebyid(int id, RendezVous updatedr) throws Exception {
		RendezVousDAO.updatebyid(id, updatedr);
	}


}
