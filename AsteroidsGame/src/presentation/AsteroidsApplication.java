package presentation;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JButton;

import logic.Asteroid;
import logic.AsteroidThread;
import logic.Cosmos;
import logic.Player;
import logic.PlayerThread;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AsteroidsApplication{
	private Cosmos myCosmos = null;
	private Random myRandom = new Random();
	private PaintThread myPaintThread = null;
	private Player myPlayer=null;
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmExit;
	private JToolBar toolBar;
	private JPanel panel;
	private JButton btnStartCosmos;
	private JButton btnAddAsteroids;
	private JButton btnStartPaintthread;
	private JButton btnPlay;
	private boolean i=true;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AsteroidsApplication window = new AsteroidsApplication();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AsteroidsApplication() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
	
		frame.setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		toolBar = new JToolBar();
		
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		btnStartCosmos = new JButton("Start Cosmos");
		btnStartCosmos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client myClient=new Client("localhost", 5000);
				myCosmos = new Cosmos();
				myClient.send(myCosmos);
				myCosmos=(Cosmos) myClient.getMyInputObject();
			}
		});
		toolBar.add(btnStartCosmos);
		
		btnAddAsteroids = new JButton("Add Asteroids");
		btnAddAsteroids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client myClient=new Client("localhost",5000);
				Asteroid myAsteroid = new Asteroid(myRandom.nextInt(450),myRandom.nextInt(450),myRandom.nextInt(50));
				
				myClient.send(myAsteroid);
				myCosmos=(Cosmos) myClient.getMyInputObject();//cosmos con asteroides
			}
		});
		toolBar.add(btnAddAsteroids);
		
		btnPlay = new JButton("Add player");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myPlayer=new Player(myRandom.nextInt(450),myRandom.nextInt(450));
				myCosmos.setMyPlayer(myPlayer);
			}
		});
		toolBar.add(btnPlay);
		
		btnStartPaintthread = new JButton("Start PaintThread");
		btnStartPaintthread.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					myPlayer.moveLeft();
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					myPlayer.moveRigth();
				}
				if(e.getKeyCode()==KeyEvent.VK_UP){
					myPlayer.moveUp();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN){
					myPlayer.moveDown();
				}
				if(e.getKeyCode()==KeyEvent.VK_CONTROL){
					if(i){
						PlayerThread myPlayerThread=new PlayerThread(myPlayer);
						myPlayerThread.start();
						i=false;
					}
					myPlayer.setClo(true);
				}
			}
		});
		btnStartPaintthread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < myCosmos.getAsteroids().size(); i++) {
					AsteroidThread myAsteroidThread = new AsteroidThread(myCosmos.getAsteroids().get(i));
					myAsteroidThread.start();
				}
				myPaintThread = new PaintThread(panel, myCosmos);
				myPaintThread.start();
				toolBar.setEnabled(false);
				menuBar.setEnabled(false);
			}
		});
		toolBar.add(btnStartPaintthread);
		
		panel = new JPanel();
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
}
