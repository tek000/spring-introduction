package pl.dominisz.springintroduction.service;

import org.springframework.stereotype.Component;
import pl.dominisz.springintroduction.exception.UnreachableException;
import pl.dominisz.springintroduction.model.ChargeResult;
import pl.dominisz.springintroduction.model.CreditCard;

import java.math.BigDecimal;
import java.util.Random;
@Component
public class PaypalCreditCardProcessor implements CreditCardProcessor {


    private Random random = new Random();

    public ChargeResult charge(CreditCard creditCard, BigDecimal amount) {

        int number = random.nextInt(3);

        if (number == 0) {
            System.out.println("I'm charging card: " + creditCard.getNumber() + " for: " + amount);
            return new ChargeResult(true, "Succesfull charge");

        } else if (number == 1) {
            System.out.println("Charging card " + creditCard.getNumber() + " failed");
            return new ChargeResult(false, "Limits excedded");

        } else {
            System.out.println("Broken network connection");
            throw new UnreachableException("Broken network connection");
        }


    }
}
