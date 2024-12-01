import Entities.Amount;
import Entities.Cart;
import Entities.Product;
import Entities.VendingMachineStatus;
import Service.Impl.VendingMachineImpl;
import Service.VendingMachine;

public class Main {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachineImpl();
        Product product1 = new Product(new Amount(0,0,2,1,0), "Coke");
        Product product2 = new Product(new Amount(0,1,0,0, 1), "Sprite");
        Product product3 = new Product(new Amount(1,0,0,1, 0), "Apple Juice");

        Cart cart = new Cart();
        cart.addProduct(product1, 5);
        cart.addProduct(product2);
        cart.addProduct(product3);

        vendingMachine.restockProduct(cart);

        VendingMachineStatus vendingMachineStatus = vendingMachine.getStatus();

        System.out.println(vendingMachineStatus.toString());

        cart = new Cart();
        cart.addProduct(product1, 2);
        Amount amount = vendingMachine.buyProduct(cart);
        System.out.println("BILLABLE AMOUNT : " + amount.toString());

        System.out.println(vendingMachine.getStatus().toString());

    }
}