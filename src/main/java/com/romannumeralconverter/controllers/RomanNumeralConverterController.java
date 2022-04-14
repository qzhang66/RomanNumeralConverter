package com.romannumeralconverter.controllers;

import com.romannumeralconverter.services.RomanNumeralConverter;
import com.romannumeralconverter.valueobject.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/romannum")
public class RomanNumeralConverterController {
    @Autowired
    private RomanNumeralConverter romanNumeralConverter;

    @PostMapping("/fromromannumeral")
    @ResponseBody
    public ResultVO<Integer> convertRomanNumToInt(@RequestParam String romanNum) {
        try {
            ResultVO<Integer> result = new ResultVO<>();
            result.setCode(HttpStatus.OK);
            result.setData(romanNumeralConverter.fromRomanNumeral(romanNum));
            return result;
        } catch (IllegalArgumentException e) {
            ResultVO<Integer> result = new ResultVO<>();
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMsg(e.getMessage());
            return result;
        }

    }

    @PostMapping("/toromannumeral")
    @ResponseBody
    public ResultVO<String> convertIntToRomanNum(@RequestParam String numberStr) {
        try {
            int number = Integer.parseInt(numberStr);
            ResultVO<String> result = new ResultVO<>();
            result.setCode(HttpStatus.OK);
            result.setData(romanNumeralConverter.toRomanNumeral(number));
            return result;
        } catch (NumberFormatException e) {
            ResultVO<String> result = new ResultVO<>();
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMsg("Error: input is not integer." + e.getMessage());
            return result;
        } catch (IllegalArgumentException e) {
            ResultVO<String> result = new ResultVO<>();
            result.setCode(HttpStatus.BAD_REQUEST);
            result.setMsg(e.getMessage());
            return result;
        }
    }
}

