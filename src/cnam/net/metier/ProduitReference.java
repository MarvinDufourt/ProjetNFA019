package cnam.net.metier;

public class ProduitReference {
	private long id;
	private String nom;
	private String categorie;
	private long dlcHeure;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public long getDlcHeure() {
		return dlcHeure;
	}
	public void setDlcHeure(long dlcHeure) {
		this.dlcHeure = dlcHeure;
	}
	public String toString() {
		return "Produit{" +
				"id produit=" + id +
				", nom produit='" + nom + '\'' +
				", catégorie='" + categorie +
				'}';
	}
}
