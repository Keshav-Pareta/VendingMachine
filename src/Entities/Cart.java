package Entities;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    public Map<Product, Integer> products;

    public Cart(){
        this.products = new HashMap<>();
    }

    public Cart(Map<Product, Integer> products) {
        this.products = products;
    }

    public synchronized void addProducts(Cart cart){
        cart.products.keySet().parallelStream().forEach(newProduct -> this.products.put(newProduct, this.products.getOrDefault(newProduct, 0) + cart.products.get(newProduct)));
        return;
    }

    public synchronized void addProduct(Product product, Integer quantity){
        this.products.put(product, this.products.getOrDefault(product, 0) + quantity);
    }


    public synchronized void addProduct(Product product){
       this.products.put(product, this.products.getOrDefault(product, 0) + 1);
    }

    public synchronized Boolean removeProducts(Cart cart){
        boolean isTransactionPossible = true;
        for(Product productToRemove : cart.products.keySet()){
            if(this.products.get(productToRemove) < cart.products.get(productToRemove)){
                isTransactionPossible = false;
            }
        }
        if(!isTransactionPossible){
            System.out.println("Transaction Not possible as items are not available");
            return false;
        }
        cart.products.keySet().parallelStream().forEach(productToRemove -> {
            this.products.put(productToRemove, this.products.get(productToRemove) - cart.products.get(productToRemove));
        });
        return true;
    }

    public synchronized static boolean isAllItemsAvailable(Cart inventory, Cart order){
        return order.products.keySet().parallelStream().allMatch(product -> inventory.products.getOrDefault(product, 0) >= order.products.get(product));
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder("Inventory : \n");
        for(Product product : products.keySet()){
            ans.append(product.getName()).append(" : ").append(products.get(product).toString()).append("\n");
        }
        return ans.toString();
    }
}
