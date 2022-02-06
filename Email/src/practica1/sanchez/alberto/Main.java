package practica1.sanchez.alberto;

import java.io.IOException;
import java.security.InvalidParameterException;

import javax.mail.MessagingException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String [] correos = {"asc.199359@gmail.com","martinperezj045@gmail.com"};
		String [] archivos = {"numero.txt","lineaEstacion.xml" };
		        try {
		            Mail email = new Mail("Prop.properties");

		          
		            email.enviarEmail("Hola", "Prueba de envio de correos",archivos);

		            System.out.println("enviado");
		            
		        } catch (InvalidParameterException | IOException | MessagingException ex) {
		            System.out.println(ex.getMessage());
		        }

		    }
		
		
	}


