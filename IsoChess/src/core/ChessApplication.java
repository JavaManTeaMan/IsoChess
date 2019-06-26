package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* 
 * ********** README ********** 
 * 
 * Just a few ideas for potential additions to this application that would have been added in the final "polished" product:
 * - A software chess clock in a separate JFrame window
 * - Implementing Stalemate
 * - Undo move
 * - Rendering the captured pieces in a place on-screen*
 * - Adding a custom promotion menu*
 * 
 * However, this is mainly intended to be an application used for speed chess. So, the lack of a promotion menu makes sense as a feature because it saves time on players' clock 
 * because players very rarely choose something other than a queen to promote to.
 * 
 * Additionally, the captured pieces on-screen may distract the players but could also be rather useless because players in speed matches do not need to see the captured material; they just focus on their moves 
 * as they try not to waste time.
 * 
 * 
 */

/**
 * @author Danyil Blyschak
 * @version 0.1
 * Class 11-1
 * AP Computer Science Final Project
 * Period 7
 *
 */

public class ChessApplication extends JPanel
{
	public static final int DISPLAY_WIDTH = 800;
	public static final int DISPLAY_HEIGHT = 800;
	
	
	public static JFrame frame;
	
	private ChessBoard board;
	
	private Clock clock;
	
	
	/**
	 * Constructor for the Chess Application class
	 * 
	 * Creates a new ChessBoard object.
	 * 
	 */
	public ChessApplication()
	{
		board = new ChessBoard(DISPLAY_WIDTH / 2, DISPLAY_HEIGHT / 2  - DISPLAY_HEIGHT / 4);
	}
	
	
	/**
	 * Sets up the JFrame window and Mouse Click Listener
	 * 
	 */
	private void createWindow()
	{
		// Set up window (Do this last)
		frame = new JFrame(); 
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("King's Capture Chess");
	    frame.setResizable(false);
	    frame.setAlwaysOnTop(false);
		frame.add(this);
	    
	    frame.pack();
	    frame.setVisible(true);
	   
	    this.addMouseListener(new MouseAdapter() {
	     
	        public void mousePressed(MouseEvent e) 
	        {
	        	board.handleClick(e.getX(), e.getY());
	        	repaint();
	        }
	    });
	 
	}	
	
	
	// JPanel-Related
	
	/**
	 * For internal use by JPanel
	 */
	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
   
        //this.setBackground(new Color(66, 206, 244));
		this.setBackground(new Color(7, 89, 34));
  
        board.render(g);
    }

	
    /**
     * Returns the size of the window to be created
     * 
     * @return Dimension object representing the size of the window
     */
    public Dimension getPreferredSize() 
    {
        return new Dimension(DISPLAY_WIDTH, DISPLAY_HEIGHT);
    }
	
	
	/**
	 * Entry point in the program; creates a new ChessApplication object and initiates a window to be created.
	 * @param args Command Line Arguments
	 */
	public static void main(String[] args)
	{
		new ChessApplication().createWindow();
	}
}
