package cnam.net.dao;

import cnam.net.metier.Historique;

public interface HistoriqueDao {

	void save(Historique historique, String message);
}
