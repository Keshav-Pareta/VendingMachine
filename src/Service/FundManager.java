package Service;

import Entities.Amount;
import Entities.Cart;

public interface FundManager {

    public Amount createInvoice(Cart cart);
    public void executeTransaction(Amount amount);
    public boolean collectFunds(Amount amount);
    public Amount getAmount();


}
