package Entities;

public record VendingMachineStatus(Amount amount, Cart cart){
    @Override
    public String toString() {
        return "VENDING MACHINE STATUS\n" + amount.toString() + "\n" +
                cart.toString();
    }
}
