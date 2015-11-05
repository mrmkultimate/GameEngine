package org.GameEngine.System;

import java.awt.event.KeyEvent;

//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;

//import com.jogamp.newt.event.*;
import java.awt.event.KeyListener;
//import com.jogamp.opengl.GLAutoDrawable;
//import javax.media.opengl.GLAutoDrawable;


public final class Input implements KeyListener  {


	int mouseClickX = 0;
	int mouseClickY = 0;
/*
	public Input(GLAutoDrawable gLAutoDrawable) {
		
		gLAutoDrawable.addKeyListener(this);
		gLAutoDrawable.addMouseListener(this);
		gLAutoDrawable.addMouseMotionListener(this);

	}
*/
	/*public Input(GLAutoDrawable glAutoDrawable){
		gLAutoDrawable.addKeyListener(this);
		gLAutoDrawable.addMouseListener(this);
		gLAutoDrawable.addMouseMotionListener(this);
	}*/

	/*
	@Override
	public void keyPressed(KeyEvent e) {
		char keyCode = e.getKeyChar();
		Keyboard.onKeyPress(keyCode);
		System.out.println(keyCode);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		char keyCode = e.getKeyChar();
		Keyboard.onKeyRelease(keyCode);
		System.out.println("keyreleased" +keyCode);
	}
*/
/*
	public void mouseReleased(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent mouseEvent) {
		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		this.mouseClickX = x;
		this.mouseClickY = y;
	}

	public void mouseClicked(MouseEvent arg0) {
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
	}

	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		int dx = Math.abs(x - this.mouseClickX);
		int dy = Math.abs(y - this.mouseClickY);

	}

	public void mouseMoved(MouseEvent e) {
		
	}


	*/
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		char keyCode = arg0.getKeyChar();
		Keyboard.onKeyPress(keyCode);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		char keyCode = arg0.getKeyChar();
		Keyboard.onKeyRelease(keyCode);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
