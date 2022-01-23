package it.unipv.inginf.po.tuskManager.model.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Classe che permette di inviare mail con Java.
 * @author "lancill@gmail.com"
 * @version 1.0
 * */
public class EmailSender {
	  private String user;
	  private String password;
	  private String host;
	  private String mittente;
	  private String destinatario;
	  private String oggetto;
	  private String contenuto;
	  
	  /**
	  * Costruttore completo, richiede i parametri
	  * di connessione al server di posta.
	  * @param user
	  * @param password
	  * @param host
	  * @param mittente
	  * @param destinatari
	  * @param oggetto
	  * @param allegati
	  */
	  public EmailSender(String user, String password, String host, 
	                     String mittente, String destinatario, 
	                     String oggetto, String contenuto){

	    this.user = user;
	    this.password = password;
	    this.host = host;
	    this.mittente = mittente;
	    this.destinatario = destinatario;
	    this.oggetto = oggetto;
	    this.contenuto = contenuto;
//	    this.allegato = allegato;
	  }

	  /**
	   * Metodo che si occupa dell'invio effettivo della mail.
	   * */
	  public boolean inviaEmail() throws AddressException, NoSuchProviderException, MessagingException{
	    int port = 465; //porta 25 per non usare SSL

	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.user", mittente);
	    props.put("mail.smtp.host", host);
	    props.put("mail.smtp.port", port);

	    // commentare la riga seguente per non usare SSL 
	    props.put("mail.smtp.starttls.enable","true");
	    props.put("mail.smtp.socketFactory.port", port);

	    // commentare la riga seguente per non usare SSL 
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");

	    Session session = Session.getInstance(props, null);
	    session.setDebug(false);

	    // Creazione delle BodyParts del messaggio
	    MimeBodyPart messageBodyPart1 = new MimeBodyPart();
	    MimeBodyPart messageBodyPart2 = new MimeBodyPart();

	    try{
	      // COSTRUZIONE DEL MESSAGGIO
	      Multipart multipart = new MimeMultipart();
	      MimeMessage msg = new MimeMessage(session);

	      // header del messaggio
	      msg.setSubject(oggetto);
	      msg.setSentDate(new Date());
	      msg.setFrom(new InternetAddress(mittente));

	      // destinatario
	      msg.addRecipient(Message.RecipientType.TO,
	      new InternetAddress(destinatario));

	      // corpo del messaggio
	      messageBodyPart1.setText(contenuto);
	      multipart.addBodyPart(messageBodyPart1);

	      // allegato al messaggio
//	      DataSource source = new FileDataSource(allegato);
//	      messageBodyPart2.setDataHandler(new DataHandler(source));
//	      messageBodyPart2.setFileName(allegato);
//	      multipart.addBodyPart(messageBodyPart2);

	      // inserimento delle parti nel messaggio
	      msg.setContent(multipart);

	      Transport transport = session.getTransport("smtps"); //("smtp") per non usare SSL
	      transport.connect(host, user, password);
	      transport.sendMessage(msg, msg.getAllRecipients());
	      transport.close();

	      return true;

	    }catch(AddressException ae) {
	      ae.printStackTrace();
	      return false;
	    }catch(NoSuchProviderException nspe){
	      nspe.printStackTrace();
	      return false;
	    }catch(MessagingException me){
	      me.printStackTrace();
	      return false;
	    }
	  }
	}