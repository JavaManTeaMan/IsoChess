package core;

import java.util.ArrayList;

public class Rook extends Piece
{

	public Rook(int side, int boardX, int boardY) 
	{
		super(side, "res/wr.png", "res/br.png", boardX, boardY);
	}

	/**
	 * Getter
	 * @return The scale factor at which the BufferedImage must be scaled at in order for the piece to fit the square 
	 */
	public double getScale() 
	{
		
		return 0.35;
	}


	/**
	 * Returns the possible moves this piece can 
	 * @param pieces The board to analyze 
	 * @return An array list of int[] of length 2 representing the (x,y), in board coordinates, of each possible move for this piece.
	 */
	public ArrayList<int[]> getPossibleMoves(Piece[][] pieces)  {
		ArrayList<int[]> ret = new ArrayList<int[]>();
		
		//TODO: clean this code up; code duplication going on
		
		// across +y axis (same x)
		for(int i = 1;boardY + i < 8;i++)
		{
			int[] a = {boardX, boardY + i};
			if(pieces[a[0]][a[1]] == null) // empty space, so its a valid move; add it
				ret.add(a);
			else if(pieces[a[0]][a[1]].side != this.side) 
			{
				// enemy piece; can capture it, but cant pass through it, so add it and break out
				ret.add(a);
				break;
			}
			else // piece of our own color; we can't move any more
			{
				break;
			}
				
		}
		
		
		// across -y axis (same x)
		for(int i = 1;boardY - i >= 0;i++)
		{
			int[] a = {boardX, boardY - i};
			if(pieces[a[0]][a[1]] == null) // empty space, so its a valid move; add it
				ret.add(a);
			else if(pieces[a[0]][a[1]].side != this.side) 
			{
				// enemy piece; can capture it, but cant pass through it, so add it and break out
				ret.add(a);
				break;
			}
			else // piece of our own color; we can't move any more
			{
				break;
			}
				
		}
		
		
		// across +x axis
		for(int i = 1; boardX + i < 8;i++)
		{
			int[] a = {boardX + i, boardY};
			if(pieces[a[0]][a[1]] == null) // empty space, so its a valid move; add it
				ret.add(a);
			else if(pieces[a[0]][a[1]].side != this.side) 
			{
				// enemy piece; can capture it, but cant pass through it, so add it and break out
				ret.add(a);
				break;
			}
			else // piece of our own color; we can't move any more
			{
				break;
			}
				
		}
			
		
		// across -x axis
		for(int i = 1;boardX - i >= 0;i++)
		{
			int[] a = {boardX - i, boardY};
			if(pieces[a[0]][a[1]] == null) // empty space, so its a valid move; add it
				ret.add(a);
			else if(pieces[a[0]][a[1]].side != this.side) 
			{
				// enemy piece; can capture it, but cant pass through it, so add it and break out
				ret.add(a);
				break;
			}
			else // piece of our own color; we can't move any more
			{
				break;
			}
				
		}
		
		return ret;
	}
	
	
	
	public Object clone()
	{
		return new Rook(side, boardX, boardY);
	}
}
