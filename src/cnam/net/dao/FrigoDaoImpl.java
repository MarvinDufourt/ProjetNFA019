package cnam.net.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cnam.net.exception.DAOException;
import cnam.net.metier.Frigo;

public class FrigoDaoImpl implements FrigoDao {

	private static final String SQL_SELECT_PAR_ID = "SELECT * FROM frigo WHERE id = ?";

	private static final String SQL_INSERT_FRIGO = "INSERT INTO frigo (nom, emplacement, tempMatin, tempAprem, dateDernierePriseTemp) VALUES (?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE_FRIGO = "UPDATE frigo set  nom = ?, emplacement = ?, tempMatin = ?, tempAprem = ?, dateDernierePriseTemp = ? where id = ?";
	
	private static final String SQL_DELETE_FRIGO = "DELETE FROM frigo where id = ?";
	
	private static final String SQL_HOW_MANY_PRODUITS_ASSOCIATED_TO_FRIGO = "SELECT COUNT(produit.id) FROM produit INNER JOIN frigo ON produit.idFrigoAssocier = frigo.id WHERE frigo.id = ?";
	
	private DAOFactory daoFactory;

	public FrigoDaoImpl(DAOFactory daoFactory) {

		this.daoFactory = daoFactory;
	}

	@Override
	public Frigo get(long id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Frigo frigo = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
			resultSet = preparedStatement.executeQuery();
			/* Parcours de la ligne de données de l'éventuel ResulSet retourné */
			if ( resultSet.next() ) {
				frigo = SqlUtils.mapFrigo( resultSet );
			}
		} catch (SQLException e ) {
			throw new DAOException( e );
		} finally {
			SqlUtils.closeAll( resultSet, preparedStatement, connexion );
		}

		return frigo;
	}

	@Override
	public List<Frigo> getAll() {

		return null;
	}

	@Override
	public void save(Frigo frigo) throws DAOException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {

			connexion = daoFactory.getConnection();
			preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_INSERT_FRIGO, true, frigo.getNom(), frigo.getEmplacement(), frigo.getTempMatin(), frigo.getTempAprem(), frigo.getDateDernierePriseTemp());
			int statut = preparedStatement.executeUpdate();

			if ( statut == 0 ) {
				throw new DAOException( "Erreur save()" );
			}

			valeursAutoGenerees = preparedStatement.getGeneratedKeys();
			if ( valeursAutoGenerees.next() ) {

				frigo.setId( valeursAutoGenerees.getLong( 1 ) );
			} else {
				throw new DAOException( "Erreur getGeneratedKeys" );
			}
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			SqlUtils.closeAll( valeursAutoGenerees, preparedStatement, connexion );
		}
	}



	@Override
	public void update(Frigo frigo) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_UPDATE_FRIGO, true, frigo.getNom(), frigo.getEmplacement(), frigo.getTempMatin(), frigo.getTempAprem(), frigo.getDateDernierePriseTemp(), frigo.getId());
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

	@Override
	public void delete(Frigo frigo) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_DELETE_FRIGO, true,  frigo.getId());
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
	
	public long countAssociatedProduit(Frigo frigo) {
		 Connection connexion = null;
	        PreparedStatement preparedStatement = null;


	        try {

	            connexion = daoFactory.getConnection();
	            PreparedStatement countAssociatedProduits = connexion.prepareStatement(SQL_HOW_MANY_PRODUITS_ASSOCIATED_TO_FRIGO);
	            countAssociatedProduits.setLong(1, frigo.getId());
	            ResultSet rs = countAssociatedProduits.executeQuery();
	            if (rs.next()) {
	            	return rs.getLong(1);
	            } else {
					return 999;
				}
	        } catch ( SQLException e ) {
	            throw new DAOException( e );
	        } finally {
	            SqlUtils.closeStatementConnection(preparedStatement, connexion );
	        }

	    }
}
