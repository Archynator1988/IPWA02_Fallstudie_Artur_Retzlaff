package ghostNetFishingJava;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

@Named("controller")
@ViewScoped
public class AufgabenController implements Serializable {

	// Erstellt eine EntityManagerFactory
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostnetfishing");

	// Erstellt einen EntityManager zur Verwaltung von Datenbankoperationen
	private EntityManager entityManager = emf.createEntityManager();

	private Aufgabe neueAufgabe = new Aufgabe();

	@Inject
	private LoginController loginController; // Holt eingeloggte Benutzerdaten

	// Lade alle Aufgaben aus der Datenbank
	public List<Aufgabe> getListe() {
		entityManager.getTransaction().begin();
		TypedQuery<Aufgabe> query = entityManager.createQuery("SELECT a FROM Aufgabe a", Aufgabe.class);
		List<Aufgabe> result = query.getResultList();
		entityManager.getTransaction().commit();
		return result;
	}

	// Neue Aufgabe erstellen & in DB speichern
	public void addAufgabe() {
		entityManager.getTransaction().begin();

		// Name & Telefonnummer aus LoginController übernehmen
		neueAufgabe.setName(loginController.getName());
		neueAufgabe.setTelefonnummer(loginController.getTel());
		neueAufgabe.setStatus(Aufgabe.Status.GEMELDET);

		// In die Datenbank speichern
		entityManager.persist(neueAufgabe);

		// Bestätigt (committed) die aktuelle Transaktion und schreibt die Änderungen in
		// die Datenbank
		entityManager.getTransaction().commit();

		// Neue Aufgabe zurücksetzen
		neueAufgabe = new Aufgabe();
		neueAufgabe.setStatus(Aufgabe.Status.GEMELDET);
	}

	// Aufgabe aktualisieren (Bergungsperson eintragen)
	public void updateAufgabe(Aufgabe aufgabe) {
		entityManager.getTransaction().begin();
		if (aufgabe.getStatus() == Aufgabe.Status.BERGUNG_BEVORSTEHEND) {
			aufgabe.setBergungspersonName(loginController.getName());
			aufgabe.setBergungspersonTelefon(loginController.getTel());

			// Änderungen in der Datenbank speichern
			entityManager.merge(aufgabe);
		}
		entityManager.getTransaction().commit();
	}

	public Aufgabe getNeueAufgabe() {
		return neueAufgabe;
	}

	public void setNeueAufgabe(Aufgabe neueAufgabe) {
		this.neueAufgabe = neueAufgabe;
	}

	// Gibt alle möglichen Statuswerte des Enums zurück (für Dropdowns etc.)
	public List<Aufgabe.Status> getStatusValues() {
		return Arrays.asList(Aufgabe.Status.values());
	}

	// Gibt nur den Status "Verschollen" zurück für Melde-Personen, eingeschränktes
	// Dropdown
	public List<Aufgabe.Status> getMeldePersonStatusOptions() {
		return Arrays.asList(Aufgabe.Status.VERSCHOLLEN);
	}
}
