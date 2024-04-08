package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItemRecord;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.MergerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MergerController {

    private final MergerRepository mergerRepository;

    public MergerController(MergerRepository mergerRepository) {
        this.mergerRepository = mergerRepository;
    }

    @GetMapping("/{menuType}")
    public ResponseEntity<List<MenuItemRecord>> getMenuItemsByType(@PathVariable String menuType) {
        Predicate<MenuItem> filter;
        switch (menuType) {
            case "all":
                filter = item -> true;
                break;
            case "vegetarian":
                filter = MenuItem::isVegetarian;
                break;
            case "breakfast":
                filter = item -> "Breakfast".equals(item.getDescription());
                break;
            case "lunch":
                filter = item -> "Lunch".equals(item.getDescription());
                break;
            case "dinner":
                filter = item -> "Dinner".equals(item.getDescription());
                break;
            default:
                return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(filterItems(filter));
    }

    private List<MenuItemRecord> filterItems(Predicate<MenuItem> filter) {
        Iterator<MenuItem> items = mergerRepository.getAllMenus().filterItems(filter);
        Iterable<MenuItem> iterable = () -> items;
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(item -> new MenuItemRecord(item.getName(), item.getDescription(), item.isVegetarian(), item.getPrice()))
                .collect(Collectors.toList());
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/customers.txt", true))) {
            writer.write(username + "," + password + "," + email + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error saving user data.");
        }
        return ResponseEntity.ok("Signup successful!");
    }
}