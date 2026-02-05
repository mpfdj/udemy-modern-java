package com.modernjava.payment;

import com.modernjava.domain.OrderDetails;
import com.modernjava.domain.PaymentResponse;

public class PaymentService {

    public PaymentResponse makePayment(OrderDetails orderDetails) {
        var cardType = orderDetails.card().cardType();
        var card = orderDetails.card();
        var amount = orderDetails.finalAmount();

        var paymentGateway = PaymentFactory.paymentGateway(cardType);  // Implement a payment gateway that can handle the different kinds of payments.

        return paymentGateway.makePayment(card, amount);
    }
}
