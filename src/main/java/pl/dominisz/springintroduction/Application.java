package pl.dominisz.springintroduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.dominisz.springintroduction.model.OrderItem;
import pl.dominisz.springintroduction.service.*;
import pl.dominisz.springintroduction.model.CreditCard;
import pl.dominisz.springintroduction.model.Order;
import pl.dominisz.springintroduction.model.Receipt;

import java.math.BigDecimal;
import java.time.LocalDate;

@ComponentScan
public class Application {

    public static void main(String[] args) {

//        CreditCardProcessor processor = new PaypalCreditCardProcessor();
//        TransactionLog transactionLog = new DatabaseTransactionLog();
//        BillingService billingService = new CreditCardBillingService(processor, transactionLog);

        Order order = new Order();
        OrderItem hotDog = new OrderItem("Hot dog", new BigDecimal("3.59"));
        OrderItem coffee = new OrderItem("coffee", new BigDecimal("4.59"));

        order.addItem(hotDog);
        order.addItem(coffee);

        CreditCard creditCard = new CreditCard("Imie",
                "Nazwisko",
                "123",
                LocalDate.of(2002, 5, 1));

        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        BillingService billingService = context.getBean(CreditCardBillingService.class);
        TransactionLog transactionLog = context.getBean(TransactionLog.class);

        Receipt receipt = billingService.chargeOrder(order, creditCard);

        System.out.println(receipt);
    }

}
