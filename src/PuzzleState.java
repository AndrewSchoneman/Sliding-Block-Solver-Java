import java.util.Arrays;


 class PuzzleState {
  private int[] puzzle;
  private final int[] solvedState; 
  private PuzzleState previousState; 
  private static int[] solvedState1; 
  private int g = 0; 
  private int h; 
  
  public PuzzleState(int[] state, int[] solvedState) {
	  puzzle = state; 
	  this.solvedState = solvedState; 
	  this.previousState = null; 
	  this.g= 0; 
	  this.h = Puzzle.getHeuristic(this.puzzle, this.solvedState);
  }
  
  public PuzzleState(int[] state, int[] solvedState, PuzzleState previousState) {
	  puzzle = state; 
	  this.solvedState = solvedState; 
	  this.previousState = previousState; 
	  this.g = previousState.g + 1; 
	  this.h = Puzzle.getHeuristic(this.puzzle, solvedState);
  }
  
  public boolean isSolved() {
	  return Arrays.equals(this.puzzle, this.solvedState); 
  }
  
  public int[] getPuzzle() {
	  return this.puzzle; 
  }
  
  public void sSolvedState(int[] solution) {
	  PuzzleState.solvedState1 = solution; 
  }
  
  public boolean isSolved1() {
	  return Arrays.equals(this.puzzle, PuzzleState.solvedState1); 
  }
  
  public int g() {
	    return this.g;
  }
  public int h() {
	    return this.h;
	  }

  public int f() {
    return g() + h();
  }

  public PuzzleState getPrevious() {
    return this.previousState;
  }
  
  @Override
  public String toString() {
    int[] state = this.puzzle;
    String s = "\n\n";
    for(int i = 0; i < state.length; i++) {
      if(i % 3 == 0 && i != 0) s += "\n";
      s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
	 }
	    return s;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PuzzleState state = (PuzzleState) o;
    return Arrays.equals(this.puzzle, state.puzzle);
  }
  @Override
  public int hashCode() {
    return Arrays.hashCode(puzzle);
  }
}
  
