package it.unipv.inginf.po.tuskManager.model.facade.utils;

public class PasswordCoder {
	public PasswordCoder() {
		
	}
	public static String codifica(String stringa) {
		String nuova;
		nuova = "";
		String alfabeto;
		alfabeto = "abcdefghijklmnopqrstuvxwyza";
		for(int i = 0; i < stringa.length();i++) {
			nuova = nuova + alfabeto.charAt(alfabeto.indexOf((stringa.charAt(i)))+1);
		}
		
		return nuova;
	}
}

