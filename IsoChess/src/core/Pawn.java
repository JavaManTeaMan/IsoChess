package core;

import java.util.ArrayList;

public class Pawn extends Piece 
{
	

	public Pawn(int side, int boardX, int boardY) 
	{
		super(side, "res/wp.png", "res/bp.png", boardX, boardY);
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
	public ArrayList<int[]> getPossibleMoves(Piece[][] pieces) {
		
		ArrayList<int[]> ret = new ArrayList<int[]>();
		
		int dir;
		
		if(side == 0) dir = -1;
		else dir = 1;
			
	
		if((side == 0 && boardX == 6) || (side == 1 && boardX == 1)) 
		{
			// on the first move, pawns can also move 2 spaces forward
			int[] special = {boardX + dir * 2, boardY };
			
			// only if that piece is empty and there is no piece 1 space in front of the pawn
			if(pieces[special[0]][special[1]] == null && pieces[boardX+dir][boardY] == null)
				ret.add(special);
		}
			
		// 1 space forward
		int[] normal = {boardX + dir, boardY};
		
		if(pieces[normal[0]][normal[1]] == null)
			ret.add(normal);
		
		// DIAGONAL CAPTURES
		if(boardY < 7 &&
				pieces[boardX+dir][boardY+1] != null && 
				pieces[boardX+dir][boardY+1].side != this.side)
		{
			int[] capture1 = {boardX+dir, boardY+1};
			ret.add(capture1);
		}
		
		if(boardY > 0 &&
				pieces[boardX+dir][boardY-1] != null &&
				pieces[boardX+dir][boardY-1].side != this.side)
		{
			int[] capture2 = {boardX+dir, boardY-1};
			ret.add(capture2);
		}
		
		
		return ret;
	
		
	}

	
	public Object clone()
	{
		return new Pawn(side, boardX, boardY);
	}
}
