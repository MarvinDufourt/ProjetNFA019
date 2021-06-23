package cnam.net.metier;

import java.sql.Timestamp;

public class Produit extends ProduitReference{

	private long id;
	private long idFrigoAssocier;
	private String nom;
	private String categorie;
	private String statut;
	private Timestamp dlc;
	private Timestamp dateEntreeProduit;


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
	public Timestamp getDateHeureEntree() {
		return dateEntreeProduit;
	}
	public void setDateHeureEntree(Timestamp dateHeureEntree) {
		this.dateEntreeProduit = dateHeureEntree;
	}

	public long getIdFrigoAssocier() {
		return idFrigoAssocier;
	}
	public void setIdFrigoAssocier(long idFrigoAssocier) {
		this.idFrigoAssocier = idFrigoAssocier;
	}

	public Timestamp getDlc() {
		return dlc;
	}
	public void setDlc(Timestamp dlc) {
		this.dlc = dlc;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	@Override
	public String toString() {
		return " Produit { " +
				"id produit=" + this.getId() +
				", nom produit='" + this.getNom() + '\'' +
				", catégorie='" + this.getCategorie() + '\'' +
				", Date entrée produit='" + this.getDateHeureEntree() + '\'' + 
				" Dlc du produit : '"+ this.getDlc() + '\'' + 
				" Statut produit : "+this.getStatut()+
				 " }";
	}
}
