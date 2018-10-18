import java.util.Arrays;


 class PuzzleState {
  private int[] puzzle;
  private final int[] solvedState; 
  private int start;
  private int finish; 

  public PuzzleState(int[] state, int[] solvedState) {
	  puzzle = state; 
	  this.solvedState = solvedState; 
  }
  
  public boolean isSolved() {
	  return Arrays.equals(this.puzzle, this.solvedState); 
  }
  
  public int[] getPuzzle() {
	  return this.puzzle; 
  }
  
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PuzzleState state = (PuzzleState) o;
    return Arrays.equals(puzzle, state.puzzle);
  }
  @Override
  public int hashCode() {
    return Arrays.hashCode(puzzle);
  }
}
  
