import java.util.ArrayList;
import java.util.Scanner;

public class GuestList {

	private Integer seats;
	private ArrayList<Guest> participantList;
	private ArrayList<Guest> waitingList;
	private Scanner sc = new Scanner(System.in);

	public GuestList(Integer seats) {
		this.participantList = new ArrayList<Guest>();
		this.waitingList = new ArrayList<Guest>();
		this.seats = seats;
	}

	private Guest searchListByName(String firstName, String lastName) {
		if (firstName == null) {
			firstName = sc.next();
		}
		if (lastName == null) {
			lastName = sc.next();
		}
		firstName = firstName.toLowerCase();
		lastName = lastName.toLowerCase();

		for (int i = 0; i < participantList.size(); i++) {
			if (participantList.get(i).getLastName().toLowerCase().equals(lastName)
					&& participantList.get(i).getFirstName().toLowerCase().equals(firstName)) {
				return participantList.get(i);
			}
		}

		for (int i = 0; i < waitingList.size(); i++) {
			if (waitingList.get(i).getLastName().toLowerCase().equals(lastName)
					&& waitingList.get(i).getFirstName().toLowerCase().equals(firstName)) {
				return waitingList.get(i);
			}
		}

		return null;
	}

	private Guest searchListByEmail(String email) {
		if (email == null) {
			email = sc.next();
		}
		email = email.toLowerCase();

		for (int i = 0; i < participantList.size(); i++) {
			if (participantList.get(i).getEmail().toLowerCase().equals(email)) {
				return participantList.get(i);
			}
		}
		for (int i = 0; i < waitingList.size(); i++) {
			if (waitingList.get(i).getEmail().toLowerCase().equals(email)) {
				return waitingList.get(i);
			}
		}
		return null;
	}

	private Guest searchListByPhone(String phone) {
		if (phone == null) {
			phone = sc.next();
		}
		for (int i = 0; i < participantList.size(); i++) {
			if (participantList.get(i).getPhoneNumber().equals(phone)) {
				return participantList.get(i);
			}
		}
		for (int i = 0; i < waitingList.size(); i++) {
			if (waitingList.get(i).getPhoneNumber().equals(phone)) {
				return waitingList.get(i);
			}
		}
		return null;
	}
	
	private Guest searchByType(int type) {
		switch (type) {
		case 1:
			System.out.println("Introduceti numele de familie");
			String lastName = sc.next();
			System.out.println("Introduceti prenumele");
			String firstName = sc.next();
			// now we search
			return searchListByName(firstName, lastName);
		case 2:
			System.out.println("Introduceti emailul..");
			String email = sc.next();
			return searchListByEmail(email);
		case 3:
			System.out.println("Introduceti numarul de telefon..");
			String phone = sc.next();
			return searchListByPhone(phone);
		default:
			System.out.println("Metoda de autentificare este invalida");
			return null;
		}
	}

	private int readAuthenticationType() {
		System.out.println("Alege modul de autentificare, tastand:\n" 
							+ "\"1\" - Nume si prenume\n" 
							+ "\"2\" - Email\n"
							+ "\"3\" - Numar de telefon (format \"+40733386463\")");
		int authType = sc.nextInt();
		return authType;
	}

