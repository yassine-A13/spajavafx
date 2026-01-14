package spa.service;

import spa.UserDAO;
import spa.user;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserManagement implements IUserManagement {

	// ================== CONSOLE ==================
	@Override
	public void afficherTousLesUtilisateurs(String role) throws Exception {
		Set<String> typesValides = Set.of("client", "gerant", "employee", "tous");

		if (!typesValides.contains(role)) {
			System.out.println("choix invalide");
			return;
		}

		System.out.println("=== Liste des utilisateurs ===");

		UserDAO.getAll().stream()
				.filter(u -> role.equals("tous") || u.getRole().equals(role))
				.sorted(Comparator.comparing(user::getNom))
				.forEach(u ->
						System.out.printf("%s | %s | %s%n",
								u.getNom(), u.getCin(), u.getEmail())
				);

		long total = UserDAO.getAll().stream()
				.filter(u -> role.equals("tous") || u.getRole().equals(role))
				.count();

		System.out.println("\nTotal utilisateurs : " + total);
	}

	// ================== JAVAFX ==================
	public List<user> getAll(String role) throws Exception {
		Set<String> typesValides = Set.of("client", "gerant", "employee", "tous");

		if (!typesValides.contains(role)) {
			throw new IllegalArgumentException("Rôle invalide");
		}

		return UserDAO.getAll().stream()
				.filter(u -> role.equals("tous") || u.getRole().equals(role))
				.sorted(Comparator.comparing(user::getNom))
				.collect(Collectors.toList());
	}

	// ================== CRUD ==================
	@Override
	public void deleteUserByCin(String cin) throws Exception {
		UserDAO.deletebycin(cin);
	}

	@Override
	public void ajouterUser(user u) throws Exception {
		UserDAO.create(u);
	}

	@Override
	public void updateByCin(String cin, user updateUser) throws Exception {
		UserDAO.updatebycin(cin, updateUser);
	}
}
