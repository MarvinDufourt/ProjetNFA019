package cnam.net.dao;

import java.util.List;

import cnam.net.metier.Produit;

public interface ProduitDao {
	Produit get(long id);

	List<Produit> getAll();

	void save(Produit produit);

	void update(Produit produit);

	void delete(Produit produit);
}
