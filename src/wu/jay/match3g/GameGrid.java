package wu.jay.match3g;

import wu.jay.match3g.Orb.OrbType;
import java.util.Random;
import java.util.Arrays;

public class GameGrid {
	
	
	public Orb [][] mGrid;
	private int width;
	private int height;
	
	public GameGrid(int w, int h) {
		this.width = w;
		this.height = h;
		initGameGrid(w, h);
	}
	
	public void initGameGrid(int w, int h) {
		mGrid = new Orb[h][w];
		
		// first set everything to NONE
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) { 
				mGrid[i][j] = new Orb(OrbType.NONE);
			}
		}
		
		// Randomly fill with orbs
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				OrbType randOrb;
				do {
				// Fill random colour.
				randOrb = OrbType.getRandomOrb();
				mGrid[i][j].type = randOrb;
				}
				// Check if it's valid
				while (checkMatch(j, i, randOrb));
			}
		}
	}
	
	public boolean checkMatch(int x, int y, OrbType orb) {
		// check horizontally.
		if (x >= 2 && mGrid[y][x-2].type == orb && mGrid[y][x-1].type == orb) return true;
		else if (x >= 1 && x <= this.width-2 && mGrid[y][x-1].type == orb && mGrid[y][x+1].type == orb) return true;
		else if (x <= this.width-3 && mGrid[y][x+1].type == orb && mGrid[y][x+2].type == orb) return true;
		
		// check vertically.
		if (y >= 2 && mGrid[y-2][x].type == orb && mGrid[y-1][x].type == orb) return true;
		else if (y >= 1 && y <= this.height-2 && mGrid[y-1][x].type == orb && mGrid[y+1][x].type == orb) return true;
		else if (y <= this.width-3 && mGrid[y+1][x].type == orb && mGrid[y+2][x].type == orb) return true;
		
		return false;
	}
	
	/* return orb in row and col of grid */
	public Orb getOrb(int row, int col) {
		return mGrid[row][col];
	}
	
	public String printGrid() {
		String grid = Arrays.deepToString(mGrid).replaceAll("],", "],\r\n");
		return grid;
	}
	
	public int getWidth() { return this.width; }
	
	public int getHeight() { return this.height; }
}
