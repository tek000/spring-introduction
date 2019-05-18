package pl.dominisz.springintroduction.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void newOrderIsEmptyTest() {
        //when
        Order order = new Order();
        //then
        assertTrue(order.getItems().isEmpty());
        assertEquals(BigDecimal.ZERO, order.getAmount());

    }


    @Test
    public void add2ItemsToOrder() {
        //given
        Order order = new Order();
        OrderItem bike = new OrderItem("rower", new BigDecimal("1250.49"));
        OrderItem scooter = new OrderItem("hulajnoga", new BigDecimal("800.45"));

        //when
        order.addItem(bike);
        order.addItem(scooter);

        //then
        assertEquals(order.getItems().size(), 2);
        assertEquals(order.getAmount(), new BigDecimal("2050.94"));

    }


}
