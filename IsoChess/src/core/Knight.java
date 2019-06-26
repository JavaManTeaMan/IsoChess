package core;

import java.util.ArrayList;

public class Knight extends Piece
{

	public Knight(int side, int boardX, int boardY) 
	{
		super(side, "res/wk.png", "res/bk.png", boardX, boardY);

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
	public ArrayList<int[]> getPossibleMoves(Piece[][] pieces)  {
		ArrayList<int[]> ret = new ArrayList<int[]>();
		
		int[][] candidates = new int[8][2]; 
		
		int[] px1 = {boardX + 2, boardY + 1};
		int[] px2 = {boardX + 2, boardY - 1};
		
		int[] nx1 = {boardX - 2, boardY + 1};
		int[] nx2 = {boardX - 2, boardY - 1};
		
		int[] py1 = {boardX + 1, boardY + 2};
		int[] py2 = {boardX - 1, boardY + 2};
		
		int[] ny1 = {boardX + 1, boardY - 2};
		int[] ny2 = {boardX - 1, boardY - 2};
		
		
		candidates[0] = px1;
		candidates[1] = px2;
		candidates[2] = nx1;
		candidates[3] = nx2;
		candidates[4] = py1;
		candidates[5] = py2;
		candidates[6] = ny1;
		candidates[7] = ny2;
		

		for(int[] s: candidates)
		{
			if((s[0] < 8 && s[0] >= 0) &&
			   (s[1] < 8 && s[1] >= 0)  &&
			   (pieces[s[0]][s[1]] == null || pieces[s[0]][s[1]].side != this.side))
			{
				// candidate passes all psuedo-legal requirements; add it
				ret.add(s);
			}
		}
		
		return ret;
	}
	public Object clone()
	{
		return new Knight(side, boardX, boardY);
	}
}
