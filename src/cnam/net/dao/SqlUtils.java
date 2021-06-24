package cnam.net.dao;

import cnam.net.metier.Frigo;
import cnam.net.metier.Historique;
import cnam.net.metier.Utilisateur;
import cnam.net.metier.Produit;
import cnam.net.metier.ProduitReference;

import java.sql.*;

public class SqlUtils {


	public static PreparedStatement initialisationRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets) throws SQLException {
		PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < objets.length; i++) {
			preparedStatement.setObject(i + 1, objets[i]);
		}
		return preparedStatement;
	}

	/**
	 * 
	 * @param resultSet Acc�s � la base de donn�es
	 * @return les donn�es de la table utilisateur
	 * @throws SQLException erreur acc�s bdd
	 */
	public static Utilisateur mapUtilisateur(ResultSet resultSet) throws SQLException {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(resultSet.getLong("id"));
		utilisateur.setEmail(resultSet.getString("email"));
		utilisateur.setNom(resultSet.getString("nom"));
		utilisateur.setPrenom(resultSet.getString("prenom"));
		utilisateur.setIsAdmin(resultSet.getString("admin"));
		utilisateur.setLogin(resultSet.getString("login"));
		utilisateur.setMotDePasse(resultSet.getString("mdp"));

		return utilisateur;
	}
	/**
	 * 
	 * @param resultSet Acc�s � la base de donn�es
	 * @return les donn�es de la table frigo
	 * @throws SQLException erreur acc�s bdd
	 */
	public static Frigo mapFrigo(ResultSet resultSet) throws SQLException {
		Frigo frigo = new Frigo();
		frigo.setId(resultSet.getLong("id"));
		frigo.setNom(resultSet.getString("nom"));
		frigo.setEmplacement(resultSet.getString("emplacement"));
		frigo.setTempMatin(resultSet.getDouble("tempMatin"));
		frigo.setTempAprem(resultSet.getDouble("tempAprem"));
		frigo.setDateDernierePriseTemp(resultSet.getTimestamp("dateDernierePriseTemp"));
		return frigo;
	}
	/**
	 * 
	 * @param resultSet Acc�s � la base de donn�es
	 * @return les donn�es de la table produit
	 * @throws SQLException erreur acc�s bdd
	 */
	public static Produit mapProduit(ResultSet resultSet) throws SQLException {
		Produit produit = new Produit();
		produit.setId(resultSet.getLong("id"));
		produit.setNom(resultSet.getString("nom"));
		produit.setCategorie(resultSet.getString("categorie"));
		produit.setStatut(resultSet.getString("statut"));
		produit.setIdFrigoAssocier(resultSet.getLong("idFrigoAssocier"));
		produit.setDlc(resultSet.getTimestamp("dlc"));
		produit.setDateHeureEntree(resultSet.getTimestamp("dateEntreeProduit"));
		return produit;
	}
	/**
	 * 
	 * @param resultSet Acc�s � la base de donn�es
	 * @return les donn�es de la table produitreference
	 * @throws SQLException erreur acc�s bdd
	 */
	public static ProduitReference mapProduitReference(ResultSet resultSet) throws SQLException {
		ProduitReference produitReference = new ProduitReference();
		produitReference.setId(resultSet.getLong("id"));
		produitReference.setNom(resultSet.getString("nom"));
		produitReference.setCategorie(resultSet.getString("categorie"));
		produitReference.setDlcHeure(resultSet.getLong("dlcHeure"));
		return produitReference;
	}
	/**
	 * 
	 * @param resultSet Acc�s � la base de donn�es
	 * @return les donn�es de la table historique
	 * @throws SQLException erreur acc�s bdd
	 */
	public static Historique mapHistorique(ResultSet resultSet) throws SQLException {
		Historique historique = new Historique();
		historique.setUtilisateur(resultSet.getString("utilisateur"));
		historique.setMessage(resultSet.getString("message"));
		historique.setDate(resultSet.getTimestamp("date"));
		return historique;
	}

	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("�chec closeResultSet : " + e.getMessage());
			}
		}
	}


	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println("�chec closeStatement : " + e.getMessage());
			}
		}
	}


	public static void closeConnection(Connection connexion) {
		if (connexion != null) {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("�chec closeConnection : " + e.getMessage());
			}
		}
	}


	public static void closeStatementConnection(Statement statement, Connection connexion) {
		closeStatement(statement);
		closeConnection(connexion);
	}


	public static void closeAll(ResultSet resultSet, Statement statement, Connection connexion) {
		closeResultSet(resultSet);
		closeStatement(statement);
		closeConnection(connexion);
	}
}
