package it.unipv.inginf.po.tuskManager.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;

import it.unipv.inginf.po.tuskManager.model.TaskManager;
import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.view.TMFrame;

public class Controller {


	private TMFrame frame;
	private TaskManager tm;
	
	public Controller(TMFrame frame, TaskManager tm) {
		this.frame = frame;
		this.tm = tm;
		initListeners();
	}

	private void initListeners() {
		initListenersApertura();
		initListenersHomePage();
		initListenersModifica();
	}

	private void initListenersApertura() {
		initListenersPanelApertura();
		initListenersPanelAccedi();
		initListenersPanelRegistrati();
	}
	private void initListenersHomePage() {
		initListenersPanelHomePage();
		initListenersPanelCrea();
	}
	private void initListenersModifica() {
		initListenersPanelModifica();
		initListenersMembro();
		initListenersWorkspace();
		initListenersCompito();
	}
	private void initListenersMembro() {
		initListenersPanelMembro();
		initListenersPanelAggiungiMembro();
		initListenersPanelModificaRuolo();
		initListenersPanelRimuoviMembro();
	}
	
	private void initListenersWorkspace() {
		initListenersPanelOperazioniWs();
		initListenersPanelModificaNome();
		initListenersPanelRimuoviWs();
	}
	
	private void initListenersCompito() {
		initListenersPanelAggiungiCompito();
		initListenersPanelModificaCompito();
		initListenersPanelOperazioniCompito();
		initListenersPanelRimuoviCompito();
		initListenersPanelSpostaCompito();
	}
	
