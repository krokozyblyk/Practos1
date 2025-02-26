package com.example.practos1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/home")
    public String home() {
        return "index";
    }

    @GetMapping("/calculator")
    public String calculator() {
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("num1") double num1,
                            @RequestParam("num2") double num2,
                            @RequestParam("operation") String operation,
                            Model model) {
        double result = 0;
        switch (operation) {
            case "add":
                result = num1 + num2;
                break;
            case "subtract":
                result = num1 - num2;
                break;
            case "multiply":
                result = num1 * num2;
                break;
            case "divide":
                result = num1 / num2;
                break;
        }
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/converter")
    public String converter(Model model) {
        model.addAttribute("currencies", new String[]{"USD", "EUR", "RUB"});
        return "converter";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam("from") String from,
                          @RequestParam("to") String to,
                          @RequestParam("amount") double amount,
                          Model model) {
        double rate = getExchangeRate(from, to);
        double result = amount * rate;
        model.addAttribute("result", result);
        return "conversionResult";
    }

    private double getExchangeRate(String from, String to) {
        // Простая логика для примера
        if (from.equals("USD") && to.equals("EUR")) {
            return 0.85;
        } else if (from.equals("EUR") && to.equals("USD")) {
            return 1.18;
        } else if (from.equals("USD") && to.equals("RUB")) {
            return 0.95;
        } else if (from.equals("RUB") && to.equals("USD")) {
            return 1.30;
        } else if (from.equals("EUR") && to.equals("RUB")) {
            return 0.68;
        } else if (from.equals("RUB") && to.equals("EUR")) {
            return 1.45;
        } else {
            return 1.0;
        }
    }

}