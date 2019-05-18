package pl.dominisz.springintroduction;

import pl.dominisz.springintroduction.service.*;
import pl.dominisz.springintroduction.model.CreditCard;
import pl.dominisz.springintroduction.model.Order;
import pl.dominisz.springintroduction.model.Receipt;

public class Application {

    public static void main(String[] args) {

        BillingService billingService = new CreditCardBillingService();

        Order order = new Order();
        CreditCard creditCard = new CreditCard();

        Receipt receipt = billingService.chargeOrder(order, creditCard);

        System.out.println(receipt);
    }

}
