package src.houses;

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

	public static final int SIZE = Facility.values().length;

	private Facility(String name) {
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public static void display(boolean[] available){

		Facility[] facilities = Facility.values();

		for(int i = 0; i < facilities.length; i++){
			if(available[i]) System.out.println("\t" + i + " - " + facilities[i].getName());
		}
	}

	public static boolean[] chooseFacilities(){

		boolean[] result = new boolean[Facility.SIZE];
		Scanner kb = new Scanner(System.in);
		int i = 0;
		Facility[] facilities = Facility.values();

		System.out.println("\nWhat are the house's facilities?\n");

		for(Facility facility : facilities){
			System.out.println("\t" + i++ + " - " + facility.getName());
		}

		System.out.println("Insert numbers (separated by any character, for example '1,3 5') or leave blank: ");
		String s = kb.nextLine().replaceAll("\\D", " ");

		kb = new Scanner(s);
		while(kb.hasNext()){
			try{
				result[kb.nextInt()] = true;
			} catch(Exception e) {}
		}
		return result;
	}

	public static String writeFacilities(boolean[] facilities){
		String results="";
		for(int i=0; i<Facility.SIZE;i++){
			if (facilities[i]==true){
				results+=i;
				results+=" ";
			}
		}
		return results;
	}

	public static boolean [] readFacilities(String s){

		boolean [] result = new boolean[Facility.SIZE];
		s = s.replaceAll("\\D", " ");
		Scanner kb = new Scanner(s);
		while(kb.hasNext()){
			try{
				result[kb.nextInt()] = true;
			} catch(Exception e) {}
		}
		return result;
	}
}
