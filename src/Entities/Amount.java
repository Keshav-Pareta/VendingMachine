package Entities;

public class Amount {
    private int note100;
    private int note50;
    private int note10;
    private int coin5;
    private int coin1;
    private int total;

    public Amount() {
    }

    private static int getTotal(int note100, int note50, int note10, int coin5, int coin1) {
        return 100 * note100 + 50 * note50 + 10 * note10 + 5 * coin5 + coin1;
    }

    private static int getTotal(Amount amount) {
        return 100 * amount.getnote100() + 50 * amount.getnote50() + 10 * amount.getnote10() + 5 * amount.getCoin5() + amount.getCoin1();
    }

    public Amount(int note100, int note50, int note10, int coin5, int coin1) {
        this.note100 = note100;
        this.note50 = note50;
        this.note10 = note10;
        this.coin5 = coin5;
        this.coin1 = coin1;
        this.total = getTotal(note100, note50, note10, coin5, coin1);
    }

    public synchronized boolean deductAmount(Amount amount) {
            int incomingTotal = getTotal(amount);
            if(incomingTotal < this.total){
                System.out.println("Insufficient Funds");
                return false;
            }
            int originalNote100 = this.note100;
            int originalNote50 = this.note50;
            int originalNote10 = this.note10;
            int originalCoin5 = this.coin5;
            if(this.note100 >= note100) this.note100 -= amount.note100;
            else {
                amount.note100 -= this.note100;
                this.note100 = 0;
                amount.note50 += 2*amount.note100;
            }
            if(this.note50 >= amount.note50) this.note50 -= amount.note50;
            else {
                amount.note50 -= this.note50;
                this.note50 = 0;
                amount.note10 += 5*amount.note50;
            }
            if(this.note10 >= amount.note10) this.note10 -= amount.note10;
            else {
                amount.note10 -= this.note10;
                this.note10 = 0;
                amount.coin5 += 2*amount.note10;
            }
            if(this.coin5 >= amount.coin5) this.coin5 -= amount.coin5;
            else {
                amount.coin5 -= this.coin5;
                this.coin5 = 0;
                amount.coin1 += 5*amount.coin5;
            }
            if(this.coin1 >= amount.coin1) this.coin1 -= amount.coin1;
            else {
                this.note100 = originalNote100;
                this.note50 = originalNote50;
                this.note10 = originalNote10;
                this.coin5 = originalCoin5;
                System.out.println("Sorry not enough change available");
                return false;
            }
            return true;
    }

    public synchronized void incrementAmount(Amount amount) {
        int incomingTotal = getTotal(amount);
        this.total += incomingTotal;
        this.note100 += amount.note100;
        this.note50 += amount.note50;
        this.note10 += amount.note10;
        this.coin5 += amount.coin5;
        this.coin1 += amount.coin1;
    }

    public int getnote100() {
        return note100;
    }

    public int getnote50() {
        return note50;
    }

    public int getnote10() {
        return note10;
    }

    public int getCoin5() {
        return coin5;
    }

    public int getCoin1() {
        return coin1;
    }

    public int getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Amount : \n");
        stringBuilder
                .append("Note100 : ").append(this.note100).append("\n")
                .append("Note50 : ").append(this.note50).append("\n")
                .append("Note10 : ").append(this.note10).append("\n")
                .append("Coin5 : ").append(this.coin5).append("\n")
                .append("Coin1 : ").append(this.coin1).append("\n");
        return stringBuilder.toString();
    }
}
