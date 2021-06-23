package cnam.net.dao;


import cnam.net.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Methode static qui renvoie une instance de DAOFactory
    public static DAOFactory getInstance() throws DAOException {

        String url = "jdbc:mysql://localhost:3306/nfa019_project?useSSL=false";
        String driver = "com.mysql.jdbc.Driver";
        String nomUtilisateur = "root";
        String motDePasse = "";

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOException("Driver not found", e);
        }

        DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);
        return instance;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    public UtilisateurDao getUtilisateurDaoImpl() {
        return new UtilisateurDaoImpl(this);
    }
    
    public FrigoDao getFrigoDaoImpl() {
        return new FrigoDaoImpl(this);
    }
    
    public ProduitDao getProduitDaoImpl() {
        return new ProduitDaoImpl(this);
    }
    
    public ProduitReferenceDao getProduitRefDaoImpl() {
		return new ProduitRefDaoImpl(this);
	}
}