package edu.iu.habahram.DinerPancakeHouseMerge.model;

import java.util.*;

public class CompositeIterator implements Iterator<MenuComponent> {
    private Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        if (iterator != null && iterator.hasNext()) {
            stack.push(iterator);
        }
    }

    public boolean hasNext() {
        while (!stack.isEmpty()) {
            Iterator<MenuComponent> iterator = stack.peek();
            if (iterator.hasNext()) {
                return true;
            } else {
                stack.pop();
            }
        }
        return false;
    }

    public MenuComponent next() {
        if (hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent component = iterator.next();
            Iterator<MenuComponent> childIterator = component.createIterator();
            if (childIterator != null && childIterator.hasNext()) {
                stack.push(childIterator);
            }
            return component;
        }
        throw new NoSuchElementException();
    }
}
