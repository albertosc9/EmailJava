package practica1.sanchez.alberto;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Scanner;

import javax.mail.MessagingException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner read = new Scanner(System.in);
		
		
		String [] archivos = {"numero.txt","lineaEstacion.xml" };
		        try {
		            Mail email = new Mail("Prop.properties");
		        	System.out.println("introduce tu email");
		        	email.setUser(read.next());
		        	System.out.println("introduce tu contraseña");
		        	email.setPassword(read.next());
		        	System.out.println("introduce los correos separados por coma");
		            String correos = read.next();
		              
		             String [] destinatarios = correos.split(",");
		              
		            email.enviarEmail("Hola", "Prueba de envio de correos",destinatarios,archivos);

		            System.out.println("enviado");
		            
		        } catch (InvalidParameterException | IOException | MessagingException ex) {
		            System.out.println(ex.getMessage());
		        }

		    }
		
		
	}


