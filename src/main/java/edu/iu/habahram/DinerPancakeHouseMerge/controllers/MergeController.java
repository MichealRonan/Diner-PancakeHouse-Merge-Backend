package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItem;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.DinerRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.PancakeHouseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/merger")
public class MergeController {

    private final DinerRepository dinerRepository;
    private final PancakeHouseRepository pancakeHouseRepository;

    public MergeController(DinerRepository dinerRepository, PancakeHouseRepository pancakeHouseRepository) {
        this.dinerRepository = dinerRepository;
        this.pancakeHouseRepository = pancakeHouseRepository;
    }
    @GetMapping
    public List<MenuItem> getMergedMenu() {
        List<MenuItem> dinerMenuItems = Arrays.asList(dinerRepository.getTheMenu());
        List<MenuItem> pancakeHouseMenuItems = pancakeHouseRepository.getTheMenu();
        List<MenuItem> mergedMenuItems = Stream.concat(dinerMenuItems.stream(), pancakeHouseMenuItems.stream()).collect(Collectors.toList());
        mergedMenuItems.sort(Comparator.comparing(MenuItem::getName));
        return mergedMenuItems;
    }
}