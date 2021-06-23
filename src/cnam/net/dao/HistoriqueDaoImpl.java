package cnam.net.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import cnam.net.exception.DAOException;
import cnam.net.metier.Historique;

public class HistoriqueDaoImpl implements HistoriqueDao {
	
	private static final String SQL_INSERT_HISTORIQUE = "INSERT INTO historique (utilisateur, message, date) VALUES (?, ?, ?)";
	
	private DAOFactory daoFactory;

	public HistoriqueDaoImpl(DAOFactory daoFactory) {

		this.daoFactory = daoFactory;
		}

	public void save(Historique historique, String message) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			Calendar dateActuelle = Calendar.getInstance();
			String formatedDateActuelle = dateActuelle.get(Calendar.YEAR)+ "-" + (dateActuelle.get(Calendar.MONTH)+1) + "-" + dateActuelle.get(Calendar.DAY_OF_MONTH) + " " + dateActuelle.get(Calendar.HOUR_OF_DAY) + ":" + dateActuelle.get(Calendar.MINUTE) + ":" + dateActuelle.get(Calendar.SECOND);
			Timestamp TsDateActuelle = Timestamp.valueOf(formatedDateActuelle);
			historique.setDate(TsDateActuelle);
			historique.setMessage(message);
			connexion = daoFactory.getConnection();
			preparedStatement = SqlUtils.initialisationRequetePreparee( connexion, SQL_INSERT_HISTORIQUE, true, historique.getUtilisateur(), historique.getMessage(), historique.getDate());
			int statut = preparedStatement.executeUpdate();																			

			if ( statut == 0 ) {
				throw new DAOException( "Erreur save()" );
			}
			
		} catch ( SQLException e ) {
			throw new DAOException( e );
		} finally {
			SqlUtils.closeAll( valeursAutoGenerees, preparedStatement, connexion );
		}
	}

}
