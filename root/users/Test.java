public class Test{
	public static void main(String[] args){

		String username = "kinderbueno";
		String password = "password";
		int phone = 965868647;
		String address = "R. D. Filipa de Vilhena";
		String nationality = "Portugal";
		String emailUser = "sofia";
		String emailDomain = "gmail.com";

		Client client = new Client(username, password, phone, address, nationality, emailUser, emailDomain);
		client.display();

		username = "vcc";
		password = "password";
		phone = 933674673;
		address = "Av, Miguel TorG";
		nationality = "Portugal";
		emailUser = "vasco";
		emailDomain = "gmail.com";

		Owner owner = new Owner(username, password, phone, address, nationality, emailUser, emailDomain, "Eu sou um gajo giro", emailUser, emailDomain);
		owner.display();
	}
}