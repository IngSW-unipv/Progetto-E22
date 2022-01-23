package it.unipv.inginf.po.tuskManager.model.utils;

public class PasswordCoder {
	public PasswordCoder() {
		
	}
	public static String codifica(String stringa) {
		stringa = stringa.replace('a', 'z');
		stringa = stringa.replace('b', 'y');
		stringa = stringa.replace('c', 'x');
		stringa = stringa.replace('d', 'w');
		stringa = stringa.replace('e', 'v');
		stringa = stringa.replace('f', 'u');
		stringa = stringa.replace('g', 't');
		stringa = stringa.replace('h', 's');
		stringa = stringa.replace('i', 'r');
		stringa = stringa.replace('j', 'q');
		stringa = stringa.replace('k', 'p');
		stringa = stringa.replace('l', 'o');
		stringa = stringa.replace('m', 'n');
		
		return stringa;
	}
}