	private void initListenersPanelApertura() {
		//bottone accedi nel panel apertura:
		frame.getPanel_apertura().getBottoneAccedi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelAccedi();
			}
		});
		//bottone registrati nel panel apertura:
		frame.getPanel_apertura().getBottoneRegistrati().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelRegistrati();
			}
		});
		//bottone esci nel panel apertura:
		frame.getPanel_apertura().getBottoneEsci().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
	
	private void initListenersPanelAccedi() {
		//bottone invia nel panel accedi:
		frame.getPanel_accedi().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = frame.getPanel_accedi().getEmail().getText();
				String pw = String.copyValueOf(frame.getPanel_accedi().getPw().getPassword());
				try {
					if(tm.login(email, pw)) {
						frame.seePanelHomePage(); 
					}else {
						// QUA POP UP CREDENZIALI ERRATE
					}
				} catch (CannotConnectToDbException e1) {
					e1.printStackTrace(); //QUA POP UP DB SPENTO
				}
			}
		});
		//bottone indietro nel panel accedi:
		frame.getPanel_accedi().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelApertura();
			}
		});
	}
	
	private void initListenersPanelRegistrati() {
		//bottone indietro nel panel registrati:
		frame.getPanel_registrati().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelApertura();
			}
		});
		//bottone invia nel panel registrati:
		frame.getPanel_registrati().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = frame.getPanel_registrati().getEmail().getText();
				String pw = String.copyValueOf(frame.getPanel_registrati().getPw().getPassword());
				String pw_conf = String.copyValueOf(frame.getPanel_registrati().getPwConferma().getPassword());
				
				if(!pw.equals(pw_conf)) {
					//QUA POP UP PASSWORD DIVERSE
					return;
				}
				try {
					if(tm.createAccount(email, pw_conf)) {
						frame.seePanelHomePage();
					}
					else {
						//QUA POP UP EMAIL GIA USATA
					}
				} catch (CannotConnectToDbException e1) {
					e1.printStackTrace();// QUA POP UP DB SPENTO
				}
			}
		});
	}
	
	private void initListenersPanelHomePage() {
		//bottone logout nel panel home page:
		frame.getPanel_homepage().getBottoneLogout().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tm.logout();
				frame.seePanelApertura();
			}
		});
		
		//bottone crea workspace nel panel home page:
		frame.getPanel_homepage().getBottoneCrea().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelCrea();
			}
		});
		
		//bottone seleziona nel panel home page:
		frame.getPanel_homepage().getBottoneSeleziona().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Workspace> lista_ws = new ArrayList<Workspace>();
				ArrayList<String> lista_nomi = new ArrayList<String>();
				try {
					lista_ws = tm.getAllWorkspaces();
				} catch (CannotConnectToDbException e1) {
					e1.printStackTrace(); //POP UP DB SPENTO
				}
				for(Workspace w : lista_ws) {
					lista_nomi.add(w.getNome());
				}
				frame.seePanelSeleziona(lista_nomi);
				initListenersPanelSeleziona();
			}
		});
		
	}
	
	private void initListenersPanelCrea() {
		//bottone invia nel panel crea:
		frame.getPanel_crea().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(frame.getPanel_crea().getNome().getText().isEmpty())
						return;
					tm.createWorkspace(frame.getPanel_crea().getNome().getText());
					tm.createScheda(new Scheda("TO DO",null));
					tm.createScheda(new Scheda("DOING",null));
					tm.createScheda(new Scheda("DONE",null));
					frame.seePanelWorkspace(tm.getWorkspace(new Workspace(0,frame.getPanel_crea().getNome().getText(),null,null)),tm.getMembroLogged().getRuolo());
					initTempListenersPanelWorkspace();
				} catch (CannotConnectToDbException | RoleNotAcceptedException e1) {
					e1.printStackTrace();//POP UP DB SPENTO
				}
			}
		});
		
		//bottone indietro nel panel crea:
		frame.getPanel_crea().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelHomePage();
			}
		});
	}
	
	private void initListenersPanelSeleziona() {
		//bottone indietro nel panel seleziona:
		frame.getPanel_seleziona().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelHomePage();
			}
		});
		//bottoni workspaces nel panel seleziona:
		for(JButton b : frame.getPanel_seleziona().getAllBottoni()){
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						frame.seePanelWorkspace(tm.getWorkspace(new Workspace(0,b.getText(),null,null)),tm.getMembroLogged().getRuolo());
						initTempListenersPanelWorkspace();
					} catch (CannotConnectToDbException e1) {
						e1.printStackTrace();//POP UP DB SPENTO
					}
				}
			});
		}
	}
	
	private void initTempListenersPanelWorkspace() {
		//bottone indietro nel panel workspace:
		frame.getPanel_workspace().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelHomePage();
			}
		});
		//bottone operazioni nel panel workspace:
		frame.getPanel_workspace().getBottoneOperazioni().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
						frame.seePanelModifica();
			}
		});
		//bottoni compiti
		for (JButton b : frame.getPanel_workspace().getAllBottoni()) {
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Compito c = null;
					try {
						for(Scheda s : tm.getWorkspace().getLista_schede()) {
							for(Compito com : s.getCompiti()) {
								if(com.getTitolo().equals(b.getText())) {//no compiti con stesso titolo in un workspace
									c = com;
								}
							}
						}
						if(c!= null) {
							//QUA POP UP CON INFO DEL COMPITO, info compito salvate in c
							System.out.println(c.getTitolo());
						}
						
					} catch (CannotConnectToDbException e1) {
						e1.printStackTrace();//POP UP DB SPENTO
					}
				}
			});
		}
	}

	private void initListenersPanelModifica() {
		//bottone indietro nel panel modifica:
		frame.getPanel_modifica().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					frame.seePanelWorkspace(tm.getWorkspace(),tm.getMembroLogged().getRuolo());
					initTempListenersPanelWorkspace();
				} catch (CannotConnectToDbException e1) {
					e1.printStackTrace();//POP UP DB SPENTO
				}
			}
		});
		//bottone modifica membri nel panel modifica:
		frame.getPanel_modifica().getBottoneMembro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelMembro();
			}
		});
		//bottone modifica compito nel panel modifica:
		frame.getPanel_modifica().getBottoneCompito().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone modifica workspace nel panel modifica:
		frame.getPanel_modifica().getBottoneWorkspace().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniWs();
			}
		});
	}
	
	private void initListenersPanelMembro() {
		//bottone aggiungi membro nel panel membro:
		frame.getPanel_membro().getBottoneAggiungi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelAggiungiMembro();
			}
		});
		//bottone indietro nel panel membro:
		frame.getPanel_membro().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModifica();
			}
		});
		//bottone rimuovi membro nel panel membro:
		frame.getPanel_membro().getBottoneRimuovi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelRimuoviMembro();
			}
		});
		//bottone modifica ruolo membro nel panel membro:
		frame.getPanel_membro().getBottoneRuolo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModificaRuolo();
			}
		});
	}

	private void initListenersPanelAggiungiMembro() {
		//bottone invia nel panel aggiungi membro:
		frame.getPanel_aggiungiMembro().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(tm.addMembro(frame.getPanel_aggiungiMembro().getEmail().getText(), new Ruolo(frame.getPanel_aggiungiMembro().getRuolo().getText()))) {
						//POP UP AGGIUNTO MEMBRO
						frame.seePanelModifica();
					}else {
						//POP UP NON AGGIUNTO MEMBRO
						frame.seePanelModifica();
					}
				} catch (RoleNotAcceptedException | CannotSendMailException | CannotConnectToDbException e1) {
					// POP UP VARI
					e1.printStackTrace();
				}
			}
		});
		//bottone indetro nel panel aggiungi membro:
		frame.getPanel_aggiungiMembro().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelMembro();
			}
		});
	}

	private void initListenersPanelModificaRuolo() {
		//bottone invia nel panel modifica ruolo:
		frame.getPanel_modificaRuolo().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(tm.modifyMembro(frame.getPanel_aggiungiMembro().getEmail().getText(), new Ruolo(frame.getPanel_aggiungiMembro().getRuolo().getText()))) {
						//POP UP MODIFICATO MEMBRO
						frame.seePanelModificaRuolo();
					}else {
						//POP UP NON MODIFICATO MEMBRO
					}
				} catch (RoleNotAcceptedException | CannotSendMailException | CannotConnectToDbException e1) {
					// POP UP VARI
					e1.printStackTrace();
				}
			}
		});
		//bottone indetro nel panel modifica ruolo:
		frame.getPanel_modificaRuolo().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModificaRuolo();
			}
		});
	}

	private void initListenersPanelRimuoviMembro() {
		//bottone invia nel panel rimuovi membro:
		frame.getPanel_rimuoviMembro().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(tm.removeMembro(new Membro(frame.getPanel_rimuoviMembro().getEmail().getText(),null,null))) {
						//POP UP RIMOZIONE FATTA
						frame.seePanelMembro();
					}else {
						//POP UP NON RIMOSSO MEMBRO
					}
				} catch (RoleNotAcceptedException | CannotSendMailException | CannotConnectToDbException e1) {
					// POP UP VARI
					e1.printStackTrace();
				}
			}
		});
		//bottone indietro nel panel rimuovi ruolo:
		frame.getPanel_rimuoviMembro().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelMembro();
			}
		});
	}
	
	private void initListenersPanelOperazioniWs() {
		//bottone indietro nel panel operazioni ws:
		frame.getPanel_operazioniWs().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModifica();
			}
		});
		//bottone  nome nel panel operazioni ws:
		frame.getPanel_operazioniWs().getBottoneNome().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModificaNome();
			}
		});
		//bottone rimuovi nel panel operazioni ws:
		frame.getPanel_operazioniWs().getBottoneRimuovi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelRimuoviWs();
			}
		});
	}
	
	private void initListenersPanelModificaNome() {
		//bottone indietro nel panel modifica nome:
		frame.getPanel_modificaNome().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniWs();
			}
		});
		//bottone invia nel panel modifica nome:
		frame.getPanel_modificaNome().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(tm.modifyWorkspace(tm.getWorkspace(), new Workspace(0,frame.getPanel_modificaNome().getNome().getText(),null,null))) {
						//POP UP MODIFICA NOME WS RIUSCITA
						frame.seePanelOperazioniWs();
					}
					else {
						//POP UP MODIFICA NOME WS NON RIUSCITA
						frame.seePanelModificaNome();
					}
				} catch (RoleNotAcceptedException | CannotConnectToDbException e1) {
					e1.printStackTrace(); //POP UP DB SPENTO
				}
			}
		});
	}

	private void initListenersPanelRimuoviWs() {
		//bottone indietro nel panel rimuovi ws:
		frame.getPanel_rimuoviWs().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniWs();
			}
		});
		//bottone invia nel panel rimuovi ws:
		frame.getPanel_rimuoviWs().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(frame.getPanel_rimuoviWs().getNome().getText().equals(tm.getWorkspace().getNome())) {
						//POP UP ELIMINAZIONE RIUSCITA
						frame.seePanelOperazioniWs();
					}else {
						//POP UP ELIMINAZIONE NON RIUSCITA
						frame.seePanelRimuoviWs();
					}
				} catch (CannotConnectToDbException e1) {
					e1.printStackTrace();//POP UP DB SPENTO
				}
			}
		});
	}
	
	private void initListenersPanelAggiungiCompito() {
		//bottone indietro nel panel aggiungi compito:
		frame.getPanel_aggiungiCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone invia nel panel aggiungi compito:
		frame.getPanel_aggiungiCompito().getBottoneAvanti().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				String titolo = frame.getPanel_aggiungiCompito().getTitolo().getText();
				String descrizione = frame.getPanel_aggiungiCompito().getDescrizione().getText();
				Calendar scadenza = frame.getPanel_aggiungiCompito().getScadenza();
				int giorno = scadenza.get(Calendar.DAY_OF_MONTH);
				int mese = scadenza.get(Calendar.MONTH);
				int anno = scadenza.get(Calendar.YEAR);
				frame.seePanelAggiungiCompito2(titolo,descrizione,giorno,mese,anno);
				initListenersPanelAggiungiCompito2();
				}catch(Exception ex) {
					//POP UP COMPITO NON AGGIUNTO
					frame.seePanelModifica();
				}
			}
		});
		
	}

	private void initListenersPanelAggiungiCompito2() {
		//bottone indietro nel panel aggiungi compito 2 :
		frame.getPanel_aggiungiCompito2().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone aggiungi ruolo nel panel aggiungi compito2:
		frame.getPanel_aggiungiCompito2().getBottoneAggiungi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_aggiungiCompito2().addRuolo(frame.getPanel_aggiungiCompito2().getRuolo().getText());
				frame.getPanel_aggiungiCompito2().getRuolo().setText("");
			}
		});
		//bottone invia nel panel aggiungi compito2:
		frame.getPanel_aggiungiCompito2().getBottoneFine().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.set(frame.getPanel_aggiungiCompito2().getAnno(), frame.getPanel_aggiungiCompito2().getMese(), frame.getPanel_aggiungiCompito2().getGiorno());
				Date scadenza = new Date(cal.getTimeInMillis());
				ArrayList<Ruolo> ruoli = new ArrayList<Ruolo>();
				for(String s : frame.getPanel_aggiungiCompito2().getLista()) {
					ruoli.add(new Ruolo(s));
				}
				
				try {//se non ci sono le tre schede todo doing e done QUA CAMBIARE
					if(tm.createCompito(tm.getWorkspace().getLista_schede().get(2), new Compito(frame.getPanel_aggiungiCompito2().getTitolo(),frame.getPanel_aggiungiCompito2().getDescrizione(),scadenza,ruoli))) {
						//POP UP COMPITO CREATO
						frame.seePanelOperazioniCompito();
					}else {
						//POP UP COMPITO NON CREATO
						frame.seePanelOperazioniCompito();
					}
				} catch (RoleNotAcceptedException | CannotConnectToDbException e1) {
					// POP UP DB SPENTO
					e1.printStackTrace();
				}
			}
		});
	}

	private void initListenersPanelOperazioniCompito() {
		//bottone indietro nel panel operazioni compito :
		frame.getPanel_operazioniCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModifica();
			}
		});
		//bottone aggiungi compito nel panel operazioni compito :
		frame.getPanel_operazioniCompito().getBottoneAggiungi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelAggiungiCompito();
			}
		});
		//bottone rimuovi compito nel panel operazioni compito :
		frame.getPanel_operazioniCompito().getBottoneRimuovi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelRimuoviCompito();;
			}
		});
		//bottone modifica compito nel panel operazioni compito :
		frame.getPanel_operazioniCompito().getBottoneModifica().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelModificaCompito();
			}
		});
		//bottone sposta compito nel panel operazioni compito :
		frame.getPanel_operazioniCompito().getBottoneSposta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelSpostaCompito();
			}
		});
	}

	private void initListenersPanelRimuoviCompito() {
		//bottone indietro compito nel panel rimuovi compito :
		frame.getPanel_rimuoviCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone invia compito nel panel rimuovi compito :
		frame.getPanel_rimuoviCompito().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {//n.b: un solo compito con quel titolo in tutto il ws
					if(tm.removeCompito(new Compito(frame.getPanel_rimuoviCompito().getTitolo().getText(),null,null,null))) {
						//POP UP RIMOZIONE COMPLETATA
						frame.seePanelOperazioniCompito();
					}else {
						//POP UP RIMOZIONE FALLITA
						frame.seePanelOperazioniCompito();
					}
				} catch (RoleNotAcceptedException | CannotConnectToDbException e1) {
					//POP UP DB SPENTO
					e1.printStackTrace();
				}
			}
		});
	}

	private void initListenersPanelSpostaCompito() {
		//bottone indietro nel panel sposta compito :
		frame.getPanel_spostaCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone invia  nel panel sposta compito :
		frame.getPanel_spostaCompito().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Scheda sc_vecchia = null;
					Scheda sc_nuova = null;
					Workspace w = tm.getWorkspace();
					for(Scheda s : w.getLista_schede()) {
						for(Compito c : s.getCompiti()) {
							if(c.getTitolo().equals(frame.getPanel_spostaCompito().getCompito().getText())) {
								s.removeCompito(new Compito(frame.getPanel_spostaCompito().getCompito().getText(),null,null,null));
								sc_vecchia = s;
							}
						}
						if(s.getTitolo().equals(frame.getPanel_spostaCompito().getScheda().getText())) {
							s.addCompito(new Compito(frame.getPanel_spostaCompito().getCompito().getText(),null,null,null));
							sc_nuova = s;
						}
					}
					if(tm.modifyScheda(sc_vecchia, sc_nuova)) {
						//POP UP COMPITO SPOSTATO
						frame.seePanelOperazioniCompito();
					}else {
						//POP UP SPOSTAMENTO FALLITO
						frame.seePanelOperazioniCompito();
					}
				} catch (CannotConnectToDbException e1) {
					// POP UP DB SPENTO
					e1.printStackTrace();
				}
			}
		});
	}

	private void initListenersPanelModificaCompito() {
		//bottone indietro nel panel modifica compito :
		frame.getPanel_modificaCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone invia nel panel modifica compito :
		frame.getPanel_modificaCompito().getBottoneAvanti().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String titolo_vecchio = frame.getPanel_modificaCompito().getTitoloVecchio().getText();
				String titolo = frame.getPanel_modificaCompito().getTitolo().getText();
				String descrizione = frame.getPanel_modificaCompito().getDescrizione().getText();
				Calendar scadenza = frame.getPanel_modificaCompito().getScadenza();
				frame.seePanelModificaCompito2(titolo_vecchio,titolo,descrizione,scadenza);
				initListenersPanelModificaCompito2();
			}
		});
	}

	private void initListenersPanelModificaCompito2() {
		//bottone indietro nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelOperazioniCompito();
			}
		});	
		//bottone aggiungi nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneAggiungi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaCompito2().addRuolo(new Ruolo(frame.getPanel_modificaCompito2().getRuolo().getText()));
				frame.getPanel_modificaCompito2().getRuolo().setText("");
			}
		});
		//bottone aggiungi nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneRimuovi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaCompito2().removeRuolo(new Ruolo(frame.getPanel_modificaCompito2().getRuolo().getText()));
				frame.getPanel_modificaCompito2().getRuolo().setText("");
			}
		});	
		//bottone aggiungi nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneFine().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Scheda sc = null;
					Compito compito_con_nuova_lista_ruoli = null;
					Workspace w = tm.getWorkspace();
					for(Scheda s : w.getLista_schede()) {
						for(Compito c : s.getCompiti()) {
							if(c.getTitolo().equals(frame.getPanel_modificaCompito2().getTitoloVecchio())) {
								sc = s;
								
								for(Ruolo r : frame.getPanel_modificaCompito2().getRuoliDaTogliere()) {
									try{
										c.removeRuolo(r);
									}catch(Exception ex) {
									}
								}
								for(Ruolo r : frame.getPanel_modificaCompito2().getRuoliDaAggiungere()) {
									c.addRuolo(r);
								}
								compito_con_nuova_lista_ruoli = c;
							}
						}
					}
					String tit = frame.getPanel_modificaCompito2().getTitolo();
					String desc = frame.getPanel_modificaCompito2().getDescrizione();
					Date scad = new Date(frame.getPanel_modificaCompito2().getScadenza().getTimeInMillis());
					
					if(tm.modifyCompito(sc, new Compito(frame.getPanel_modificaCompito2().getTitoloVecchio(), null,null,null),new Compito(tit,desc,scad,compito_con_nuova_lista_ruoli.getRuoli()))) {
						//POP UP COMPITO MODIFICATO
						frame.seePanelOperazioniCompito();
					}else {
						//POP UP MODIFICA FALLITA
						frame.seePanelOperazioniCompito();
					}
				} catch (CannotConnectToDbException | RoleNotAcceptedException e1) {
					// POP UP DB SPENTO
					e1.printStackTrace();
				}
			}
		});	
	}
}

