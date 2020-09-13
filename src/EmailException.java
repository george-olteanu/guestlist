
public class EmailException extends Throwable{
	String str1;
	EmailException(String str2){
		str1 = str2;
	}
	public String toString() {
		return ("Formatul adresei de email " +str1 +" este gresit." );
	}

}
