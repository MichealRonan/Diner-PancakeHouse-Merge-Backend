package edu.iu.habahram.DinerPancakeHouseMerge.model;


import java.util.Collections;
import java.util.Iterator;
import java.util.function.Predicate;

public class MenuItem extends MenuComponent{
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name,
                    String description,
                    boolean vegetarian,
                    double price)
    {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
    public String toString() {
        return (name + ", $" + price + "\n   " + description);
    }
    public MenuItem[] getItems() {
        MenuItem[] items = new MenuItem[1];
        items[0] = this;
        return items;
    }

    public Iterator<MenuComponent> createIterator() {
        return null;
    }

    @Override
    public Iterator<MenuItem> filterItems(Predicate<MenuItem> predicate) {
        if (predicate.test(this)) {
            return Collections.singleton(this).iterator();
        } else {
            return Collections.emptyIterator();
        }
    }
}