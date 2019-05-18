package pl.dominisz.springintroduction.model;

public class ChargeResult {

    private boolean successfull;
    private String message;

    public ChargeResult(boolean successfull, String message) {
        this.successfull = successfull;
        this.message = message;
    }

    public boolean isSuccessfull() {
        return successfull;
    }


    public String getDeclineMessage() {
        return message;
    }
}
