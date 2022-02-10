package it.unipv.inginf.po.tuskManager.controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import it.unipv.inginf.po.tuskManager.model.beans.Membro;
import it.unipv.inginf.po.tuskManager.model.beans.Compito;
import it.unipv.inginf.po.tuskManager.model.beans.Ruolo;
import it.unipv.inginf.po.tuskManager.model.beans.Scheda;
import it.unipv.inginf.po.tuskManager.model.beans.Workspace;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotConnectToDbException;
import it.unipv.inginf.po.tuskManager.model.exceptions.CannotSendMailException;
import it.unipv.inginf.po.tuskManager.model.exceptions.RoleNotAcceptedException;
import it.unipv.inginf.po.tuskManager.model.facade.ITaskManager;
import it.unipv.inginf.po.tuskManager.view.TMFrame;
import it.unipv.inginf.po.tuskManager.view.utils.JTuskButton;
public class Controller {


	private TMFrame frame;
	private ITaskManager tm;
	
	public Controller(TMFrame frame, ITaskManager tm) {
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
		initListenersPanelImpostazioni();
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
		//bottone impostazioni nel panel apertura:
		frame.getPanel_apertura().getBottoneImpostazioni().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelImpostazioni();
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
	
	private void initListenersPanelImpostazioni() {
		//bottone indietro nel panel impostazioni:
		frame.getPanel_impostazioni().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.seePanelApertura();
			}
		});
		//bottoni dei colori
		for(JTuskButton b : frame.getPanel_impostazioni().getAllBottoni()) {
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.getPanel_impostazioni().selectColor(b.getText());
					JOptionPane.showMessageDialog(frame, "modifica applicata, per renderla visibile riavviare l'applicazione");
					frame.seePanelApertura();
				}
			});
		}
		
		
	}
	
	private void initListenersPanelAccedi() {
		//bottone invia nel panel accedi:
		frame.getPanel_accedi().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = frame.getPanel_accedi().getEmail().getText();
				String pw = String.copyValueOf(frame.getPanel_accedi().getPw().getPassword());
				frame.getPanel_accedi().getEmail().reset();
				frame.getPanel_accedi().getPw().reset();
				try {
					if(tm.login(email, pw)) {
						frame.getPanel_accedi().getEmail().reset();
						frame.getPanel_accedi().getPw().reset();
						frame.seePanelHomePage(); 
					}else {
						
						JOptionPane.showMessageDialog(frame, "credenziali errate");
					}
				} catch (CannotConnectToDbException e1) {
					frame.getPanel_accedi().getEmail().reset();
					frame.getPanel_accedi().getPw().reset();
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}
			}
		});
		//bottone indietro nel panel accedi:
		frame.getPanel_accedi().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_accedi().getEmail().reset();
				frame.getPanel_accedi().getPw().reset();
				frame.seePanelApertura();
			}
		});
	}
	
	private void initListenersPanelRegistrati() {
		//bottone indietro nel panel registrati:
		frame.getPanel_registrati().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_registrati().getEmail().reset();
				frame.getPanel_registrati().getPw().reset();
				frame.getPanel_registrati().getPwConferma().reset();
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
					JOptionPane.showMessageDialog(frame, "le password non coincidono");
					return;
				}
				try {
					if(tm.createAccount(email, pw_conf)) {
						JOptionPane.showMessageDialog(frame, "registrazione effettuata, per iniziare ad usare l'applicazione effettuare l'accesso");
						frame.getPanel_registrati().getEmail().reset();
						frame.getPanel_registrati().getPw().reset();
						frame.getPanel_registrati().getPwConferma().reset();
						frame.seePanelApertura();
					}
					else {
						JOptionPane.showMessageDialog(frame, "esiste gia' un account collegato a questa email");
					}
				} catch (CannotConnectToDbException e1) {
					frame.getPanel_registrati().getEmail().reset();
					frame.getPanel_registrati().getPw().reset();
					frame.getPanel_registrati().getPwConferma().reset();
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
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
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
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
					if(frame.getPanel_crea().getNome().getText().isEmpty()) {
						return;
					}
					if(tm.createWorkspace(frame.getPanel_crea().getNome().getText())) {
						tm.createScheda(new Scheda("TO DO",null));
						tm.createScheda(new Scheda("DOING",null));
						tm.createScheda(new Scheda("DONE",null));
						frame.seePanelWorkspace(tm.getWorkspace(new Workspace(0,frame.getPanel_crea().getNome().getText(),null,null)),tm.getMembroLogged().getRuolo());
						initTempListenersPanelWorkspace();
						frame.getPanel_crea().getNome().reset();
					}
					else {
						JOptionPane.showMessageDialog(frame, "nome non utilizzabile");
						frame.getPanel_crea().getNome().reset();
						frame.seePanelHomePage();
					}
				} catch (CannotConnectToDbException e3) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
					frame.getPanel_crea().getNome().reset();
					frame.seePanelHomePage();
				}catch(RoleNotAcceptedException e1) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
					frame.getPanel_crea().getNome().reset();
					frame.seePanelHomePage();
				}
			}
		});
		
		//bottone indietro nel panel crea:
		frame.getPanel_crea().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_crea().getNome().reset();
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
		for(JTuskButton b : frame.getPanel_seleziona().getAllBottoni()){
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						frame.seePanelWorkspace(tm.getWorkspace(new Workspace(0,b.getText(),null,null)),tm.getMembroLogged().getRuolo());
						initTempListenersPanelWorkspace();
					} catch (CannotConnectToDbException e1) {
						JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
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
							String sruoli = "";
							for (Ruolo r : c.getRuoli()) {
								if(sruoli == "")
									sruoli = r.getNome();
								else {
									sruoli =sruoli + ", "+r.getNome();
								}
							}
							Calendar cal = Calendar.getInstance();
							cal.setTimeInMillis(c.getScadenza().getTime());
//							int mese;
//							if(cal.get(Calendar.MONTH) == 0)
//								mese = 12;
//							else{
//								mese = cal.get(Calendar.MONTH);//dicembre viene tradotto in 0, cosi risolve
//							}
							JOptionPane.showMessageDialog(frame, "titolo: "+c.getTitolo() + "\ndescrizione: "+ c.getDescrizione() + "\nscadenza: "+(cal.get(Calendar.DAY_OF_MONTH))+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR)+"\nruoli:"+sruoli);
						}
						
					} catch (CannotConnectToDbException e1) {
						JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
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
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
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
						JOptionPane.showMessageDialog(frame, "il membro e' stato aggiunto correttamente");
						frame.getPanel_aggiungiMembro().getEmail().reset();
						frame.getPanel_aggiungiMembro().getRuolo().reset();
						frame.seePanelModifica();
					}else {
						JOptionPane.showMessageDialog(frame, "il membro non e' stato aggiunto, assicurarsi che esista un account associato all'email inserita e che l'email sia valida");
						frame.seePanelAggiungiMembro();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}catch(Exception ex2) {
					JOptionPane.showMessageDialog(frame, "impossibile inviare email di notifica");
				}
				frame.getPanel_aggiungiMembro().getEmail().reset();
				frame.getPanel_aggiungiMembro().getRuolo().reset();
			}
		});
		//bottone indetro nel panel aggiungi membro:
		frame.getPanel_aggiungiMembro().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_aggiungiMembro().getEmail().reset();
				frame.getPanel_aggiungiMembro().getRuolo().reset();
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
					if(tm.modifyMembro(frame.getPanel_modificaRuolo().getEmail().getText(), new Ruolo(frame.getPanel_modificaRuolo().getRuolo().getText()))) {
						JOptionPane.showMessageDialog(frame, "le informazioni del membro sono state modificate con successo");
						frame.seePanelMembro();
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelModificaRuolo();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(CannotSendMailException ex2) {
					JOptionPane.showMessageDialog(frame, "impossibile inviare email di notifica");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_modificaRuolo().getEmail().reset();
				frame.getPanel_modificaRuolo().getRuolo().reset();
			}
		});
		//bottone indetro nel panel modifica ruolo:
		frame.getPanel_modificaRuolo().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaRuolo().getEmail().reset();
				frame.getPanel_modificaRuolo().getRuolo().reset();
				frame.seePanelMembro();
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
						JOptionPane.showMessageDialog(frame, "il membro e' stato rimosso con successo");
						frame.seePanelMembro();
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelRimuoviMembro();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(CannotSendMailException ex2) {
					JOptionPane.showMessageDialog(frame, "impossibile inviare email di notifica");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_rimuoviMembro().getEmail().reset();
			}
		});
		//bottone indietro nel panel rimuovi ruolo:
		frame.getPanel_rimuoviMembro().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_rimuoviMembro().getEmail().reset();
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
				frame.getPanel_modificaNome().getNome().reset();
				frame.seePanelOperazioniWs();
			}
		});
		//bottone invia nel panel modifica nome:
		frame.getPanel_modificaNome().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(frame.getPanel_modificaNome().getNome().getText().equals("")) {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelModificaNome();
						return;
					}
					if(tm.modifyWorkspace(tm.getWorkspace(), new Workspace(0,frame.getPanel_modificaNome().getNome().getText(),null,null))) {
						frame.seePanelHomePage();
					}
					else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelModificaNome();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_modificaNome().getNome().reset();
			}
		});
	}

	private void initListenersPanelRimuoviWs() {
		//bottone indietro nel panel rimuovi ws:
		frame.getPanel_rimuoviWs().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_rimuoviWs().getNome().reset();
				frame.seePanelOperazioniWs();
			}
		});
		//bottone invia nel panel rimuovi ws:
		frame.getPanel_rimuoviWs().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(frame.getPanel_rimuoviWs().getNome().getText().equals(tm.getWorkspace().getNome())) {
						tm.removeWorkspace(tm.getWorkspace());
						frame.seePanelHomePage();
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelRimuoviWs();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(CannotSendMailException ex2) {
					JOptionPane.showMessageDialog(frame, "impossibile inviare email di notifica");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_rimuoviWs().getNome().reset();
			}
		});
	}
	
	private void initListenersPanelAggiungiCompito() {
		//bottone indietro nel panel aggiungi compito:
		frame.getPanel_aggiungiCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_aggiungiCompito().getTitolo().reset();
				frame.getPanel_aggiungiCompito().getDescrizione().reset();
				frame.getPanel_aggiungiCompito().getGiorno().reset();
				frame.getPanel_aggiungiCompito().getMese().reset();
				frame.getPanel_aggiungiCompito().getAnno().reset();
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
				if((frame.getPanel_aggiungiCompito().getAnno().getText().length() != 4) || Integer.parseInt(frame.getPanel_aggiungiCompito().getMese().getText()) <1 || Integer.parseInt(frame.getPanel_aggiungiCompito().getMese().getText())>12 || Integer.parseInt(frame.getPanel_aggiungiCompito().getGiorno().getText()) < 1 || Integer.parseInt(frame.getPanel_aggiungiCompito().getGiorno().getText())>31|| titolo == null || scadenza == null) {
					frame.getPanel_aggiungiCompito().getTitolo().reset();
					frame.getPanel_aggiungiCompito().getDescrizione().reset();
					frame.getPanel_aggiungiCompito().getGiorno().reset();
					frame.getPanel_aggiungiCompito().getMese().reset();
					frame.getPanel_aggiungiCompito().getAnno().reset();
					JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
					frame.seePanelModifica();
					return;
				}
				frame.seePanelAggiungiCompito2(titolo,descrizione,giorno,mese,anno);
				initListenersPanelAggiungiCompito2();
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
					frame.seePanelModifica();
				}
				frame.getPanel_aggiungiCompito().getTitolo().reset();
				frame.getPanel_aggiungiCompito().getDescrizione().reset();
				frame.getPanel_aggiungiCompito().getGiorno().reset();
				frame.getPanel_aggiungiCompito().getMese().reset();
				frame.getPanel_aggiungiCompito().getAnno().reset();
			}
		});
		
	}

	private void initListenersPanelAggiungiCompito2() {
		//bottone indietro nel panel aggiungi compito 2 :
		frame.getPanel_aggiungiCompito2().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_aggiungiCompito2().getRuolo().reset();
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone aggiungi ruolo nel panel aggiungi compito2:
		frame.getPanel_aggiungiCompito2().getBottoneAggiungi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_aggiungiCompito2().addRuolo(frame.getPanel_aggiungiCompito2().getRuolo().getText());
				frame.getPanel_aggiungiCompito2().getRuolo().reset();
			}
		});
		//bottone invia nel panel aggiungi compito2:
		frame.getPanel_aggiungiCompito2().getBottoneFine().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.set(frame.getPanel_aggiungiCompito2().getAnno(), frame.getPanel_aggiungiCompito2().getMese(), frame.getPanel_aggiungiCompito2().getGiorno(),0,0,0);
				Date scadenza = new Date(cal.getTimeInMillis());
				
				ArrayList<Ruolo> ruoli = new ArrayList<Ruolo>();
				for(String s : frame.getPanel_aggiungiCompito2().getLista()) {
					ruoli.add(new Ruolo(s));
				}
				
				try {//lo aggiunge nella prima!
					if(tm.createCompito(tm.getWorkspace().getLista_schede().get(0), new Compito(frame.getPanel_aggiungiCompito2().getTitolo(),frame.getPanel_aggiungiCompito2().getDescrizione(),scadenza,ruoli))) {
						frame.seePanelOperazioniCompito();
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelOperazioniCompito();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_aggiungiCompito2().getRuolo().reset();
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
				frame.getPanel_rimuoviCompito().getTitolo().reset();
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone invia compito nel panel rimuovi compito :
		frame.getPanel_rimuoviCompito().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {//n.b: un solo compito con quel titolo in tutto il ws
					if(tm.removeCompito(new Compito(frame.getPanel_rimuoviCompito().getTitolo().getText(),null,null,null))) {
						frame.seePanelOperazioniCompito();
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelOperazioniCompito();
					}
				} catch (CannotConnectToDbException e1) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(RoleNotAcceptedException ex3) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_rimuoviCompito().getTitolo().reset();
			}
		});
	}

	private void initListenersPanelSpostaCompito() {
		//bottone indietro nel panel sposta compito :
		frame.getPanel_spostaCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_spostaCompito().getScheda().reset();
				frame.getPanel_spostaCompito().getCompito().reset();
				frame.seePanelOperazioniCompito();
			}
		});
		//bottone invia  nel panel sposta compito :
		frame.getPanel_spostaCompito().getBottoneInvia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Compito in_questione = null;
					Scheda scheda_in_questione = null;
					for(Scheda s : tm.getWorkspace().getLista_schede()) {
						if(s.getTitolo().equals(frame.getPanel_spostaCompito().getScheda().getText())) {
							scheda_in_questione = s;
						}
						for(Compito c : s.getCompiti()) {
							if(c.getTitolo().equals(frame.getPanel_spostaCompito().getCompito().getText())) {
								in_questione = c;
							}
						}
					}
					if(in_questione == null || scheda_in_questione == null) {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.getPanel_spostaCompito().getScheda().reset();
						frame.getPanel_spostaCompito().getCompito().reset();
						frame.seePanelOperazioniCompito();
						return;
					}
					
					if(tm.modifyCompito(scheda_in_questione, in_questione, in_questione)) {
						frame.seePanelOperazioniCompito();
						frame.getPanel_spostaCompito().getScheda().reset();
						frame.getPanel_spostaCompito().getCompito().reset();
						return;
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelOperazioniCompito();
					}
				} catch (CannotConnectToDbException | HeadlessException e2) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
				}catch(RoleNotAcceptedException e1) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
				}
				frame.getPanel_spostaCompito().getScheda().reset();
				frame.getPanel_spostaCompito().getCompito().reset();
			}
		});
	}

	private void initListenersPanelModificaCompito() {
		//bottone indietro nel panel modifica compito :
		frame.getPanel_modificaCompito().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaCompito().getTitoloVecchio().reset();
				frame.getPanel_modificaCompito().getTitolo().reset();
				frame.getPanel_modificaCompito().getDescrizione().reset();
				frame.getPanel_modificaCompito().getGiorno().reset();
				frame.getPanel_modificaCompito().getMese().reset();
				frame.getPanel_modificaCompito().getAnno().reset();
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
				if((frame.getPanel_modificaCompito().getAnno().getText().length() != 4) || Integer.parseInt(frame.getPanel_modificaCompito().getMese().getText()) <1 || Integer.parseInt(frame.getPanel_modificaCompito().getMese().getText())>12 || Integer.parseInt(frame.getPanel_modificaCompito().getGiorno().getText()) < 1 || Integer.parseInt(frame.getPanel_modificaCompito().getGiorno().getText())>31 ||titolo_vecchio == null || titolo == null || scadenza == null) {
					frame.getPanel_modificaCompito().getTitolo().reset();
					frame.getPanel_modificaCompito().getDescrizione().reset();
					frame.getPanel_modificaCompito().getGiorno().reset();
					frame.getPanel_modificaCompito().getMese().reset();
					frame.getPanel_modificaCompito().getAnno().reset();
					JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
					frame.seePanelModifica();
					return;
				}
				frame.seePanelModificaCompito2(titolo_vecchio,titolo,descrizione,scadenza);
				initListenersPanelModificaCompito2();
				frame.getPanel_modificaCompito().getTitoloVecchio().reset();
				frame.getPanel_modificaCompito().getTitolo().reset();
				frame.getPanel_modificaCompito().getDescrizione().reset();
				frame.getPanel_modificaCompito().getGiorno().reset();
				frame.getPanel_modificaCompito().getMese().reset();
				frame.getPanel_modificaCompito().getAnno().reset();
			}
		});
	}

	private void initListenersPanelModificaCompito2() {
		//bottone indietro nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneIndietro().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaCompito2().getRuolo().reset();
				frame.seePanelOperazioniCompito();
			}
		});	
		//bottone aggiungi nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneAggiungi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaCompito2().addRuolo(new Ruolo(frame.getPanel_modificaCompito2().getRuolo().getText()));
				frame.getPanel_modificaCompito2().getRuolo().reset();
			}
		});
		//bottone rimuovi nel panel modifica compito 2:
		frame.getPanel_modificaCompito2().getBottoneRimuovi().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getPanel_modificaCompito2().removeRuolo(new Ruolo(frame.getPanel_modificaCompito2().getRuolo().getText()));
				frame.getPanel_modificaCompito2().getRuolo().reset();
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
					ArrayList<Ruolo> ruoli;
					try {
					ruoli = compito_con_nuova_lista_ruoli.getRuoli();
					}catch(Exception exe) {
						ruoli = new ArrayList<Ruolo>();
					}
					
					if(tm.modifyCompito(sc, new Compito(frame.getPanel_modificaCompito2().getTitoloVecchio(), null,null,null),new Compito(tit,desc,scad,ruoli))) {
						frame.seePanelOperazioniCompito();
					}else {
						JOptionPane.showMessageDialog(frame, "impossibile effettuare l'operazione richiesta");
						frame.seePanelOperazioniCompito();
					}
				} catch (RoleNotAcceptedException e1) {
					JOptionPane.showMessageDialog(frame, "il tuo ruolo non e' adeguato a svolgere questa operazione");
					frame.seePanelOperazioniCompito();
				}catch(CannotConnectToDbException ex3) {
					JOptionPane.showMessageDialog(frame, "impossibile reperire informazioni dalla piattaforma");
					frame.seePanelOperazioniCompito();
				}catch(Exception ex1) {
					JOptionPane.showMessageDialog(frame, "errore: riprovare piu' tardi");
				}
				frame.getPanel_modificaCompito2().getRuolo().reset();
			}
		});	
	}
}

