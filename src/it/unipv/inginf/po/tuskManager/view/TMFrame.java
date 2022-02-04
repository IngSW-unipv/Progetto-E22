package it.unipv.inginf.po.tuskManager.view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.view.apertura.Accedi;
import it.unipv.inginf.po.tuskManager.view.apertura.Apertura;
import it.unipv.inginf.po.tuskManager.view.apertura.Registrati;
import it.unipv.inginf.po.tuskManager.view.homepage.Crea;
import it.unipv.inginf.po.tuskManager.view.homepage.HomePage;
import it.unipv.inginf.po.tuskManager.view.homepage.Seleziona;
import it.unipv.inginf.po.tuskManager.view.homepage.WindowWorkspace;
import it.unipv.inginf.po.tuskManager.view.modifica.Modifica;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.AggiungiCompito;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.AggiungiCompito2;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.ModificaCompito;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.ModificaCompito2;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.OperazioniCompito;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.RimuoviCompito;
import it.unipv.inginf.po.tuskManager.view.modifica.compito.SpostaCompito;
import it.unipv.inginf.po.tuskManager.view.modifica.membro.AggiungiMembro;
import it.unipv.inginf.po.tuskManager.view.modifica.membro.ModificaRuolo;
import it.unipv.inginf.po.tuskManager.view.modifica.membro.RimuoviMembro;
import it.unipv.inginf.po.tuskManager.view.modifica.ws.ModificaNome;
import it.unipv.inginf.po.tuskManager.view.modifica.ws.OperazioniWs;
import it.unipv.inginf.po.tuskManager.view.modifica.ws.RimuoviWs;

