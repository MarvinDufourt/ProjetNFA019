package cnam.net.dao;

import cnam.net.exception.DAOException;
import cnam.net.metier.Utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurDaoImpl implements UtilisateurDao {

    private static final String SQL_SELECT_PAR_ID = "SELECT id, email, nom, prenom, login, mdp, admin FROM Utilisateur WHERE id = ?";
    
    private static final String SQL_SELECT_PAR_LOGIN_ET_MDP = "SELECT id, email, nom, prenom, login, mdp, admin FROM Utilisateur WHERE Login = ? AND mdp = ?";

    private static final String SQL_INSERT_UTILISATEUR = "INSERT INTO Utilisateur (email, nom, prenom, login, mdp, admin) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_UTILISATEUR = "UPDATE Utilisateur set  email = ? , nom = ? , prenom = ? , login = ? , mdp = ? , admin = ? where id = ?";
    
    private static final String SQL_DELETE_UTILISATEUR = "DELETE FROM Utilisateur where id = ?";

    // Factory gestion connexion
    private DAOFactory daoFactory;

    public UtilisateurDaoImpl(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;
    }
    @Override
    public Utilisateur get(long id) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_ID, false, id );
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if ( resultSet.next() ) {
                utilisateur = SqlUtils.mapUtilisateur( resultSet );
            }
        } catch (SQLException e ) {
            throw new DAOException( e );
        } finally {
            SqlUtils.closeAll( resultSet, preparedStatement, connexion );
        }

        return utilisateur;
    }

    @Override
    public List<Utilisateur> getAll() {

        return null;
    }

    @Override
    public void save( Utilisateur utilisateur ) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_INSERT_UTILISATEUR, true, utilisateur.getEmail(),  utilisateur.getNom() , utilisateur.getPrenom() , utilisateur.getLogin() , utilisateur.getMotDePasse() , utilisateur.getIsAdmin());
            int statut = preparedStatement.executeUpdate();

            if ( statut == 0 ) {
                throw new DAOException( "Erreur save()" );
            }

            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if ( valeursAutoGenerees.next() ) {

                utilisateur.setId( valeursAutoGenerees.getLong( 1 ) );
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
    public void update(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_UPDATE_UTILISATEUR, true, utilisateur.getEmail(),  utilisateur.getNom() , utilisateur.getPrenom(), utilisateur.getLogin() , utilisateur.getMotDePasse() , utilisateur.getIsAdmin(), utilisateur.getId());
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
    public void delete(Utilisateur utilisateur) {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;


        try {

            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_DELETE_UTILISATEUR, true,  utilisateur.getId());
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
	public Utilisateur connexionUtlisateur(String login, String mdp) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Utilisateur utilisateur = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_SELECT_PAR_LOGIN_ET_MDP, false, login, mdp );
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                utilisateur = SqlUtils.mapUtilisateur( resultSet );
            }
        } catch (SQLException e ) {
            throw new DAOException( e );
        } finally {
            SqlUtils.closeAll( resultSet, preparedStatement, connexion );
        }

        return utilisateur;
		
	}




}
