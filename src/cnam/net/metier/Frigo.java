package cnam.net.metier;

import java.sql.Timestamp;

public class Frigo {

	private long id;
	private String nom;
	private String emplacement;
	private double tempMatin;
	private double tempAprem;
	private Timestamp dateDernierePriseTemp;
	
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
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public double getTempMatin() {
		return tempMatin;
	}
	public void setTempMatin(double tempMatin) {
		this.tempMatin = tempMatin;
	}
	public double getTempAprem() {
		return tempAprem;
	}
	public void setTempAprem(double tempAprem) {
		this.tempAprem = tempAprem;
	}

	public Timestamp getDateDernierePriseTemp() {
		return dateDernierePriseTemp;
	}
	public void setDateDernierePriseTemp(Timestamp dateDernierePriseTemp) {
		this.dateDernierePriseTemp = dateDernierePriseTemp;
	}
	
	@Override
	public String toString() {
		return "Frigo{" +
				"id=" + id +
				", nom='" + nom + '\'' +
				", Emplacement='" + emplacement + '\'' +
				", Température ce matin='" + tempMatin + '\'' + " Température cet après-midi : "+ tempAprem +
				'}';
	}
	
}
