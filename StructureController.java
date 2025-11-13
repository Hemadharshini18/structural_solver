package com.example.structuralsolver.controller;

import com.example.structuralsolver.model.Load;
import com.example.structuralsolver.service.CalculationService;
import com.example.structuralsolver.service.CalculationService.ResultPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StructureController {

    @Autowired
    CalculationService calc;

    @GetMapping("/")
    public String index(){ return "index"; }

    @PostMapping("/analyze")
    public String analyze(@RequestParam double span,
                          @RequestParam(defaultValue="0") double pointLoad,
                          @RequestParam(defaultValue="0") double pointPos,
                          @RequestParam(defaultValue="0") double udlIntensity,
                          Model model){

        List<Load> loads=new ArrayList<>();
        if(pointLoad>0) loads.add(new Load("point",pointLoad,pointPos));
        if(udlIntensity>0) loads.add(new Load("udl",udlIntensity,0));

        List<ResultPoint> result=calc.analyzeSimplySupported(span,loads,20);

        model.addAttribute("results",result);
        model.addAttribute("span",span);

        return "results";
    }
}
