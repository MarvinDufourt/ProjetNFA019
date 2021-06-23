package cnam.net.dao;

import java.util.List;

import cnam.net.metier.Frigo;

public interface FrigoDao {

	Frigo get(long id);

	List<Frigo> getAll();

	void save(Frigo frigo);

	void update(Frigo frigo);

	void delete(Frigo frigo);
	
	public long countAssociatedProduit(Frigo frigo);
	
}
