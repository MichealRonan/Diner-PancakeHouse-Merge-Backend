package edu.iu.habahram.DinerPancakeHouseMerge.repository;

import edu.iu.habahram.DinerPancakeHouseMerge.model.CafeMenu;
import edu.iu.habahram.DinerPancakeHouseMerge.model.Menu;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import org.springframework.stereotype.Repository;

import java.util.Iterator;

@Repository
public class CafeRepository {
    public Iterator<MenuItem> getTheMenuIterator() {
        Menu cafe = new CafeMenu();
        return cafe.createIterator();
    }
}