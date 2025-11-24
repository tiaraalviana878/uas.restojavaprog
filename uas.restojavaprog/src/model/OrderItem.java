package model;

public class OrderItem {
    private final MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item) {
        this.item = item;
        this.quantity = 1;
    }

    public MenuItem getItem() { return item; }
    public String getName() { return item.getName(); }
    public int getQuantity() { return quantity; }

    public void increment() { quantity++; }
    public void decrement() { quantity = Math.max(0, quantity - 1); }

    public int getTotalPrice() { return item.getPrice() * quantity; }
}
