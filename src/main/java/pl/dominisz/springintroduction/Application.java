package pl.dominisz.springintroduction;

import pl.dominisz.springintroduction.service.BillingService;
import pl.dominisz.springintroduction.service.CreditCardBillingService;

public class Application {

    public static void main(String[] args) {

        BillingService billingService = new CreditCardBillingService();

        PizzaOrder pizzaOrder = new PizzaOrder();
        CreditCard creditCard = new CreditCard();

        Receipt receipt = billingService.chargeOrder(pizzaOrder, creditCard);

        System.out.println(receipt);
    }

}
