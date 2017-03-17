import java.io.*;

public class Score implements Serializable {
    private int score;
    private String name;
    private String difficulty;

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    public String getDifficulty() {
        return difficulty;
    }

    public Score(String difficulty, String name, int score) {
        this.difficulty = difficulty;
        this.score = score;
        this.name = name;
    }
}

