package Service.Impl;

import Entities.Amount;
import Entities.Cart;
import Service.FundManager;

public class FundManagerImpl implements FundManager {
    private Amount amount;

    public FundManagerImpl(Amount amount) {
        this.amount = amount;
    }

    @Override
    public Amount createInvoice(Cart cart) {
        Amount amount = new Amount();
        cart.products.keySet().forEach(product -> {
            amount.incrementAmount(product.getPerUnitPrice());
        });
        return amount;
    }

    @Override
    public synchronized void executeTransaction(Amount amount) {
        this.amount.incrementAmount(amount);
        return;
    }

    @Override
    public synchronized boolean collectFunds(Amount amount) {
        return this.amount.deductAmount(amount);
    }

    @Override
    public Amount getAmount() {
        return this.amount;
    }
}
