package pl.dominisz.springintroduction.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(OrderItem orderItem) {
        items.add(orderItem);
    }

    public BigDecimal getAmount() {

        BigDecimal sum = BigDecimal.ZERO;

        for (OrderItem orderItem : items
        ) {
            //poprawić na stream
            sum = sum.add(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));

        }


        return sum;

    }


}
