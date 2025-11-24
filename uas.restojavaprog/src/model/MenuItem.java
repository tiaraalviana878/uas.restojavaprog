package model;

public class MenuItem {
    private final String name;
    private final int price;
    private final String imagePath;

    public MenuItem(String name, int price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
    }

    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImagePath() { return imagePath; }
}