public class TMFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Apertura panel_apertura;
	private Accedi panel_accedi;
	private Registrati panel_registrati;
	
	private HomePage panel_homepage;
	private Seleziona panel_seleziona;
	private Crea panel_crea;
	private WindowWorkspace panel_workspace;
	
	private Modifica panel_modifica;
	
	private it.unipv.inginf.po.tuskManager.view.modifica.membro.Membro panel_membro;
	private AggiungiMembro panel_aggiungi_membro;
	private ModificaRuolo panel_modifica_ruolo;
	private RimuoviMembro panel_rimuovi_membro;
	
	private AggiungiCompito panel_aggiungi_compito;
	private AggiungiCompito2 panel_aggiungi_compito2;
	private ModificaCompito panel_modifica_compito;
	private ModificaCompito2 panel_modifica_compito2;
	private OperazioniCompito panel_operazioni_compito;
	private RimuoviCompito panel_rimuovi_compito;
	private SpostaCompito panel_sposta_compito;
	
	private ModificaNome panel_modifica_nome;
	private OperazioniWs panel_operazioni_ws;
	private RimuoviWs panel_rimuovi_ws;
	
	
	
	public TMFrame() {
		super();
		initFrame();
		seePanelApertura();
	}
	
	public void initFrame() {
		this.setTitle("Tusk Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(400,400);
//		this.setResizable(false);
//		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice device = graphics.getDefaultScreenDevice();
//		device.setFullScreenWindow(this);
		try {
			this.setIconImage(ImageIO.read(new File("assets/background.jpg")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}
	}
	
	public Apertura getPanel_apertura() {
		if(panel_apertura == null)
			panel_apertura = new Apertura();
		return panel_apertura;
	}

	public Accedi getPanel_accedi() {
		if(panel_accedi == null)
			panel_accedi = new Accedi();
		return panel_accedi;
	}

	public Registrati getPanel_registrati() {
		if(panel_registrati == null)
			panel_registrati = new Registrati();
		return panel_registrati;
	}
	
	public HomePage getPanel_homepage() {
		if(panel_homepage == null)
			panel_homepage = new HomePage();
		return panel_homepage;
	}
	
	public Crea getPanel_crea() {
		if(panel_crea == null)
			panel_crea = new Crea();
		return panel_crea;
	}
	
	public Seleziona getPanel_seleziona() {
		return panel_seleziona;
	}
	
	public WindowWorkspace getPanel_workspace() {
		return panel_workspace;
	}
	
	public Modifica getPanel_modifica() {
		if(panel_modifica == null)
			panel_modifica = new Modifica();
		return panel_modifica;
	}
	
	public it.unipv.inginf.po.tuskManager.view.modifica.membro.Membro getPanel_membro() {
		if(panel_membro == null)
			panel_membro = new it.unipv.inginf.po.tuskManager.view.modifica.membro.Membro();
		return panel_membro;
	}
	public AggiungiMembro getPanel_aggiungiMembro() {
		if(panel_aggiungi_membro == null)
			panel_aggiungi_membro = new AggiungiMembro();
		return panel_aggiungi_membro;
	}
	public ModificaRuolo getPanel_modificaRuolo() {
		if(panel_modifica_ruolo == null)
			panel_modifica_ruolo = new ModificaRuolo();
		return panel_modifica_ruolo;
	}
	public RimuoviMembro getPanel_rimuoviMembro() {
		if(panel_rimuovi_membro == null)
			panel_rimuovi_membro = new RimuoviMembro();
		return panel_rimuovi_membro;
	}
	
	public AggiungiCompito getPanel_aggiungiCompito() {
		if(panel_aggiungi_compito == null)
			panel_aggiungi_compito = new AggiungiCompito();
		return panel_aggiungi_compito;
	}
	public AggiungiCompito2 getPanel_aggiungiCompito2() {
		return panel_aggiungi_compito2;
	}
	public ModificaCompito getPanel_modificaCompito() {
		if(panel_modifica_compito == null)
			panel_modifica_compito = new ModificaCompito();
		return panel_modifica_compito;
	}
	public ModificaCompito2 getPanel_modificaCompito2() {
		return panel_modifica_compito2;
	}
	public OperazioniCompito getPanel_operazioniCompito() {
		if(panel_operazioni_compito == null)
			panel_operazioni_compito = new OperazioniCompito();
		return panel_operazioni_compito;
	}
	public RimuoviCompito getPanel_rimuoviCompito() {
		if(panel_rimuovi_compito == null)
			panel_rimuovi_compito = new RimuoviCompito();
		return panel_rimuovi_compito;
	}
	public SpostaCompito getPanel_spostaCompito() {
		if(panel_sposta_compito == null)
			panel_sposta_compito = new SpostaCompito();
		return panel_sposta_compito;
	}
	
	public ModificaNome getPanel_modificaNome() {
		if(panel_modifica_nome == null)
			panel_modifica_nome = new ModificaNome();
		return panel_modifica_nome;
	}
	public OperazioniWs getPanel_operazioniWs() {
		if(panel_operazioni_ws == null)
			panel_operazioni_ws = new OperazioniWs();
		return panel_operazioni_ws;
	}
	public RimuoviWs getPanel_rimuoviWs() {
		if(panel_rimuovi_ws == null)
			panel_rimuovi_ws = new RimuoviWs();
		return panel_rimuovi_ws;
	}
	
	
	public void seePanelApertura() {
		if(panel_apertura == null)
			panel_apertura = new Apertura();
		this.seePanel(panel_apertura);
	}
	
	public void seePanelAccedi() {
		if(panel_accedi == null)
			panel_accedi = new Accedi();
		this.seePanel(panel_accedi);
	}
	public void seePanelRegistrati() {
		if(panel_registrati == null)
			panel_registrati = new Registrati();
		this.seePanel(panel_registrati);
	}
	
	public void seePanelHomePage() {
		if(panel_homepage == null)
			panel_homepage = new HomePage();
		this.seePanel(panel_homepage);
	}
	public void seePanelWorkspace(Workspace w, Ruolo r) {
		panel_workspace = new WindowWorkspace(w, r);
		this.seePanel(panel_workspace);
	}
	public void seePanelSeleziona(ArrayList<String> a) {
		panel_seleziona = new Seleziona(a);
		this.seePanel(panel_seleziona);
	}
	public void seePanelCrea() {
		if(panel_crea == null)
			panel_crea = new Crea();
		this.seePanel(panel_crea);
	}
	
	public void seePanelModifica() {
		if(panel_modifica == null)
			panel_modifica = new Modifica();
		this.seePanel(panel_modifica);
	}
	
	public void seePanelMembro() {
		if(panel_membro == null)
			panel_membro = new it.unipv.inginf.po.tuskManager.view.modifica.membro.Membro();
		this.seePanel(panel_membro);
	}
	public void seePanelAggiungiMembro() {
		if(panel_aggiungi_membro == null)
			panel_aggiungi_membro = new AggiungiMembro();
		this.seePanel(panel_aggiungi_membro);
	}
	public void seePanelModificaRuolo() {
		if(panel_modifica_ruolo == null)
			panel_modifica_ruolo = new ModificaRuolo();
		this.seePanel(panel_modifica_ruolo);
	}
	public void seePanelRimuoviMembro() {
		if(panel_rimuovi_membro == null)
			panel_rimuovi_membro = new RimuoviMembro();
		this.seePanel(panel_rimuovi_membro);
	}
	
	public void seePanelAggiungiCompito() {
		if(panel_aggiungi_compito == null)
			panel_aggiungi_compito = new AggiungiCompito();
		this.seePanel(panel_aggiungi_compito);
	}
	public void seePanelAggiungiCompito2(String titolo, String descrizione, int g, int m, int a) {
		panel_aggiungi_compito2 = new AggiungiCompito2(titolo,descrizione,g,m,a);
		this.seePanel(panel_aggiungi_compito2);
	}
	public void seePanelModificaCompito() {
		if(panel_modifica_compito == null)
			panel_modifica_compito = new ModificaCompito();
		this.seePanel(panel_modifica_compito);
	}
	public void seePanelModificaCompito2(String vecchio, String titolo, String descrizione, Calendar cal) {
		panel_modifica_compito2 = new ModificaCompito2(vecchio, titolo,descrizione,cal);
		this.seePanel(panel_modifica_compito2);
	}
	public void seePanelOperazioniCompito() {
		if(panel_operazioni_compito == null)
			panel_operazioni_compito = new OperazioniCompito();
		this.seePanel(panel_operazioni_compito);
	}
	public void seePanelRimuoviCompito() {
		if(panel_rimuovi_compito == null)
			panel_rimuovi_compito = new RimuoviCompito();
		this.seePanel(panel_rimuovi_compito);
	}
	public void seePanelSpostaCompito() {
		if(panel_sposta_compito == null)
			panel_sposta_compito = new SpostaCompito();
		this.seePanel(panel_sposta_compito);
	}
	
	public void seePanelModificaNome() {
		if(panel_modifica_nome == null)
			panel_modifica_nome = new ModificaNome();
		this.seePanel(panel_modifica_nome);
	}
	public void seePanelOperazioniWs() {
		if(panel_operazioni_ws == null)
			panel_operazioni_ws = new OperazioniWs();
		this.seePanel(panel_operazioni_ws);
	}
	public void seePanelRimuoviWs() {
		if(panel_rimuovi_ws == null)
			panel_rimuovi_ws = new RimuoviWs();
		this.seePanel(panel_rimuovi_ws);
	}
	
	private void seePanel(JPanel p) {
		try {
			this.getContentPane().removeAll();
			this.add(p);
			this.revalidate();
			this.repaint();
		}catch(Exception ex) {
			ex.printStackTrace();
		}catch(Error e) {
			e.printStackTrace();
		}
	}

	
}
