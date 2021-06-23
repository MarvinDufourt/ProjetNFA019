package cnam.net.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cnam.net.exception.DAOException;
import cnam.net.metier.ProduitReference;

public class ProduitRefDaoImpl implements ProduitReferenceDao {

	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM produitreference WHERE id = ?";

	private static final String SQL_INSERT_PRODUIT = "INSERT INTO produitreference (nom, categorie, dlcHeure) VALUES (?, ?, ?)";

	private static final String SQL_UPDATE_PRODUIT = "UPDATE produitreference set  nom = ?, categorie = ?, dlcHeure = ? where id = ?";

	private static final String SQL_DELETE_PRODUIT = "DELETE FROM produitreference where id = ?";

	private DAOFactory daoFactory;
	
	public ProduitRefDaoImpl(DAOFactory daoFactory) {

		this.daoFactory = daoFactory;
	}

	public ProduitReference get(long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ProduitReference produitReference = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				produitReference = SqlUtils.mapProduitReference( resultSet );
			}
		} catch (SQLException e ) {
			throw new DAOException( e );
		} finally {
			SqlUtils.closeAll( resultSet, preparedStatement, connexion );
		}

		return produitReference;
	}

	public List<ProduitReference> getAll() {
		return null;
	}

	public void save(ProduitReference produitReference) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_INSERT_PRODUIT, true, produitReference.getNom(), produitReference.getCategorie(), produitReference.getDlcHeure());
			int statut = preparedStatement.executeUpdate();																			

			if ( statut == 0 ) {
				throw new DAOException( "Erreur save()" );
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {

				produitReference.setId( valeursAutoGenerees.getLong( 1 ) );
			} else {
				throw new DAOException( "Erreur getGeneratedKeys" );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			SqlUtils.closeAll( valeursAutoGenerees, preparedStatement, connexion );
		}
	}


	public void update(ProduitReference produitReference) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_UPDATE_PRODUIT, true, produitReference.getNom(), produitReference.getCategorie(), produitReference.getDlcHeure(), produitReference.getId());
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


	public void delete(ProduitReference produitReference) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_DELETE_PRODUIT, true,  produitReference.getId());
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
