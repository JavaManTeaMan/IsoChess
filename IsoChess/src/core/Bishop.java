package core;

import java.util.ArrayList;

public class Bishop extends Piece
{

	public Bishop(int side, int boardX, int boardY) 
	{
		super(side, "res/wb.png", "res/bb.png", boardX, boardY);
		
	}

	/**
	 * Getter
	 * @return The scale factor at which the BufferedImage must be scaled at in order for the piece to fit the square 
	 */
	public double getScale() 
	{
	
		return 0.3;
	}


	/**
	 * Returns the possible moves this piece can 
	 * @param pieces The board to analyze 
	 * @return An array list of int[] of length 2 representing the (x,y), in board coordinates, of each possible move for this piece.
	 */
	public ArrayList<int[]> getPossibleMoves(Piece[][] pieces) {
		
		ArrayList<int[]> ret = new ArrayList<int[]>();
		
		// ++
		
		for(int i = 1; (boardX + i < 8 && boardY + i < 8); i++)
		{
			int[] a = {boardX + i, boardY + i};
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
		
		
		// +-
		for(int i = 1; (boardX + i < 8 && boardY - i >= 0); i++)
		{
			int[] a = {boardX + i, boardY - i};
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
		
		
		// -+
		for(int i = 1; (boardX - i >= 0  && boardY + i < 8); i++)
		{
			int[] a = {boardX - i, boardY + i};
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
		
		
		
		// --
		for(int i = 1; (boardX - i >= 0 && boardY - i >= 0); i++)
		{
			int[] a = {boardX - i, boardY - i};
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
		
		//System.out.println(ret.size());
		return ret;
	}
	public Object clone()
	{
		return new Bishop(side, boardX, boardY);
	}
}
