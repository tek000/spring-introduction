package pl.dominisz.springintroduction;

import pl.dominisz.springintroduction.service.*;
import pl.dominisz.springintroduction.model.CreditCard;
import pl.dominisz.springintroduction.model.Order;
import pl.dominisz.springintroduction.model.Receipt;

import java.time.LocalDate;

public class Application {

    public static void main(String[] args) {

        BillingService billingService = new CreditCardBillingService();

        Order order = new Order();
        CreditCard creditCard = new CreditCard("Imie",
                "Nazwisko",
                "123",
                LocalDate.of(2002,5,1));

        Receipt receipt = billingService.chargeOrder(order, creditCard);

        System.out.println(receipt);
    }

}
