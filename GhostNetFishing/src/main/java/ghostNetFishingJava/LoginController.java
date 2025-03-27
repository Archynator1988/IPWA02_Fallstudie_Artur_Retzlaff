package ghostNetFishingJava;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped

//Controller zum Zwischenspeichern von Name und Telefonnummer sowie zur Seitenweiterleitung 
public class LoginController implements Serializable {
	private String name;
	private String tel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String registrationAMP() {
		return "aufgabenlisteAMP";
	}

	public String registrationMP() {
		return "aufgabenlisteMP";
	}

	public String registrationBP() {
		return "aufgabenlisteBP";
	}

	public String zurueck() {
		return "welcome";
	}

}
