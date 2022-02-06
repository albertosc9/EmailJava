package practica1.sanchez.alberto;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Properties;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	
	
	Scanner read = new Scanner(System.in);

    private Properties prop;
    private Session sesion;
    private String user;
    private String password;

    public Mail(String ruta) throws IOException {
        this.prop = new Properties();
        loadConfig(ruta);

        sesion = Session.getDefaultInstance(prop);
    }

    private void loadConfig(String ruta) throws InvalidParameterException, IOException {
        InputStream is = new FileInputStream(ruta);
        this.prop.load(is);
        checkConfig();
    }

    private void checkConfig() throws InvalidParameterException {

        String[] keys = {
            "mail.smtp.host",
            "mail.smtp.port",
            "mail.smtp.starttls.enable",
            "mail.smtp.auth"
        };

        for (int i = 0; i < keys.length; i++) {
            if (this.prop.get(keys[i]) == null) {
                throw new InvalidParameterException("No existe la clave " + keys[i]);
            }
        }

    }


    public void enviarEmail(String asunto, String mensaje,String[] destinatarios,String []  ruta) throws MessagingException {

    	
    
    	
    	BodyPart texto = new MimeBodyPart();
    	texto.setText(mensaje);

    	MimeMultipart multiparte = new MimeMultipart();
    	
    	multiparte.addBodyPart(texto);
    	
    	for (int i = 0; i < ruta.length; i++) {
    		BodyPart adjunto = new MimeBodyPart();
    		FileDataSource archivo = new FileDataSource(ruta[i]);
    		
    		adjunto.setDataHandler(new DataHandler(archivo));
    		adjunto.setFileName(archivo.getName());
    		multiparte.addBodyPart(adjunto);
    		
		}
    	
    
        MimeMessage contenedor = new MimeMessage(sesion);
        contenedor.setFrom(new InternetAddress(user));
        
        for (int i = 0; i < destinatarios.length; i++) {
            contenedor.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios[i]));
        }
        contenedor.setSubject(asunto);
        contenedor.setContent(multiparte);
        

        
        Transport t = sesion.getTransport("smtp");
        t.connect(user,password);
        t.sendMessage(contenedor, contenedor.getAllRecipients());
        

    }

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
