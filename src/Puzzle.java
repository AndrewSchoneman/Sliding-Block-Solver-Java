import java.io.*;
import java.util.*;

/*
 * @toDo
 * create a summation checker to check arbitrarily large inputs
 * implement smart search for all inputs and solutions
 * create auto generator for arbitrarily large inputs
 */

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
  
  public static boolean isSolvable(int[] p) {
	    int inversions = 0;	    
	    for(int i = 0; i < p.length - 1; i++) {

	      for(int j = i + 1; j < p.length; j++)
	        if(p[i] > p[j]) inversions++;
	      if(p[i] == 0 && i % 2 == 1) inversions++;
	    }
	    return (inversions % 2 == 0);
	  }
  
  public static int getHeuristic1(int[] puzzle, int[] solution) {
	    int heuristic = 0;

	    for(int i = 0; i < puzzle.length; i++) {
	    //	System.out.println(getManhattanDistance(findNum(puzzle, i), findNum(solution, i)));
	        if(puzzle[i] != 0) heuristic += getManhattanDistance(findNum(puzzle, i), findNum(solution, i));
	    }
	    return heuristic;
	  }
  
  public static int getHeuristic(int[] array, int[] array2) {
	    int heuristic = 0;

	    for(int i = 0; i < array.length; i++) {
	      if (array[i] != 0)
	   // 	  System.out.println(getManhattanDistance(i, array[i]));
	        heuristic += getManhattanDistance(i, array[i]);
	    }
	    return heuristic;
	  }
 

  public static String prettyPrint(int[] puzzle) {
	    int[] state = puzzle;
	    String s = "\n\n";
	    for(int i = 0; i < state.length; i++) {
	      if(i % 3 == 0 && i != 0) s += "\n";
	      s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
		 }
		    return s;
  }
  
  public static int getManhattanDistance(int index, int number) {
	    return Math.abs((index / 3) - ((number-1) / 3)) + Math.abs((index % 3) - ((number-1) % 3));
	  }
  
  public static final PriorityQueue<PuzzleState> queue = new PriorityQueue<PuzzleState>(1000, new Comparator<PuzzleState>() {
	    @Override
	    public int compare(PuzzleState o1, PuzzleState o2) {
	      return o1.f() - o2.f();
	    }
	  });
  
  public static void checkSolveable(int[] puzzle) {
	  if(!isSolvable(puzzle)) {
		  System.out.println("The puzzle:" + prettyPrint(puzzle) + "\n\nis not solveable");
		  System.exit(0); 
	  }
  }
  
  public static void solve(int[] puzzle, int[] solution) {
	  checkSolveable(puzzle);
	  LinkedList<int[]> moves = Move.generateMoves(puzzle); 
	  HashSet<PuzzleState> visited = new HashSet<PuzzleState>();
	  PuzzleState currentState; 
	  boolean solved  = false;
	  for(int[] move : moves) {
		  visited.add(new PuzzleState(move, solution,  new PuzzleState(puzzle, solution)));
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
		  currentState = queue.remove(); 
		  moves = Move.generateMoves(currentState.getPuzzle());
		  for(int[] move : moves) {
			  PuzzleState state = new PuzzleState(move, solution, currentState);
			  statesToAdd.add(state);
			  if (state.isSolved()) {
				  System.out.println("Found a solution to the puzzle " + 
						  prettyPrint(puzzle) + "\n\nin "  + count + " moves" );
				  solved = true;  
				  break; 
			  }		 
		  }
		  if(solved) break; 
		  for(PuzzleState state : statesToAdd) {
			  if(!visited.contains(state)){
				  if (state.isSolved()) System.out.println("found a solution");
				  queue.add(state); 
				  visited.add(state );
			  }
		  }
		  statesToAdd.clear(); 
	  }
  }

  public static void main(String[] args) {

 if (args.length != 2) {
  	   System.out.println("Argument must be the name of a file");
  	} else try {
  		solve((createPuzzle( new Scanner( new File( args[0] ) )) ) , 
  				createPuzzle(( new Scanner( new File( args[1] ) ) ) ));
  	} catch (FileNotFoundException e) {
  	    System.out.println("File not found");
  	}
  }
}