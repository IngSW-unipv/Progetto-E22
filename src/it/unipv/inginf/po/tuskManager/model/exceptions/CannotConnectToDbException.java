package it.unipv.inginf.po.tuskManager.model.exceptions;

public class CannotConnectToDbException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String stringa = "Impossibile reperire informazioni dalla piattaforma, riprovare più tardi.";

	public CannotConnectToDbException(){
		super(String.format(stringa));
	}
}
