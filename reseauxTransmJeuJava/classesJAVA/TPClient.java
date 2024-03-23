/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reseauxjeu;

import java.awt.Frame;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter; // Window Event
import java.awt.event.WindowEvent; // Window Event

import java.util.Timer;
import java.util.TimerTask;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.lang.Integer;

//server-client
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class TPClient extends Frame {

        private static int windowSize = 1000;   //taille de la fenêtre (px)
	byte [] etat = new byte [2*10*10];     //position, couleur (equipe, win ou lose)
	int team;
	int x;
	int y;
	int port = 5002;
	Socket socket = null;
	InputStream in;
	DataOutputStream out;
	TPPanel tpPanel;
	TPCanvas tpCanvas;
	Timer timer;

	/** Constructeur */
	public TPClient(int number,int team, int x, int y)
	{
		setLayout(new BorderLayout());
		tpPanel = new TPPanel(this);
		add("North", tpPanel);
		tpCanvas = new TPCanvas(this.etat);
		add("Center", tpCanvas);
		
		timer = new Timer();
		timer.schedule ( new MyTimerTask (  ) , 500,500) ;

	}
	
	/** Action vers la droite,
         *  Quand le client appuie sur "droite" on envoie au serveur qui va vérifier si c'est possible
         */
	public synchronized void droit()
	{
		System.out.println("Droit");
		try{        //envoi + réception serveur    
                    
			tpCanvas.repaint();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/** Action vers gauche */
	public synchronized void gauche()
	{
		System.out.println("Gauche");
		try{
			//insérer envoi + réception serveur 
			tpCanvas.repaint();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/** Action vers gauche */
	public synchronized void haut()
	{
		System.out.println("Haut");
		try{
			//insérer envoi + réception serveur 
			tpCanvas.repaint();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	
	}
	/** Action vers bas */
	public synchronized void bas ()
	{
		System.out.println("Bas");
		try{
			//insérer envoi + réception serveur 
			tpCanvas.repaint();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	/** Pour rafraichir la situation */
	public synchronized void refresh ()
	{
		try {
                    //insérer envoi + réception serveur 
                    
		} catch (Exception e) {
			e.printStackTrace();
		}

		tpCanvas.repaint();
	}
	/** Pour recevoir l'Etat en cas de changement (défaite ou victoire*/
	public void receiveEtat()
	{
		try{
                    //insérer envoi + réception serveur     
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	/** Initialisations de l'equipe du joueur et sa position 
         *   Le byte etat[] contient la position ainsi que la couleur du joueur. L'idée est de les définir ici.
         */
	public void minit(int number, int pteam, int px, int py)
	{
		try{
                    //faire apparaitre joueur avec sa position et son equipe.
                    int position = (py*10 + px)*2;  //position représente la case sur laquelle se situe le joueur
                    etat[position] ++;              //On définit la position sur cette case.
                    
                    if ( pteam < 3 || pteam > 0){   //Si l'équipe choisie est bleu ou rouge,
                        
                        if (pteam == 1) etat[position+1] ++ ; //definir couleur bleue
                        if (pteam == 2) etat[position+1] += 2 ; //definir couleur rouge

                    }
                    
                    else {
                        
                        System.out.println("Erreur: Il faut correctement préciser une équipe (1 = bleu, 2 = rouge).");
                        
                    }
                    
                    
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String etat()
	{
		String result = new String();
		return result;
	}
	
	/** Pour rafraichir */
	class MyTimerTask extends TimerTask{
		
		public void run ()
		{
			System.out.println("refresh");
          		refresh();
		}
	}
        
        public static void main(String[] args) {
            
            final Socket clientSocket;
            final BufferedReader in;
            final PrintWriter out;
            final Scanner sc = null;
            final int port = 5002;
            
            System.out.println("Connexion au serveur...");
            
                try{
                clientSocket = new Socket("localhost",port);    //Le client essaye de se connecter au serveur
                
                //envoyer
                out = new PrintWriter(clientSocket.getOutputStream());
                //recevoir
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
		System.out.println("args :"+args.length);
		if (args.length != 4) {
			System.out.println("Usage : java TPClient(int number, int color, int positionX, int positionY) ");
			System.exit(0);
		}
            
                    try {
                        int number = Integer.parseInt(args[0]);
                        int team = Integer.parseInt(args[1]);
                        int x = Integer.parseInt(args[2]);
                        int y = Integer.parseInt(args[3]);
			TPClient tPClient = new TPClient(number, team, x, y);
			tPClient.minit(number, team, x, y);

			

			// Pour fermeture
			tPClient.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});

			// Create Panel back forward
			
			tPClient.pack();
			tPClient.setSize(windowSize, windowSize+200);
			tPClient.setVisible(true);

                    } catch(Exception e) {
			e.printStackTrace();
                    }
                }
                catch(IOException e){       //Si le client n'arrive pas à se connecter au serveur.
                    e.printStackTrace();
                    System.out.println("Impossible de se connecter au serveur. (Vérifiez si il est ouvert.)");
                }
	}
	
}
