package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import engine.Core;

public class Keys implements KeyListener{
	
	public boolean f1 = false;

	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_UP){
			Core.player.up = true;
		}
		if (keyCode == KeyEvent.VK_DOWN){
			Core.player.down = true;
		}
		if (keyCode == KeyEvent.VK_LEFT){
			Core.player.left = true;
		}
		if (keyCode == KeyEvent.VK_RIGHT){
			Core.player.right = true;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_UP){
			Core.player.up = false;
		}
		if (keyCode == KeyEvent.VK_DOWN){
			Core.player.down = false;
		}
		if (keyCode == KeyEvent.VK_LEFT){
			Core.player.left = false;
		}
		if (keyCode == KeyEvent.VK_RIGHT){
			Core.player.right = false;
		}
		if (keyCode == KeyEvent.VK_F1){
			f1 = !f1;
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

}
