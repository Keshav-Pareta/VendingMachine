package Service.Impl;

import Entities.Cart;
import Service.InventoryManager;

public class InventoryManagerImpl implements InventoryManager {

    private Cart availableStocks;

    private Cart lockedStocks;

    public InventoryManagerImpl(Cart inventory) {
        this.availableStocks = inventory;
        this.lockedStocks = new Cart();
    }

    @Override
    public Boolean isAvailable(Cart cart) {
        return Cart.isAllItemsAvailable(availableStocks, cart);
    }

    @Override
    public Boolean lockStocks(Cart cart) {
        boolean isTransactionAvailable = availableStocks.removeProducts(cart);
        if(isTransactionAvailable){
            lockedStocks.addProducts(cart);
            return true;
        }
        return false;
    }

    @Override
    public synchronized void buyStocks(Cart cart) {
        lockedStocks.removeProducts(cart);
        return;
    }

    @Override
    public void releaseStocks(Cart cart) {
        lockedStocks.removeProducts(cart);
        availableStocks.addProducts(cart);
        return;
    }

    @Override
    public void addStock(Cart cart) {
        availableStocks.addProducts(cart);
    }

    @Override
    public Cart getCart() {
        return this.availableStocks;
    }
}
