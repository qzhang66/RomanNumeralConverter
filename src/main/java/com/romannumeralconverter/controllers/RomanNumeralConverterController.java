package com.romannumeralconverter.controllers;

import com.romannumeralconverter.services.RomanNumeralConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/romannum")
public class RomanNumeralConverterController {
    @Autowired
    private RomanNumeralConverter romanNumeralConverter;

    @PostMapping("/fromromannumeral")
    @ResponseBody
    public int convertRomanNumToInt(@RequestParam String romanNum) {
        return romanNumeralConverter.fromRomanNumeral(romanNum);
    }

    @PostMapping("/toromannumeral")
    @ResponseBody
    public String convertIntToRomanNum(@RequestParam int number) {
        return romanNumeralConverter.toRomanNumeral(number);
    }

}

