package ghostNetFishingJava;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aufgabe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Eindeutige ID als Primärschlüssel für die Datenbank (wird automatisch
	// generiert )

	private Long id;

	private String name;
	private String telefonnummer;
	private BigDecimal breitengrad = BigDecimal.ZERO;
	private BigDecimal laengengrad = BigDecimal.ZERO;

	private int groesse;
	private String bergungspersonName;
	private String bergungspersonTelefon;

	@Enumerated(EnumType.STRING)
	private Status status;

	// Enum für die möglichen Zustände eines Geisternetzes mit Beschreibung.
	public enum Status {
		GEMELDET("Gemeldet"), BERGUNG_BEVORSTEHEND("Bergung bevorstehend"), GEBORGEN("Geborgen"),
		VERSCHOLLEN("Verschollen");

		private final String beschreibung;

		Status(String beschreibung) {
			this.beschreibung = beschreibung;
		}

		public String getBeschreibung() {
			return beschreibung;
		}

	}

	// Standard-Konstruktor
	public Aufgabe() {
	}

	public Aufgabe(String name, String telefonnummer, BigDecimal breitengrad, BigDecimal laengengrad, int groesse,
			Status status, String bergungspersonName, String bergungspersonTelefon) {
		this.name = name;
		this.telefonnummer = telefonnummer;
		this.breitengrad = breitengrad;
		this.laengengrad = laengengrad;
		this.groesse = groesse;
		this.status = status;
		this.bergungspersonName = bergungspersonName;
		this.bergungspersonTelefon = bergungspersonTelefon;
	}

	// Getter und Setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public BigDecimal getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(BigDecimal breitengrad) {
		this.breitengrad = breitengrad;
	}

	public BigDecimal getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(BigDecimal laengengrad) {
		this.laengengrad = laengengrad;
	}

	public int getGroesse() {
		return groesse;
	}

	public void setGroesse(int groesse) {
		this.groesse = groesse;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getBergungspersonName() {
		return bergungspersonName;
	}

	public void setBergungspersonName(String bergungspersonName) {
		this.bergungspersonName = bergungspersonName;
	}

	public String getBergungspersonTelefon() {
		return bergungspersonTelefon;
	}

	public void setBergungspersonTelefon(String bergungspersonTelefon) {
		this.bergungspersonTelefon = bergungspersonTelefon;
	}
}