	/*
	 * public boolean searchAllList() {
	 * 
	 * System.out.println(
	 * "To search by lastName & firstName press 1 /n to search by email press 2 /n to search by phone press 3"
	 * ); Integer input = sc.nextInt();
	 * 
	 * if (input == 1) { System.out.println("Introdu prenume și nume: "); return
	 * searchListByName(null, null);
	 * 
	 * String firstName = sc.next(); String lastName = sc.next();
	 * 
	 * for (int i = 0; i < waitingList.size(); i++) { if
	 * (waitingList.get(i).getLastName() == lastName &&
	 * waitingList.get(i).getFirstName() == firstName) { return true; }
	 * 
	 * }
	 * 
	 * } else if (input == 2) { System.out.println("Introdu email:"); return
	 * searchListByEmail(null);
	 * 
	 * 
	 * String email = sc.next(); if (email.contains("@")) { for (int i = 0; i <
	 * waitingList.size(); i++) { if (waitingList.get(i).getEmail() == email) {
	 * return true; } }
	 * 
	 * 
	 * } else if (input == 3) { System.out.println("Introdu numar de telefon:");
	 * return searchListByPhone(null);
	 * 
	 * Long phone = sc.nextLong(); for (int i = 0; i < waitingList.size(); i++) { if
	 * (waitingList.get(i).getPhoneNumber() == phone) { return true; } } if
	 * (searchListByName() == true) { return true; }
	 * 
	 * } return false; }
	 */
	
	public Integer addParticipant(Guest x) {
		if (searchListByEmail(x.getEmail()) != null || searchListByName(x.getFirstName(), x.getLastName()) != null
				|| searchListByPhone(x.getPhoneNumber()) != null) {
			return -1;
		}

		if (participantList.size() < seats) {
			participantList.add(x);
			return 0;
		} else {
			waitingList.add(x);
			return waitingList.size();
		}
	}
	
