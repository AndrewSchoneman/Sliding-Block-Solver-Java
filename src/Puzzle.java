//import java.util.LinkedList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue; 
import java.util.HashSet; 
import java.util.Queue; 


 public class Puzzle{

	public static int findNum(int[] puzzle, int num) {
		for(int i = 0; i < puzzle.length; i++) {
			if(puzzle[i] == num) return i; 
		}
		return -1; 
	}	 
	 
  public static int[] createPuzzle(Scanner sc){
	    int[] puz = new int[9];
	    int count = 0;
	    int sum = 0; 
	    while(sc.hasNext()){
	      if(count > puz.length){
	        System.err.println("invalid puzzle given");
	        System.exit(-1);
	      }
	      int temp = Integer.parseInt(sc.next()); 
	      puz[count] = temp;
	      sum += temp; 
	      count++;
	    }
	    if(sum != 36) System.exit(-1);
	    return puz;
	  }
  public static void kickThingsOff(Scanner sc1, Scanner sc2) {
	  solve(createPuzzle(sc1), createPuzzle(sc2)); 	  
  }

  public static int getHeuristic(int[] puzzle, int[] solution) {
	    int heuristic = 0;

	    for(int i = 0; i < puzzle.length; i++) {
	        heuristic += getManhattanDistance(findNum(puzzle, i), findNum(solution, i));
	    }
	    return heuristic;
	  }
  
  public static int getManhattanDistance(int index, int number) {
	    return Math.abs((index / 3) - ((number-1) / 3)) + Math.abs((index % 3) - ((number-1) % 3));
	  }
  
  public static void solve(int[] puzzle, int[] solution) {
	  LinkedList<int[]> moves = Move.generateMoves(puzzle); 
	  HashSet<PuzzleState> visited = new HashSet<PuzzleState>();
	  Queue<PuzzleState> queue = new LinkedList<PuzzleState>(); 
	  boolean solved  = false;
	  for(int[] move : moves) {
		  visited.add(new PuzzleState(move, solution));
	  }
	  for(PuzzleState state : visited) {
		  if(state.isSolved()) {
			  System.out.println("Found a solution to the puzzle " + 
					  Arrays.toString(state.getPuzzle()) + " in one move" );			  
			  solved = true; 
		  }
		  queue.add(state);
	  }
	  moves.clear();
	  LinkedList<PuzzleState> statesToAdd = new LinkedList<PuzzleState>();
	  int count = 0; 
	  while(!queue.isEmpty() && !solved) {
		   
		  count++; 
		  moves = Move.generateMoves(queue.remove().getPuzzle());
		  for(int[] move : moves) {
			  PuzzleState state = new PuzzleState(move, solution);
			  statesToAdd.add(state);
			  if (state.isSolved()) {
				  System.out.println("Found a solution to the puzzle " + 
						  Arrays.toString(state.getPuzzle()) + " in "  + count + " moves" );
				  solved = true;  
				  break; 
			  }
			 
		  }
		  if(solved) break; 
		  for(PuzzleState state : statesToAdd) {
			  if(!visited.contains(state)){
				 
				  queue.add(state); 
				  visited.add(state );
			  }
		  }
		  statesToAdd.clear(); 
	  }
	  System.out.println(visited.size()); 
	  if(queue.isEmpty()) System.out.println("No solution found in " + count + " moves"); 
  }

  public static void main(String[] args) {

 if (args.length != 2) {
  	   System.out.println("Argument must be the name of a file");

  	} else try {
  		System.out.println(args[0] + " " + args[1]);
  		kickThingsOff(( new Scanner( new File( args[0] ) )) , ( new Scanner( new File( args[1] ) ) ) );

  	} catch (FileNotFoundException e) {
  	    System.out.println("File not found");
  	}

  }
  
}