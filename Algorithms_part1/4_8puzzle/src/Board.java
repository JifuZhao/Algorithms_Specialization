/*----------------------------------------------------------------
 *  Author:        Jifu Zhao
 *  Written:       03/23/2017
 *  
 *  Programming assignment for Princeton Algorithms Part I
 *  8 Puzzle Problem
 *----------------------------------------------------------------*/

import java.util.Stack;

public class Board {
	private int n;  // n row by n column, board dimension
	private int[] board;  // board
	private int hammingDist;  //  hamming distance
	private int manhattanDist;  // manhattan distance
	
	public Board(int[][] blocks) {
		// construct a board from an n-by-n array of blocks
		// (where blocks[i][j] = block in row i, column j)
		n = blocks.length;
		board = new int[n * n];
		hammingDist = 0;
		manhattanDist = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int loc = i * n + j;  // current location
				int current = blocks[i][j];  // get current value
				board[loc] = current;
				
				if (current != 0) {
					if (current != loc + 1) 
						hammingDist++;  // calculate hamming distance
					
					// calculate the correct location
					int col = (current - 1) % n;
					int row = (current - 1) / n;
					
					// calculate manhattan distance
					if (col > j) 
						manhattanDist += col - j;
					else 
						manhattanDist += j - col;
					
					if (row > i) 
						manhattanDist += row - i;
					else 
						manhattanDist += i - row;
				}
			}
		}
	}

    public int dimension() {
    	// board dimension n
    	return n;
    }
    
    public int hamming() {
    	// number of blocks out of place
    	return hammingDist;
    }
    
    public int manhattan() {
    	// sum of Manhattan distances between blocks and goal
    	return manhattanDist;
    }
    
    public boolean isGoal() {
    	// is this board the goal board?
    	return hammingDist == 0;
    }
    
    private void swap(int[][] array, int a, int b, int c, int d) {
    	// function to swap array[a][b] and array[c][d]
    	int tmp = array[a][b];
    	array[a][b] = array[c][d];
    	array[c][d] = tmp;
    }
    
    public Board twin() {
    	// a board that is obtained by exchanging any pair of blocks
    	int[][] twins = new int[n][n];
    	
    	// copy original board into new board
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			twins[i][j] = board[i * n + j];
    		}
    	}
    	
    	// swap [0, 0] and [0, 1] or [1, 0] and [1, 1]
    	if (twins[0][0] == 0 || twins[0][1] == 0) {
    		swap(twins, 1, 0, 1, 1);
    	} else {
    		swap(twins, 0, 0, 0, 1);
    	}
    	
    	return new Board(twins);
    }
    
    public boolean equals(Object y) {
    	// does this board equal y?
    	if (this == y)
    		return true;
    	
    	if (y == null)
    		return false;
    	
    	if (this.getClass() != y.getClass())
    		return false;
    	
    	Board that = (Board) y;
    	if (that.dimension() != n)
    		return false;
    	for (int i = 0; i < n * n; i++) {
    		if (this.board[i] != that.board[i])
    			return false;
    	}
    	return true;
    }
    
    public Iterable<Board> neighbors() {
    	// all neighboring boards
    	int row = 0;  // row of blank 0
    	int col = 0;  // column of blank 0
    	Stack<Board> neighbor = new Stack<Board>();
    	
    	int[][] copy = new int[n][n];
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			copy[i][j] = board[i * n + j];
    			if (copy[i][j] == 0) {
    				row = i;
    				col = j;
    			}		
    		}
    	}
    	
    	// exchange with left neighbor
    	if (col != 0) {
    		swap(copy, row, col, row, col - 1);
    		neighbor.push(new Board(copy));
    		swap(copy, row, col, row, col - 1);
    	}
    	// exchange with right neighbor
    	if (col != n - 1) {
    		swap(copy, row, col, row, col + 1);
    		neighbor.push(new Board(copy));
    		swap(copy, row, col, row, col + 1);
    	}
    	// exchange with upper neighbor
    	if (row != 0) {
    		swap(copy, row, col, row - 1, col);
    		neighbor.push(new Board(copy));
    		swap(copy, row, col, row - 1, col);
    	}
    	// exchange with lower neighbor
    	if (row != n - 1) {
    		swap(copy, row, col, row + 1, col);
    		neighbor.push(new Board(copy));
    	}
    	
    	return neighbor;
    }
    
    public String toString() {
    	// string representation of this board (in the output format specified below)
    	StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", board[i * n + j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
    	// unit tests (not graded)
    }
}