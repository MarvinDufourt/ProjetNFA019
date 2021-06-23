package cnam.net.dao;

import java.util.List;

import cnam.net.metier.ProduitReference;

public interface ProduitReferenceDao {
	ProduitReference get(long id);

	List<ProduitReference> getAll();

	void save(ProduitReference produitReference);

	void update(ProduitReference produitReference);

	void delete(ProduitReference produitReference);
}
