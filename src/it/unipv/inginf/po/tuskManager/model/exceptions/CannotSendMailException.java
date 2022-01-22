package it.unipv.inginf.po.tuskManager.model.exceptions;

public class CannotSendMailException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String stringa = "Invio mail non riuscito.\nL'operazione richiesta tuttavia può essere andata a buon fine.";

	public CannotSendMailException(){
		super(String.format(stringa));
	}
}
