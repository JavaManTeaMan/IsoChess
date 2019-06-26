package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ChessBoard 
{
	
	private int x, y; // coordinates of "top right" corner
	
	private int[] xBaseCoords = new int[4];
	private int[] yBaseCoords = new int[4];
	
	private int[] xLeftCoords = new int[4];
	private int[] yLeftCoords = new int[4];
	
	private int[] xRightCoords = new int[4];
	private int[] yRightCoords = new int[4];
	
	public static final int SQUARE_WIDTH = 50;
	public static final int BOARD_HEIGHT = 40;
	
	// Colors 
	private final Color shadowedWood = new Color(152, 106, 80);
	private final Color bottomWood = new Color(203, 145, 108);
	
	private final Color darkWood = new Color(99, 48, 17);
	private final Color lightWood = new Color(235, 191, 164);
	
	private final Color highlightColor = new Color(240, 230, 140);
	
	private boolean kingCapture = false;
	
	// Containers
	public static Square[][] squares = new Square[8][8];
	public static Piece[][] pieces = new Piece[8][8]; 

	private Piece selectedPiece = null;
	
	public static int moves = 0; // Every time a player makes a move, increment; even: white to move | odd: black to move
	
	
	/**
	 * Constructor for the ChessBoard class 
	 * @param topRightX the x coordinate of the "top right" corner of the board (farthest away from you)
	 * @param topRightY the y coordinate of the "top right" corner of the board (farthest away from you)
	 */
	public ChessBoard(int topRightX, int topRightY)
	{
		this.x = topRightX;
		this.y = topRightY;
		
		this.construct();
	}
	
	/**
	 * Initializes the squares on the board and places the pieces
	 */
	private void construct()
	{
		// Initialize outline of the board
		xBaseCoords[0] = x;
		xBaseCoords[1] = (int)(x + (4 * Math.sqrt(3) * SQUARE_WIDTH));
		xBaseCoords[2] = x; 
		xBaseCoords[3] = (int)(x - (4 * Math.sqrt(3) * SQUARE_WIDTH)); 
		
		yBaseCoords[0] = y;
		yBaseCoords[1] = y + 4 * SQUARE_WIDTH;
		yBaseCoords[2] = y + 8 * SQUARE_WIDTH;
		yBaseCoords[3] = y + 4 * SQUARE_WIDTH;
		
		// Initialize thick section rectangles of the board
		// Left portion
		xLeftCoords[0] = xBaseCoords[2];
		xLeftCoords[1] = xBaseCoords[3];
		xLeftCoords[2] = xBaseCoords[3];
		xLeftCoords[3] = xBaseCoords[2];
		
		yLeftCoords[0] = yBaseCoords[2];
		yLeftCoords[1] = yBaseCoords[3];
		yLeftCoords[2] = yBaseCoords[3] + BOARD_HEIGHT;
		yLeftCoords[3] = yBaseCoords[2] + BOARD_HEIGHT;
		
		// Right portion
		xRightCoords[0] = xBaseCoords[2];
		xRightCoords[1] = xBaseCoords[1];
		xRightCoords[2] = xBaseCoords[1];
		xRightCoords[3] = xBaseCoords[2];
		
		yRightCoords[0] = yBaseCoords[2];
		yRightCoords[1] = yBaseCoords[1];
		yRightCoords[2] = yBaseCoords[1] + BOARD_HEIGHT;
		yRightCoords[3] = yBaseCoords[2] + BOARD_HEIGHT;
		
		
		// Construct Squares
		for(int i = 0; i < 8; i++)
		{
			int startX = (int)(x + i * (Math.sqrt(3) * SQUARE_WIDTH / 2));
			int startY = (int)(y + i * (SQUARE_WIDTH / 2));
			
			for(int j = 0; j < 8; j++)
			{
				int xPrime = (int)(startX - j * (Math.sqrt(3) * SQUARE_WIDTH / 2));
				int yPrime = (int)(startY + j * (SQUARE_WIDTH / 2));
				squares[j][i] = new Square(xPrime, yPrime);
				squares[j][i].boardX = i;
				squares[j][i].boardY = j;
			}
		}
		
		
		// Place initial pieces
		
		/////////////// White pieces \\\\\\\\\\\\\\\\\\\\\\
		// pawns
		for(int i = 0; i < 8; i++)
		{
			pieces[6][i] = new Pawn(0, 6, i);
		}
		
		
		// rooks
		pieces[7][0] = new Rook(0,7,0);
		pieces[7][7] = new Rook(0, 7, 7);
		
		// knights
		pieces[7][1] = new Knight(0,7,1);
		pieces[7][6] = new Knight(0,7,6);
		
		//bishops
		pieces[7][2] = new Bishop(0, 7, 2);
		pieces[7][5] = new Bishop(0, 7, 5);
		
		//queen
		pieces[7][4] = new Queen(0, 7, 4);
		
		//king
		pieces[7][3] = new King(0, 7, 3);
		
		
		/////////////// Black pieces \\\\\\\\\\\\\\\\\\\\\\
		for(int i = 0; i < 8; i++)
		{
			pieces[1][i] = new Pawn(1, 1, i);
		}
		
		//pieces[6][6] = null;
		
		// rooks
		pieces[0][0] = new Rook(1, 0, 0);
		pieces[0][7] = new Rook(1, 0, 7);
		
		// knights
		pieces[0][1] = new Knight(1, 0, 1);
		pieces[0][6] = new Knight(1, 0, 6);
		
		//bishops
		pieces[0][2] = new Bishop(1, 0, 2);
		pieces[0][5] = new Bishop(1, 0, 5);
		
		//queen
		pieces[0][4] = new Queen(1, 0, 4);
		
		//king
		pieces[0][3] = new King(1, 0, 3);
		
	}
	
	/**
	 * @param g Instance of a Graphics object for internal use by Javax Swing: used to draw shapes, texts, etc.
	 */
	public void render(Graphics g)
	{	
		// Draw Outline
		
		// g2d useful for shapes
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(lightWood); 
		g.fillPolygon(xBaseCoords, yBaseCoords, 4);
		
		// Draw rectangles below
		g.setColor(shadowedWood); 
		g.fillPolygon(xLeftCoords, yLeftCoords, 4);
		
		g.setColor(bottomWood);
		g.fillPolygon(xRightCoords, yRightCoords, 4);
		
		// Draw squares
		for(int i = 0; i < squares.length; i++)
		{
			for(int j = 0; j < squares[0].length; j++)
			{
				Square currentSquare = squares[i][j];
				
				if(currentSquare.highlight) g.setColor(highlightColor);
				else if((i + j) % 2 == 0) g.setColor(darkWood);
				else g.setColor(lightWood);
				
				g.fillPolygon(currentSquare.getXCoords(), currentSquare.getYCoords(), 4);	
			}
		}
		
		// Draw a box around squares that are potential moves for the current piece
		if(selectedPiece != null)
		{
			for(int[] move: selectedPiece.getPossibleMoves(pieces))  
			{
				if(selectedPiece.isValidSquare(squares[move[1]][move[0]])) // [row][column] -> [y][x]
				{
					// If move is attacking another piece, paint it red
					// If move is a push, paint it green
					if(pieces[move[0]][move[1]] != null)
						g2d.setColor(Color.RED);
					else
						g2d.setColor(new Color(50,205,50)); // lime green
					
					g2d.setStroke(new BasicStroke(4)); // set line thickness
					
					g2d.drawPolygon(squares[move[1]][move[0]].getXCoords(),
							squares[move[1]][move[0]].getYCoords(), 4);
				}
			}
		}
		
		
		// Draw pieces 
		for(Piece[] pi: pieces)
		{
			for(Piece p: pi)
			{
			
				if(p != null)
				{
				
					double scale = p.getScale();
			
					int imageWidth = (int)(p.getImage().getWidth() * scale);
					int imageHeight = (int)(p.getImage().getHeight() * scale);
			
					int targetX = squares[p.boardY][p.boardX].getCenterCoords()[0];
					int targetY = squares[p.boardY][p.boardX].getCenterCoords()[1];
			
			
					g.drawImage(p.getImage(), targetX - imageWidth / 2, targetY - imageHeight + imageHeight/6, imageWidth, imageHeight, null);
			
				}
			}
		}
	}
	
	/**
	 * Officially moves a piece on the board. If this method is ran, then it is guaranteed that the target is a valid square
	 * @param p The piece to be moved
	 * @param target The target square that the piece is going to
	 */
	public void movePiece(Piece p, Square target)
	{
		if(!(p.boardX == target.boardX && p.boardY == target.boardY))
		{
			
			deselectPiece();
			
			
			pieces[p.boardX][p.boardY] = null;
			// deadPieces.add(pieces[target.boardX][target.boardY]); -> how a captured "inventory" might have been implemented 
			
			if(pieces[target.boardX][target.boardY] != null && pieces[target.boardX][target.boardY].getClass().getName().equals("core.King")) 
				// Good idea for a TODO: implement a stalemate
			{
				kingCapture = true;
			}
			
			pieces[target.boardX][target.boardY] = p;
		
			// actually move the piece to the new target 
			p.boardX = target.boardX;
			p.boardY = target.boardY;
		
			
			// Check if king has been captured
			if(kingCapture)
			{
				// current player won	
				
				// **************************************
				/* IMPORTANT NOTE:
				 * 
				 * Try uncommenting the two lines below and seeing if they work on your machine
				 * I've looked it up, and apparently its a java bug that sometimes causes the program to crash when calling methods to JOptionPane, as it did on my machine.
				 * For total compatibility I have left these lines out, and just made the board freeze when the game ends
				 * 
				 *  */
				// **************************************
				
				//String winningMessage = ((moves % 2 == 0) ? "White" : "Black") + " has won because of King capture";
				//JOptionPane.showMessageDialog(null, winningMessage);
			}
				
			
			// Deal with pawn promotion
			if(p.getClass().getName() == "core.Pawn" && (p.boardX == 0 || p.boardX == 7)) 
			{
				// Replace piece
				Piece replacement;
				
				// Automatically switch to a queen
				// Very rarely does anybody pick anything other than a queen to promote to
				replacement = new Queen(p.side, p.boardX, p.boardY);
			
				pieces[p.boardX][p.boardY] = replacement;
			}
		
		}
		moves++;
	}

	/**
	 * Accesses the grid of pieces and returns the piece located at a given Square on the board
	 * @param s The square at which to return the piece on 
	 * @return The piece on square s
	 */
	public Piece pieceOnSquare(Square s)
	{
		return pieces[s.boardX][s.boardY];
	}
	
	
	
	/**
	 * Helper method that unselects the current piece  
	 */
	public void deselectPiece()
	{
		// un-highlight all other squares
		for(Square[] squ: squares)
		{
			for(Square sq: squ)
				sq.highlight = false;
		}
		
		selectedPiece = null;		
	}
	
	
	/**
	 * Helper method to select the current piece
	 * @param p The piece to select
	 * @param s The square that the piece is on, so that it can be highlighted
	 */
	public void selectPiece(Piece p, Square s)
	{
		//deselectPiece();
		for(Square[] squ: squares)
		{
			for(Square sq: squ)
				sq.highlight = false;
		}
		
		
		selectedPiece = p;
		s.highlight = true; //highlight square
		
	}
	
	/**
	 * Helper method to process mouse clicks
	 * @param s The square that the mouse clicked on
	 */
	public void handleSquareClick(Square s)
	{		
		Piece pieceOnClickedSquare = pieceOnSquare(s);
	
		// A piece is not currently selected
		if(selectedPiece == null)
		{
			// A piece is clicked on
			if(pieceOnClickedSquare != null)
			{ 	
				if(pieceOnClickedSquare.side == moves % 2) 
				{
					// Piece is of same color of the current turn's player; select it as the new selected piece
					selectPiece(pieceOnClickedSquare, s);
				}
				
			}
		}
		
		
		// A piece is currently selected
		else
		{	
			// no piece on clicked square (its empty)
			if(pieceOnClickedSquare == null)					
			{
				// Check if clicked on empty square is a valid move for the current selected piece
				if(selectedPiece.isValidSquare(s))
				{
						// King can be captured, so do not check
						movePiece(selectedPiece, s);
					
				}
				else
				{
					// clicked square is not a valid move; deselect the current piece
					deselectPiece();
				}
				
						
			}
			// square is not empty
			else
			{
				
				if(selectedPiece.isValidSquare(s))
				{
					if(pieceOnClickedSquare.side == moves % 2) 
					{
						// piece we clicked on is our piece 
						// select it as the newly selected piece
						selectPiece(pieceOnClickedSquare, s);
					} 
					
					if(pieceOnClickedSquare.side != moves % 2) 
					{
						// piece we clicked on is enemy's piece and it is on a valid square, so capture the enemy's piece
						movePiece(selectedPiece, s);
					}
				}
				else
				{
					// Select the new piece
					if(pieceOnClickedSquare.side == moves % 2) 
					{
						// Piece is of same color of the current turn's player; select it as the new selected piece
						selectPiece(pieceOnClickedSquare, s);
					}
					else
					{
						deselectPiece();
					}
				}
			}	
		}
	}
	
	
	/**
	 * The primary and first handler for mouse input
	 * @param mouseX The x coordinate of the mouse click
	 * @param mouseY The y coordinate of the mouse click
	 */
	public void handleClick(int mouseX, int mouseY)
	{
		if(!kingCapture)
		{
		
		for(int i = 0; i < squares.length; i++)
		{
			for(int j = 0; j < squares[0].length; j++)
			{
				if(squares[i][j].contains(mouseX, mouseY)) 
				{
					// User clicked on a square			
					handleSquareClick(squares[i][j]);
						
					return;
				}
			}
		}
		
		deselectPiece();
		
		}
	}
	
}
