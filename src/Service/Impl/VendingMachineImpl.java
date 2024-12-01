package Service.Impl;

import Entities.Amount;
import Entities.Cart;
import Entities.VendingMachineStatus;
import Service.FundManager;
import Service.InventoryManager;
import Service.VendingMachine;

public class VendingMachineImpl implements VendingMachine {

    FundManager fundManager;

    InventoryManager inventoryManager;

    public VendingMachineImpl(Cart inventory, Amount cash) {
       fundManager = new FundManagerImpl(cash);
       inventoryManager = new InventoryManagerImpl(inventory);
    }

    public VendingMachineImpl() {
        fundManager = new FundManagerImpl(new Amount());
        inventoryManager = new InventoryManagerImpl(new Cart());
    }

    @Override
    public Amount buyProduct(Cart cart) {
        if(inventoryManager.lockStocks(cart)){
            Amount invoice = fundManager.createInvoice(cart);
            fundManager.executeTransaction(invoice);
            inventoryManager.buyStocks(cart);
            return invoice;
        } else {
            System.out.println("Sorry all products are not available");
            return new Amount();
        }
    }

    @Override
    public void restockProduct(Cart cart) {
        inventoryManager.addStock(cart);
    }

    @Override
    public boolean collectFunds(Amount amount) {
        return fundManager.collectFunds(amount);
    }

    @Override
    public VendingMachineStatus getStatus() {
        return new VendingMachineStatus(fundManager.getAmount(), inventoryManager.getCart());
    }
}
