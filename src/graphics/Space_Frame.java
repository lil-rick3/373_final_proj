package graphics;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import Audio.SimpleAudioPlayer;

public class Space_Frame extends JFrame  {
	
	public Space_Gui game_gui;
	SimpleAudioPlayer musicPlayer;
	public Space_Frame() {
		super("Peacemakers");
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,700);
		this.setLocation(100, 100);
		this.setBackground(Color.RED);
		try
	     {
	    	 System.out.println("test");
	         musicPlayer = 
	                         new SimpleAudioPlayer();
	           
	         musicPlayer.play();
	         
	         
	     } 
	       
	     catch (Exception ex) 
	     {
	         System.out.println("Error with playing sound.");
	         ex.printStackTrace();
	       
	       }
		
		
		game_gui = new Space_Gui();
		
		this.add(game_gui);
		this.setVisible(true);
		
	}
	
}
