package com.bnomad.IAteIt.domain.plate.controller;

import com.bnomad.IAteIt.domain.plate.service.PlateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/plate")
@RequiredArgsConstructor
public class PlateController {

    private final PlateService plateService;


    /**
     * Plate 1개 삭제
     */
    @DeleteMapping("")
    public void deletePlate(@RequestParam("mealId") Long mealId,
                            @RequestParam("plateId") Long plateId) {

    }





}
