package edu.iu.habahram.DinerPancakeHouseMerge.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CafeMenuIterator implements Iterator<MenuItem> {
    private final Map<String, MenuItem> menuItems;
    private final Iterator<Map.Entry<String, MenuItem>> iterator;

    public CafeMenuIterator(Map<String, MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.iterator = menuItems.entrySet().iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public MenuItem next() {
        return iterator.next().getValue();
    }
}