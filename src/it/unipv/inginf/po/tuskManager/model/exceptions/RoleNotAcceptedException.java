package it.unipv.inginf.po.tuskManager.model.exceptions;

public class RoleNotAcceptedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String stringa = "Autorizzazione negata: ruolo non adatto.";

	public RoleNotAcceptedException(){
		super(String.format(stringa));
	}
}
