package it.unipv.inginf.po.tuskManager.view.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class JTuskField extends JTextField implements MouseListener{
    
	private static final long serialVersionUID = 1L;
    private boolean mouse_clicked;
    private Color txtcolor;
    private String testo;
    public JTuskField(String s, Color colore, Color txtcolor, Font font) {
    	super(s);
    	testo = s;
    	this.txtcolor = txtcolor;
        this.setFont(font);
        this.setBackground(colore);
        this.setForeground(txtcolor);
        LineBorder bordo = new LineBorder(Color.BLACK,2,false);
        this.setBorder(bordo);
        this.setHorizontalAlignment(JTextField.CENTER);
        addMouseListener(this);
        mouse_clicked = false;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	this.setForeground(txtcolor);
    	if(!mouse_clicked) {
    		this.setForeground(Color.GRAY);
    		this.setText(testo);
    	}
    		
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    	if(!mouse_clicked)
    		this.setText("");
    	mouse_clicked = true;
		repaint();
    }
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}