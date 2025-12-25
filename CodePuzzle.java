public class CodePuzzle extends Puzzle {

   
    private int code;

    public CodePuzzle(String name, int difficulty, int code) {
        super(name, difficulty);
        this.code = code;
    }

    @Override
    public boolean attemptSolve(String answer)
            throws InvalidPuzzleAnswerException {
        try {
            int input = Integer.parseInt(answer);
            if (input == code) {
                solved = true;
                System.out.println("Code unlocked!");
                return true;
            }
        } catch (NumberFormatException e) {
            throw new InvalidPuzzleAnswerException("Not a number!");
        }
        throw new InvalidPuzzleAnswerException("Incorrect code!");
    }

    @Override
    public void inspect() {
        System.out.println("Code Puzzle: " + name +
                " (difficulty " + difficulty + ")");
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

