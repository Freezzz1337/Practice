package com.github.shop.controllers;

import com.github.shop.modelsBD.CarModel;
import com.github.shop.repos.CarModelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private CarModelsRepository carModelsRepository;

    @GetMapping("/")
    public String home(Model model) {
        Iterable<CarModel> carModels = carModelsRepository.findAll();
        model.addAttribute("carModels", carModels);
        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String addPost(@RequestParam String name,
                          @RequestParam String color,
                          @RequestParam String description,
                          @RequestParam String fuel,
                          @RequestParam Double mileage,
                          @RequestParam Double engine,
                          @RequestParam Double price,
                          @RequestParam("picture") Optional<MultipartFile> picture,
                          Model model) throws IOException {
        CarModel carModels = new CarModel(name, color, description, fuel, mileage, engine, price, picture.get().getBytes());
        carModelsRepository.save(carModels);
        return "add";
    }
}