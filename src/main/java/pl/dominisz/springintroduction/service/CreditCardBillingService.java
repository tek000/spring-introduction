package pl.dominisz.springintroduction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.dominisz.springintroduction.exception.UnreachableException;
import pl.dominisz.springintroduction.model.ChargeResult;
import pl.dominisz.springintroduction.model.CreditCard;
import pl.dominisz.springintroduction.model.Order;
import pl.dominisz.springintroduction.model.Receipt;

@Component
public class CreditCardBillingService implements BillingService {

    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Autowired
    public CreditCardBillingService(CreditCardProcessor processor, TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    @Override
    public Receipt chargeOrder(Order order, CreditCard creditCard) {


        try {
            ChargeResult result = processor.charge(creditCard, order.getAmount());
            transactionLog.logChargeResult(result);

            return result.isSuccessfull()
                    ? Receipt.forSuccessfulCharge(order.getAmount())
                    : Receipt.forDeclinedCharge(result.getDeclineMessage());

        } catch (UnreachableException e) {
            transactionLog.logConnectException(e);
            return Receipt.forSystemFailure(e.getMessage());
        }
    }

}
