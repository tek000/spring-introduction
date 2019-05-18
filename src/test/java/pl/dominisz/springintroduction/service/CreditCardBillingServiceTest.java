package pl.dominisz.springintroduction.service;


import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import pl.dominisz.springintroduction.exception.UnreachableException;
import pl.dominisz.springintroduction.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CreditCardBillingServiceTest {


    CreditCardProcessor processor = mock(CreditCardProcessor.class);
    TransactionLog transactionLog = mock(TransactionLog.class);
    Order order = new Order();
    OrderItem hotDog = new OrderItem("Hot dog", new BigDecimal("3.59"));
    OrderItem coffee = new OrderItem("coffee", new BigDecimal("4.59"));


    CreditCard card = new CreditCard("Homer",
            "Simpson",
            "123",
            LocalDate.of(2020, 10, 10));


    @Test
    public void shouldSucessfullyChargeCardTest() {
//        CreditCardProcessor processor = new PaypalCreditCardProcessor();
//        TransactionLog transactionLog = new DatabaseTransactionLog();

        //given

        order.addItem(hotDog);
        order.addItem(coffee);

        CreditCard card = new CreditCard("Homer",
                "Simpson",
                "123",
                LocalDate.of(2020, 10, 10));

        //when

        Mockito.when(processor.charge(card, order.getAmount())).thenReturn(new ChargeResult(true, "succesfull"));
        CreditCardBillingService creditCardBillingService = new CreditCardBillingService(processor, transactionLog);
        Receipt receipt = creditCardBillingService.chargeOrder(order, card);

        //then
        assertTrue(receipt.isSucessfull());
        assertEquals(new BigDecimal("8.18"), receipt.getAmount());

    }


    @Test
    public void shouldDeclineChargeCardTest() {

        //given
        order.addItem(hotDog);
        order.addItem(coffee);
        //when
        Mockito.when(processor.charge(card, order.getAmount())).thenReturn(new ChargeResult(false, "declined"));

        CreditCardBillingService creditCardBillingService = new CreditCardBillingService(processor, transactionLog);
        Receipt receipt = creditCardBillingService.chargeOrder(order, card);

        //then
        assertFalse(receipt.isSucessfull());


    }


    @Test
    public void shouldThrowException() {
        //given
        order.addItem(hotDog);
        order.addItem(coffee);
        //when
        Mockito.when(processor.charge(card, order.getAmount())).thenThrow(new UnreachableException("Broken connection"));

        CreditCardBillingService creditCardBillingService = new CreditCardBillingService(processor, transactionLog);
        Receipt receipt = creditCardBillingService.chargeOrder(order, card);

        //then
        assertFalse(receipt.isSucessfull());
        assertEquals("Broken connection",receipt.getMessage());

    }

}
