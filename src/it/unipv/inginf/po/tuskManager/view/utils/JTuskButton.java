package it.unipv.inginf.po.tuskManager.view.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class JTuskButton extends JComponent implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private Dimension size;
	private Dimension arc;
	private boolean mouse_entered;
	private boolean mouse_pressed;
	private String testo;
	private Color colore;
	private Color colore_testo;
	private boolean bold;
	private Image image;
	private String path_image;
	private ArrayList<ActionListener> listeners;
	public JTuskButton(String s, Color c, Color ctext, boolean txtbold, Dimension button_size, Dimension button_arc) {
		this(s,c,ctext,txtbold,"",button_size,button_arc);
	}
	
	public JTuskButton(String s, Color c, Color ctext, boolean txtbold, String path_img, Dimension button_size, Dimension button_arc) {
		super();
		size = button_size;
		arc = button_arc;
		setSize(size.width,size.height);
		enableInputMethods(true);   
		addMouseListener(this);
		setFocusable(true);
		
		mouse_entered = false;
		mouse_pressed = false;
		testo = s;
		colore = c;
		colore_testo = ctext;
		bold = txtbold;
		path_image = path_img;
		try {
			if(!path_img.equals(""))
				image = ImageIO.read(new File(path_img));
		} catch (IOException e) {
			image = null;
		}
		listeners = new ArrayList<ActionListener>();
		
	}
	
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// turn on anti-alias mode
        Graphics2D antiAlias = (Graphics2D)g;
        antiAlias.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int padding_x = (int)getWidth()/80;
        int padding_y = (int)getHeight()/15;
        //border
        g.setColor(colore_testo);
        g.fillRoundRect(padding_x, padding_y, getWidth() - padding_x*2 -1, getHeight() - padding_y*2 -1, arc.width, arc.height);
        //interno
        g.setColor(colore);       
//        if(mouse_entered) 
//        	g.setColor(g.getColor().darker());
        if(mouse_pressed)
        	g.setColor(g.getColor().darker());
        g.fillRoundRect(padding_x+2, padding_y+2, getWidth() - padding_x*2 -5, getHeight() - padding_y*2 -5, arc.width, arc.height);
        
        g.setColor(colore_testo);
        
        g.setColor(colore_testo);
        int size_testo;
        size_testo = (int)Math.min(getHeight()/2, getWidth()*2/(3*testo.length()));
        if(bold)
        	g.setFont(new Font("Serif",Font.BOLD,size_testo));
        else
        	g.setFont(new Font("Serif",Font.PLAIN,size_testo));
        int stringX;
        stringX = (!path_image.equals("")) ? (int)getWidth()/7 : ((int)((getWidth()/2) -(size_testo*(testo.length()-2)/2) + padding_x));
        int stringY = ((int)getHeight()/2 + (int)size_testo/2 - padding_y);
        
        g.drawString(testo, stringX, stringY);
        if(image != null) {
    		int image_scale = Math.min((int)getHeight()/2, (int)getWidth()/5);
    		g.drawImage(image, getWidth()*2/3, padding_y + (int)getHeight()/7, image_scale,image_scale, null);
        }
    
        
        
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		mouse_entered = true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		mouse_entered = false;
		mouse_pressed = false;
        repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		mouse_pressed = true;
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		mouse_pressed = false;
		if(mouse_entered) {
			mouse_entered = false;
			notifyListeners(arg0);
		}
		repaint();
	}
	
	public void addActionListener(ActionListener a) {
		listeners.add(a);
	}
	public void notifyListeners(MouseEvent e) {
		ActionEvent evt = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, new String(), e.getWhen(), e.getModifiers());
		synchronized(listeners)
        {
            for (int i = 0; i < listeners.size(); i++)
            {
                ActionListener tmp = listeners.get(i);
                tmp.actionPerformed(evt);
            }
        }
	}
	
	public Dimension getPreferredSize()
    {
        return new Dimension(getWidth(), getHeight());
    }
    public Dimension getMinimumSize()
    {
        return getPreferredSize();
    }
    public Dimension getMaximumSize()
    {
        return getPreferredSize();
    }
    public String getText() {
    	return testo;
    }
}
