package spa.service;

public interface IUserManagement {
	
	void afficherTousLesUtilisateurs(String type) throws Exception;

    void deleteUserByCin(String cin) throws Exception;

    void ajouterUser(spa.user u) throws Exception;
    
    void updateByCin(String cin , spa.user updateUser) throws Exception;
    
}
