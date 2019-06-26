package core;

import java.awt.Color;
import java.awt.Polygon;

public class Square 
{
	private int[] xCoords = new int[4];
	private int[] yCoords = new int[4];
	
	public int boardX;
	public int boardY;
	
	public Color color;
	
	Polygon poly;
	
	private int width = ChessBoard.SQUARE_WIDTH;
	
	public boolean highlight = false;
	
	public Square(int topRightX, int topRightY)
	{
		xCoords[0] = topRightX;
		xCoords[1] = (int)(topRightX + (0.5 * Math.sqrt(3) * width));
		xCoords[2] = topRightX; 
		xCoords[3] = (int)(topRightX - (0.5 * Math.sqrt(3) * width)); 
		
		yCoords[0] = topRightY;
		yCoords[1] = (int)(topRightY + 0.5 * width);
		yCoords[2] = topRightY + width;
		yCoords[3] = (int)(topRightY + 0.5 * width);	
		
		poly = new Polygon(xCoords, yCoords, 4);
	}
	

	/**
	 * Tests if a (x,y) pair is inside a square
	 * @param x The x coordinate to test if it is inside the square
	 * @param y The y coordinate to test if it is inside the square
	 * @return A boolean representing whether or not this square contains the x and y coordinate specified
	 */
	public boolean contains(int x, int y)
	{
		return poly.contains(x, y);	
	}
	
	/**
	 * Getter
	 * @return Array of the 4 x coordinates of each point of the square
	 */
	public int[] getXCoords()
	{
		return xCoords;
	}
	
	/**
	 * Getter
	 * @return Array of the 4 y coordinates of each point of the square
	 */
	public int[] getYCoords()
	{
		return yCoords;
	}
	
	/**
	 * Getter 
	 * @return Array of the coordinates of the center of the square 
	 */
	public int[] getCenterCoords()
	{
		int cx = (int) ((xCoords[1] + xCoords[3]) / 2);
		int cy = (int) ((yCoords[0] + yCoords[2]) / 2);
		int[] ret = {cx, cy};
		return ret;
	}

}
