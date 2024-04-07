package edu.iu.habahram.DinerPancakeHouseMerge;

import edu.iu.habahram.DinerPancakeHouseMerge.model.*;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

class MergerRepositoryTest {
    @Test
    void test() {
        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
        allMenus.add(new DinerMenu("DINER MENU", "Lunch"));
        allMenus.add(new PancakeHouseMenu("PANCAKE HOUSE MENU", "Breakfast"));
        allMenus.add(new CafeMenu("CAFE MENU", "Dinner"));
        Iterator<MenuComponent> iterator = allMenus.createIterator();

        while (iterator.hasNext()) {
            MenuComponent component = iterator.next();
            System.out.println(component.getName());
        }
    }

}
