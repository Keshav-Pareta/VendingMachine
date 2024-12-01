package Service;

import Entities.Amount;
import Entities.Cart;
import Entities.VendingMachineStatus;

;

public interface VendingMachine {

    //This will return complete amount if the transaction is unsuccessful, else the deducted amount along with product
    public Amount buyProduct(Cart cart);

    public void restockProduct(Cart cart);

    // Return True if the transaction is successfully else false;
    public boolean collectFunds(Amount amount);

    // Return Amount and Cart;
    public VendingMachineStatus getStatus();

}
