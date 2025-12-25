public class RiddlePuzzle extends Puzzle {

 
    private String correctAnswer;

    public RiddlePuzzle(String name, int difficulty, String answer) {
        super(name, difficulty);
        this.correctAnswer = answer;
    }

    @Override
    public boolean attemptSolve(String answer)
            throws InvalidPuzzleAnswerException {
        if (answer.equalsIgnoreCase(correctAnswer)) {
            solved = true;
            System.out.println("Riddle solved!");
            return true;
        }
        throw new InvalidPuzzleAnswerException("Wrong riddle answer!");
    }

    @Override
    public void inspect() {
        System.out.println("Riddle Puzzle: " + name +
                " (difficulty " + difficulty + ")");
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
