package core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Piece 
{
	public int side; // 0 for white, 1 for black
	
	public int boardX, boardY;
	
	
	protected String imageFilePathSide0, imageFilePathSide1;
	
	BufferedImage image;
	
	/**
	 * @param side 0: white / 1: black 
	 * @param imageFilePathSide0 The image file path of the white piece
	 * @param imageFilePathSide1 The image file path of the black piece
	 * @param boardX The x coordinate, relative to the board, of the piece
	 * @param boardY The y coordinate, relative to the board, of the piece
	 */
	public Piece(int side, String imageFilePathSide0, String imageFilePathSide1, int boardX, int boardY)
	{
		this.side = side;
		
		this.boardX = boardX;
		this.boardY = boardY;
		
		this.imageFilePathSide0 = imageFilePathSide0;
		this.imageFilePathSide1 = imageFilePathSide1;
		
		String imageFilePath;
		
		if(side == 0) imageFilePath = imageFilePathSide0;
		else imageFilePath = imageFilePathSide1;
		
		try 
		{
			this.image = ImageIO.read(new File(imageFilePath));
		}
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Tests if a given square is valid for this piece
	 * @param s The square to test at
	 * @return A boolean representing if the square is valid for this piece
	 */
	public boolean isValidSquare(Square s)
	{
		for(int[] coords: getPossibleMoves(ChessBoard.pieces))
		{
			if(coords[0] == s.boardX && coords[1] == s.boardY)
			{
				return true;				
			}
		}
		return false;
	}
	
	/**
	 * Getter
	 * @return The BufferedImage of this piece
	 */
	public BufferedImage getImage()
	{
		return image;
	}
	

	/**
	 * @return The cloned Object (must cast to Piece to do anything meaningful)
	 */
	public Object clone()
	{
		return new Piece(side, imageFilePathSide0, imageFilePathSide1, boardX, boardY);
	}
	
	
	// get possible moves?
	
	/**
	 * Returns the possible moves this piece can 
	 * @param pieces The board to analyze 
	 * @return An array list of int[] of length 2 representing the (x,y), in board coordinates, of each possible move for this piece.
	 */
	public ArrayList<int[]> getPossibleMoves(Piece[][] pieces)  {return null;}
	
	
	/**
	 * Getter
	 * @return The scale factor at which the BufferedImage must be scaled at in order for the piece to fit the square 
	 */
	public double getScale() {return 0;}



}
