package com.github.shop.controllers;

import com.github.shop.modelsBD.CarModel;
import com.github.shop.repos.CarModelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CarDetails {

    private final CarModelsRepository carModelsRepository;

    @GetMapping("/carDetails/{id}")
    public String carDetails(@PathVariable(value = "id") long id, Model model) {

        if (!carModelsRepository.existsById(id)) {
            return "redirect:/";
        }

        Optional<CarModel> carModels = carModelsRepository.findById(id);
        ArrayList<CarModel> arr = new ArrayList<>();
        carModels.ifPresent(arr::add);
        model.addAttribute("carModels", arr);
        return "carDetails";
    }


    @GetMapping("/carDetails/{id}/edit")
    public String carDetailsEdit(@PathVariable(value = "id") long id, Model model) {

        if (!carModelsRepository.existsById(id)) {
            return "redirect:/";
        }

        Optional<CarModel> carModels = carModelsRepository.findById(id);
        ArrayList<CarModel> arr = new ArrayList<>();
        carModels.ifPresent(arr::add);
        model.addAttribute("carModels", arr);
        return "carDetailsEdit";
    }

    @PostMapping("/carDetails/{id}/edit")
    public String carDetailsUpdate(@PathVariable(value = "id") long id,
                                  @RequestParam String name,
                                  @RequestParam String color,
                                  @RequestParam String description,
                                  @RequestParam String fuel,
                                  @RequestParam Double mileage,
                                  @RequestParam Double engine,
                                  @RequestParam Double price,
                                  @RequestParam("picture") Optional<MultipartFile> picture,
                                  Model model) throws IOException{
        CarModel carModel = carModelsRepository.findById(id).get();
        carModel.setName(name);
        carModel.setColor(color);
        carModel.setDescription(description);
        carModel.setFuel(fuel);
        carModel.setMileage(mileage);
        carModel.setEngine(engine);
        carModel.setPrice(price);
        carModel.setPicture(picture.get().getBytes());
        carModelsRepository.save(carModel);
        return "redirect:/";
    }

    @PostMapping("/carDetails/{id}/remove")
    public String carDetailsRemove(@PathVariable(value = "id") long id, Model model) throws IOException{
        CarModel carModel = carModelsRepository.findById(id).get();
        carModelsRepository.delete(carModel);
        return "redirect:/";
    }
}
