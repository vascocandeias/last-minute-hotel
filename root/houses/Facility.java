package root.houses;

import java.util.*;

public enum Facility {
	WIFI("Wifi"),
	POOL("Pool"),
	BREAKFAST("Breakfast"),
	IRON("Iron"),
	MAT("Praying Mat"),
	KEETLE("Keetle"),
	DENTAL("Dental Kit"),
	SHAMPOO("Shampoo"),
	WASHING("Washing Gel"),
	PARKING("Parking"),
	SMOKING("Smoking Area"),
	PLAYGROUND("Playground"),
	DURIAN("Durian Permission"),
	PETS("Pets Permission"),
	BLOWDRYER("Blowdryer"),
	FRIDGE("Fridge");

	private String name;

	private Facility(String name) {
		this.name = name;
	}

	public String name(){
		return name;
	}
}
