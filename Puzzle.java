public abstract class Puzzle extends GameComponent
        implements Comparable<Puzzle> {

    protected int difficulty;
    protected boolean solved;

    public Puzzle(String name, int difficulty) {
        super(name);
        this.difficulty = difficulty;
        this.solved = false;
    }

    public boolean isSolved() {
        return solved;
    }

    @Override
    public int compareTo(Puzzle o) {
        return Integer.compare(this.difficulty, o.difficulty);
    }

    public abstract boolean attemptSolve(String answer)
            throws InvalidPuzzleAnswerException;
}
