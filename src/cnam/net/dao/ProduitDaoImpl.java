package cnam.net.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cnam.net.exception.DAOException;
import cnam.net.metier.Produit;

public class ProduitDaoImpl implements ProduitDao {
	
	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM produit WHERE id = ?";
	
	private static final String SQL_INSERT_PRODUIT = "INSERT INTO produit (nom, categorie, statut, idFrigoAssocier, dlc, dateEntreeProduit) VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_UPDATE_PRODUIT = "UPDATE produit set  nom = ?, categorie = ?, statut = ?, idFrigoAssocier = ?, dlc = ?, dateEntreeProduit = ? where id = ?";

	private static final String SQL_DELETE_PRODUIT = "DELETE FROM produit where id = ?";
	
	private DAOFactory daoFactory;

	public ProduitDaoImpl(DAOFactory daoFactory) {

		this.daoFactory = daoFactory;
	}

	
	public Produit get(long id) {
	Connection connexion = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	Produit produit = null;

	try {
		/* Récupération d'une connexion depuis la Factory */
		connexion = daoFactory.getConnection();
		preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
		resultSet = preparedStatement.executeQuery();
		/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
		if ( resultSet.next() ) {
			produit = SqlUtils.mapProduit( resultSet );
		}
	} catch (SQLException e ) {
		throw new DAOException( e );
	} finally {
		SqlUtils.closeAll( resultSet, preparedStatement, connexion );
	}

	return produit;
}

	
	public List<Produit> getAll() {
		
		return null;
	}

	
	public void save(Produit produit) {
	Connection connexion = null;
	PreparedStatement preparedStatement = null;
	ResultSet valeursAutoGenerees = null;

	try {

		connexion = daoFactory.getConnection();
		preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_INSERT_PRODUIT, true, produit.getNom(), produit.getCategorie(), produit.getStatut(), produit.getIdFrigoAssocier(), produit.getDlc(), produit.getDateHeureEntree());
		int statut = preparedStatement.executeUpdate();																			

		if ( statut == 0 ) {
			throw new DAOException( "Erreur save()" );
		}

		valeursAutoGenerees = preparedStatement.getGeneratedKeys();
		if ( valeursAutoGenerees.next() ) {

			produit.setId( valeursAutoGenerees.getLong( 1 ) );
		} else {
			throw new DAOException( "Erreur getGeneratedKeys" );
		}
	} catch ( SQLException e ) {
		throw new DAOException( e );
	} finally {
		SqlUtils.closeAll( valeursAutoGenerees, preparedStatement, connexion );
	}
}


	public void update(Produit produit) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_UPDATE_PRODUIT, true, produit.getNom(), produit.getCategorie(), produit.getStatut(), produit.getIdFrigoAssocier(), produit.getDlc(), produit.getDateHeureEntree(), produit.getId());
            int statut = preparedStatement.executeUpdate();  								

            if ( statut == 0 ) {
                throw new DAOException( "Erreur update()" );
            }

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            SqlUtils.closeStatementConnection(preparedStatement, connexion );
        }

    }

	
	public void delete(Produit produit) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_DELETE_PRODUIT, true,  produit.getId());
            int statut = preparedStatement.executeUpdate();

            if ( statut == 0 ) {
                throw new DAOException( "Erreur update()" );
            }

        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            SqlUtils.closeStatementConnection(preparedStatement, connexion );
        }

    }




}
