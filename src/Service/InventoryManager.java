package Service;

import Entities.Cart;

public interface InventoryManager {
    public Boolean isAvailable(Cart cart);
    public Boolean lockStocks(Cart cart);
    public void buyStocks(Cart cart);
    public void releaseStocks(Cart cart);
    public void addStock(Cart cart);
    public Cart getCart();
}
