import java.io.Serializable;

public class ResponseModal implements Serializable {
    private boolean is_successful;
    private boolean is_finished;
    private String msg;

    private ResponseModal(boolean is_successful, boolean is_finished, String msg) {
        this.is_successful = is_successful;
        this.is_finished = is_finished;
        this.msg = msg;
    }

    public boolean isSuccessful() {
        return is_successful;
    }

    public boolean isFinished() {
        return is_finished;
    }

    public String getMessage() {
        return msg;
    }

    static class Builder {

        private boolean is_successful;
        private boolean is_finished;
        private String msg;

        Builder isSuccessful(boolean is_successful) {
            this.is_successful = is_successful;
            return this;
        }

        Builder isFinished(boolean is_finished) {
            this.is_finished = is_finished;
            return this;
        }

        Builder message(String msg) {
            this.msg = msg;
            return this;
        }

        ResponseModal build() {
            return new ResponseModal(is_successful, is_finished, msg);
        }
    }
}