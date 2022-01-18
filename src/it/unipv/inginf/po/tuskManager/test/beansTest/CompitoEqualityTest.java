package it.unipv.inginf.po.tuskManager.test.beansTest;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;

public class CompitoEqualityTest {
	
	Compito primo_compito, secondo_compito;
	int counter_test;
	@Before
	public void initTest() {
		ArrayList<Ruolo> prima_lista_ruoli = new ArrayList<Ruolo>();
		ArrayList<Ruolo> seconda_lista_ruoli = new ArrayList<Ruolo>();
		
		primo_compito = new Compito("tit","descr",new Date(), prima_lista_ruoli);
		secondo_compito = new Compito("tit","descr",new Date(), seconda_lista_ruoli);
		
		counter_test = 1;
	}
	
	@Test
	public void test() {
		// 1: compito uguale a se stesso:
		testa(primo_compito,primo_compito,true);
		
		// 2: liste ruoli (di istanze) diverse ma entrambe vuote:
		testa(primo_compito,secondo_compito,true);
		
			Ruolo r = new Ruolo("nomeruolo");
			primo_compito.addRuolo(r);
		// 3: liste ruoli diverse:
		testa(primo_compito,secondo_compito,false);
		
			secondo_compito.addRuolo(r);
		// 4: liste ruoli uguali ma non vuote:
		testa(primo_compito,secondo_compito,true);
		
			Ruolo r1 = new Ruolo("nomeruolo");
			secondo_compito.removeRuolo(r);
			secondo_compito.addRuolo(r1);
		// 5: liste contententi stessi ruoli ma di diverse istanze:
		testa(primo_compito,secondo_compito,true);
		
			r1.setNome("nomeruolodiverso");
			secondo_compito.addRuolo(r);
			primo_compito.addRuolo(r1);
		// 6: liste stessi ruoli ma in ordine diverso:
		testa(primo_compito,secondo_compito,true);
		
			secondo_compito.setScadenza(new Date((long) 328384836));
		// 7: scadenze diverse:
		testa(primo_compito,secondo_compito,false);
			
			secondo_compito.setScadenza(primo_compito.getScadenza());
			secondo_compito.setDescrizione("descrdiversa");
		// 8: descrizioni diverse:
		testa(primo_compito,secondo_compito,false);
		
			secondo_compito.setDescrizione(primo_compito.getDescrizione());
			secondo_compito.setTitolo("titolodiverso");
		// 9: titoli diversi:
		testa(primo_compito,secondo_compito,false);
			
			secondo_compito.setTitolo(primo_compito.getTitolo());
			
/*
 * Se si vogliono aggiungere altri test ricordarsi di numerarli nel commento.
 * Inoltre tenere presente che a questo punto del test primo_compito e secondo_compito sono uguali!
 */ 
	}
	
	private void testa(Compito c1, Compito c2, boolean expected) {
		boolean res;
		res = c1.equals(c2);
		Assert.assertTrue("Fail nel test numero: " + counter_test,res == expected);
		counter_test++;
	}

}
