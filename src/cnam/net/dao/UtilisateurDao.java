package cnam.net.dao;

import cnam.net.metier.Utilisateur;

import java.util.List;

public interface UtilisateurDao {
	Utilisateur get(long id);

	List<Utilisateur> getAll();

	void save(Utilisateur utilisateur);

	void update(Utilisateur utilisateur);

	void delete(Utilisateur utilisateur);

	Utilisateur connexionUtlisateur(String login, String mdp);

}
