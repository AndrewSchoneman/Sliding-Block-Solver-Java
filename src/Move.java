import java.util.Arrays;
import java.util.LinkedList;


 class Move {
	
	private static int findZero(int[] puzzle) {
		for(int i = 0; i < puzzle.length; i++) {
			if(puzzle[i] == 0) return i; 
		}
		return -1; 
	}
	
	private static boolean canMoveLeft(int[] puzzle) {
		if(findZero(puzzle) == -1) return false; 
		return findZero(puzzle) -1 >= 0; 
	}
	
	private static boolean canMoveRight(int[] puzzle) {
		if(findZero(puzzle) == -1) return false;
		return findZero(puzzle) + 1 < puzzle.length; 
	}

	private static boolean canMoveDown(int[] puzzle) {
		if(findZero(puzzle) == -1) return false;
		return findZero(puzzle) +3 < puzzle.length; 
	}

	private static boolean canMoveUp(int[] puzzle) {
		if(findZero(puzzle) == -1) return false;
		return findZero(puzzle) -3 > 0; 
	}
	
	private static int[] moveLeft(int[] puzzle) {
		int[] puzzleMove = Arrays.copyOf(puzzle, 9);
		if(canMoveLeft(puzzleMove)) {		
			int zeroIndex = findZero(puzzleMove);
			int temp = puzzleMove[zeroIndex-1];
			puzzleMove[zeroIndex] = temp;
			puzzleMove[zeroIndex-1] = 0;
			return puzzleMove;
		}
		return puzzle;
	}
		
	private static int[] moveRight(int[] puzzle) {
		int[] puzzleMove = Arrays.copyOf(puzzle, 9);
		if(canMoveRight(puzzleMove)) {
			int zeroIndex = findZero(puzzleMove);
			int temp = puzzleMove[zeroIndex+1];
			puzzleMove[zeroIndex] = temp;
			puzzleMove[zeroIndex+1] = 0;
			return puzzleMove;
			 
		}
		return puzzle;
	}
	
	private static int[] moveDown(int[] puzzle) {
		int[] puzzleMove = Arrays.copyOf(puzzle, 9);
		if(canMoveDown(puzzleMove)) {
			int zeroIndex = findZero(puzzleMove);
			int temp = puzzleMove[zeroIndex+3];
			puzzleMove[zeroIndex] = temp;
			puzzleMove[zeroIndex+3] = 0;
			return puzzleMove;
			 
		}
		return puzzle;
	}
	
	private static int[] moveUp(int[] puzzle) {
		int[] puzzleMove = Arrays.copyOf(puzzle, 9);
		if(canMoveUp(puzzleMove)) {
			int zeroIndex = findZero(puzzleMove);
			int temp = puzzleMove[zeroIndex-3];
			puzzleMove[zeroIndex] = temp;
			puzzleMove[zeroIndex-3] = 0;
			return puzzleMove;
			 
		}
		return puzzle;
	}
	
	public static LinkedList<int[]> generateMoves (int[] puzzle){
		LinkedList<int[]> moves  = new LinkedList<int[]>(); 
		moves.add(moveLeft(puzzle));
		moves.add(moveRight(puzzle));
		moves.add(moveUp(puzzle));
		moves.add(moveDown(puzzle));
		return moves; 
	}
}