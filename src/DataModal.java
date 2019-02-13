import java.io.Serializable;

public class DataModal implements Serializable {
    private int level;
    private long answer;

    private DataModal(int level, long answer) {
        this.level = level;
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public long getAnswer() {
        return answer;
    }

    static class Builder {

        private int level;
        private long answer;

        Builder level(int level) {
            this.level = level;
            return this;
        }

        Builder answer(long answer) {
            this.answer = answer;
            return this;
        }

        DataModal build() {
            return new DataModal(level, answer);
        }
    }
}