	public boolean deleteParticipant() {
		System.out.println("Se sterge o persoana existenta din lista…");
		int type = readAuthenticationType();

		Guest guestToDelete = searchByType(type);
		
		if (guestToDelete == null) {
			return false;
		}
		for (int i = 0; i < participantList.size(); i++) {
			if (participantList.get(i).equals(guestToDelete)) {
				participantList.remove(i);
				
				if(waitingList.size() > 0) {
					Guest x = waitingList.get(0);
					participantList.add(x);
					waitingList.remove(0);
					System.out.println("["+x.getLastName().toUpperCase() + " " + x.getFirstName() + 
										"] Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
				}
				return true;
			}
		}
		for (int i = 0; i < waitingList.size(); i++) {
			if (waitingList.get(i).equals(guestToDelete)) {
				waitingList.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean updateContact() {
		System.out.println("Se actualizeaza detaliile unei persoane…");
		int type = readAuthenticationType();

		Guest guestToUpdate = searchByType(type);
		if (guestToUpdate == null) {
			System.out.println("Participantul nu a fost gasit");
			return false;
		}

		System.out.println("Alege campul de actualizat, tastand:\n" 
							+ "\"1\" - Nume\n" 
							+ "\"2\" - Prenume\n"
							+ "\"3\" - Email \n" 
							+ "\"4\" - Numar de telefon (format \"+40733386463\")");
		int updateOption = sc.nextInt();
		switch (updateOption) {
		case 1:
			System.out.println("Introduceti numele de familie:");
			String nume = sc.next();
			guestToUpdate.setLastName(nume);
			break;
		case 2:
			System.out.println("Introduceti prenumele:");
			String prenume = sc.next();
			guestToUpdate.setFirstName(prenume);
			break;
		case 3:
			System.out.println("Introduceti adresa de email:");
			String email = sc.next();
			try {
				guestToUpdate.setEmail(email);
			} catch (EmailException e) {
				System.out.println(e.toString());
				return false;
			}
			break;
		case 4:
			System.out.println("Introduceti numarul de telefon:");
			String phone = sc.next();
			guestToUpdate.setPhoneNumber(phone);
			break;
		default:
			System.out.println("Optiune invalida.");
			return false;
		}
		return true;
	}

	public boolean checkParticipant() {
		System.out.println("Se cauta detaliile unei persoane…");
		int type = readAuthenticationType();

		Guest guest = searchByType(type);
		if (guest == null) {
			return false;
		} else {
			return true;
		}
	}

	/*
	 * public boolean updateContact(Guest x) { for (int i = 0; i <
	 * participantList.size(); i++) { if (searchListByEmail(x.getEmail()) ||
	 * searchListByName(x.getFirstName(), x.getLastName()) ||
	 * searchListByPhone(x.getPhoneNumber())) {
	 * System.out.println("Ce camp doriti sa actualizati? " + "\n\t email press 1 "
	 * + "\n\t prenume, nume press 2 " + "\n\t numar telefon press 3"); int input =
	 * sc.nextInt(); if (input == 1) {
	 * System.out.println("Introduceti noul email: "); String newMail = sc.next();
	 * try { participantList.get(i).setEmail(newMail); } catch (EmailException e) {
	 * System.out.println(e.toString());
	 * System.out.println("Introduceti noul email: "); newMail = sc.next(); try {
	 * participantList.get(i).setEmail(newMail); } catch (EmailException e1) {
	 * System.out.println(e.toString()); return false; }
	 * 
	 * } return true; } if (input == 2) {
	 * System.out.println("Introduceti prenumne, nume"); String prenume = sc.next();
	 * String nume = sc.next(); participantList.get(i).setFirstName(prenume);
	 * participantList.get(i).setLastName(nume); return true; } if (input == 3) {
	 * System.out.println("Introduceti numar nou de telefon"); String telefon =
	 * sc.next(); participantList.get(i).setPhoneNumber(telefon); return true; } } }
	 * for (int i = 0; i < waitingList.size(); i++) { if
	 * (searchListByEmail(x.getEmail()) || searchListByName(x.getFirstName(),
	 * x.getLastName()) || searchListByPhone(x.getPhoneNumber())) {
	 * System.out.println("Ce camp doriti sa actualizati? " + "\n\t email press 1 "
	 * + "\n\t prenume, nume press 2 " + "\n\t numar telefon press 3"); int input =
	 * sc.nextInt(); if (input == 1) {
	 * System.out.println("Introduceti noul email: "); String newMail = sc.next();
	 * waitingList.get(i).setEmail(newMail); return true; } if (input == 2) {
	 * System.out.println("Introduceti prenumne, nume"); String prenume = sc.next();
	 * String nume = sc.next(); waitingList.get(i).setFirstName(prenume);
	 * waitingList.get(i).setLastName(nume); return true; } if (input == 3) {
	 * System.out.println("Introduceti numar nou de telefon"); String telefon =
	 * sc.next(); waitingList.get(i).setPhoneNumber(telefon); return true; } } }
	 * System.out.println("Contact nu există"); return false;
	 * 
	 * }
	 */
	
	public void participantListGuests() {
		for (int i = 0; i < participantList.size(); i++) {
			System.out.println(participantList.get(i).toString());
		}
	}

	public void waitingListGuest() {
		for (int i = 0; i < waitingList.size(); i++) {
			System.out.println(waitingList.get(i).toString());
		}
	}

	public int availableSpots() {
		int participantSize = seats - participantList.size();
		return participantSize;
	}

	public int participantSize() {
		int size = participantList.size();
		return size;
	}

	public int waitingListSize() {
		int size = waitingList.size();
		return size;
	}

	public int totalSize() {
		int total = participantList.size() + waitingList.size();
		return total;
	}

	private static ArrayList<Guest> searchInList(String x, ArrayList<Guest> guests){
		ArrayList<Guest> temp = new ArrayList<Guest>();
		
		for (int i = 0; i < guests.size(); i++) {
			Guest p = guests.get(i);
			if ((p.getEmail().toLowerCase().contains(x)) || (p.getFirstName().toLowerCase().contains(x))
					|| (p.getLastName().toLowerCase().contains(x)) || (p.getPhoneNumber().toLowerCase().contains(x))) {
				temp.add(p);
			}
		}
		return temp;
	}
	
	public ArrayList<Guest> search(String x) {
		x = x.toLowerCase();
		ArrayList<Guest> temp = new ArrayList<Guest>();
		ArrayList<Guest> participants = searchInList(x, participantList);
		ArrayList<Guest> onWait = searchInList(x, waitingList);
		temp.addAll(participants);
		temp.addAll(onWait);
		return temp;
	}
}
