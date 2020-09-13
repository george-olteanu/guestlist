import java.util.ArrayList;
import java.util.Scanner;

public class Program {
	
	public static GuestList myList;
	
	public static void addParticipant() {
		System.out.println("Se adauga o noua persoana…");
		String firstName, lastName, email, phoneNumber;
		System.out.println("Introduceti numele de familie: ");
		lastName = sc.next();
		System.out.println("Introduceti prenumele:");
		firstName = sc.next();
		System.out.println("Introduceti email:");
		email = sc.next();
		System.out.println("Introduceti numar de telefon (format „+40733386463“):");
		phoneNumber = sc.next();
		
		try {
			Integer result = myList.addParticipant(new Guest(firstName, lastName, phoneNumber, email));
			if(result == -1) {
				System.out.println("Participantul este deja inscris.");
			} else if(result == 0) {
				System.out.println("Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
			} else {
				System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine "
						+ result + " Te vom notifica daca un loc devine disponibil.");
			}
			
		} catch (EmailException e) {
			System.out.println(e.toString());
		}
	}
	
	public static void deleteParticipant() {
		Boolean result = myList.deleteParticipant();
		if(result == false) {
			System.out.println("Eroare: Persoana nu este inscrisa la eveniment.");
		} else {
			System.out.println("Stergerea persoanei s-a realizat cu succes.");
		}
	}
	
	public static void checkParticipant() {
		boolean result = myList.checkParticipant();
		if(result == false) {
			System.out.println("Participantul nu a fost gasit");
		} else {
			System.out.println("Participantul este inscris la eveniment.");
		}
	}
	
	public static void availableSpots() {
		System.out.println(myList.availableSpots());
	}
	
	public static void guestSize() {
		System.out.println(myList.participantSize());
	}
	
	public static void waitListNo() {
		System.out.println(myList.waitingListSize());
	}
	
	public static void totalSize() {
		System.out.println(myList.totalSize());
	}
	
	public static void search() {
		System.out.println("Ce doriti sa cautati?");
		String sch = sc.next();
		ArrayList<Guest> foundGuests = myList.search(sch);
		System.out.println("Au fost gasiti " + foundGuests.size() + " participanti.");
		
		for (int i = 0; i < foundGuests.size(); i++) {
			System.out.println(foundGuests.get(i).toString());
		}
	}

	private static Scanner sc = new Scanner(System.in);

	public static void displayCommands() {
		System.out.println("help         - Afiseaza aceasta lista de comenzi\r\n"
				+ "add          - Adauga o noua persoana (inscriere) \r\n"
				+ "check        - Verifica daca o persoana este inscrisa la eveniment \r\n"
				+ "remove       - Sterge o persoana existenta din lista \r\n"
				+ "update       - Actualizeaza detaliile unei persoane \r\n"
				+ "guests       - Lista de persoane care participa la eveniment\r\n"
				+ "waitlist     - Persoanele din lista de asteptare \r\n"
				+ "available    - Numarul de locuri libere \r\n"
				+ "guests_no    - Numarul de persoane care participa la eveniment \r\n"
				+ "waitlist_no  - Numarul de persoane din lista de asteptare \r\n"
				+ "subscribe_no - Numarul total de persoane inscrise \r\n"
				+ "search       - Cauta toti invitatii conform sirului de caractere introdus \r\n"
				+ "quit         - Inchide aplicatia");
	}

	public static void main(String[] args) {
		System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
		int nrLocuri = sc.nextInt();
		myList = new GuestList(nrLocuri);
		
	//try {
//		Guest g1 = new Guest("Alex", "Olteanu", "0723998151","alex.olteanu@gmail.com");
//		Guest g2 = new Guest("Ana", "Maricica", "0733444555","ana.maricica@gmail.com");
//		Guest g3 = new Guest("Marian", "Badica","0722111000","Marian.badica@gmail.com");
//		
//		myList.addParticipant(g1);
//		myList.addParticipant(g2);
//		myList.addParticipant(g3);
	//} catch (EmailException e) {
		//ignored
	//}	

		System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
		
		String input = sc.next();
		while (!input.equals("quit")) {
			
			switch (input) {
				case "help":
					displayCommands();
					break;
				case "add":
					addParticipant();
					break;
				case "check":
					checkParticipant();
					break;
				case "remove":
					deleteParticipant();
					break;
				case "guest":
					myList.participantListGuests();
					break;
				case "update":
					myList.updateContact();
					break;
				case "waitlist":
					myList.waitingListGuest();
					break;
				case "available":
					availableSpots();
					break;
				case "guest_no":
					guestSize();
					break;
				case "waitlist_no":
					waitListNo();
					break;
				case "subscribe_no":
					totalSize();
					break;
				case "search":
					search();
					break;
				default:
					System.out.println("Comanda introdusa nu este valida.");
					break;
				}
			System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
			
			input = sc.next();
		}
		System.out.println("Aplicatia se inchide...");
	}

}
