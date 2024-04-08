package edu.iu.habahram.DinerPancakeHouseMerge.repository;

import edu.iu.habahram.DinerPancakeHouseMerge.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;
import java.util.List;
@CrossOrigin
@Repository
public class MergerRepository {
    private final MenuComponent allMenus;  // Maintain a single instance of allMenus

    public MergerRepository() {
        allMenus = new Menu("ALL MENUS", "All menus combined");
        initializeMenus();
    }

    private void initializeMenus() {
        allMenus.add(new DinerMenu("DINER MENU", "Lunch"));
        allMenus.add(new PancakeHouseMenu("PANCAKE HOUSE MENU", "Breakfast"));
        allMenus.add(new CafeMenu("CAFE MENU", "Dinner"));
    }

    public List<MenuItemRecord> getTheMenuItems() {
        MenuItem[] menuItems = allMenus.getItems();  // Assuming getItems returns a flat array of all MenuItem instances
        return Arrays.stream(menuItems)
                .map(item -> new MenuItemRecord(item.getName(),
                        item.getDescription(),
                        item.isVegetarian(),
                        item.getPrice()))
                .toList();
    }

    public MenuComponent getAllMenus() {
        return allMenus;
    }
}
