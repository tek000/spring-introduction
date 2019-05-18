package pl.dominisz.springintroduction.model;

import java.math.BigDecimal;

public class Receipt {

    private BigDecimal amount;
    private String message;
    private boolean sucessfull;


    public Receipt(BigDecimal amount, String message, boolean sucessfull) {
        this.amount = amount;
        this.message = message;
        this.sucessfull = sucessfull;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSucessfull() {
        return sucessfull;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "amount=" + amount +
                ", message='" + message + '\'' +
                ", sucessfull=" + sucessfull +
                '}';
    }

    public static Receipt forSuccessfulCharge(BigDecimal amount) {

        return new Receipt(amount, null, true);
    }


    public static Receipt forDeclinedCharge(String declineMessage) {

        return new Receipt(null, declineMessage, false);
    }


    public static Receipt forSystemFailure(String message) {
        return new Receipt(null, message, false);
    }

}

