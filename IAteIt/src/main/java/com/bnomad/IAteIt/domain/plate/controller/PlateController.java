package com.bnomad.IAteIt.domain.plate.controller;

import com.bnomad.IAteIt.domain.plate.entity.dto.PlateAddDto;
import com.bnomad.IAteIt.domain.plate.entity.dto.PlateResponseDto;
import com.bnomad.IAteIt.domain.plate.service.PlateService;
import com.bnomad.IAteIt.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bnomad.IAteIt.global.result.ResultCode.*;

@RestController
@RequestMapping("/api/v1/plate")
@RequiredArgsConstructor
public class PlateController implements PlateControllerSwagger {

    private final PlateService plateService;

    /**
     * Plate 1개 추가
     */
    @PostMapping("")
    public ResponseEntity<ResultResponse> addPlate(@ModelAttribute PlateAddDto plateAddDto) {
        PlateResponseDto response = plateService.addPlate(plateAddDto);
        return ResponseEntity.ok(ResultResponse.of(PLATE_ADD_SUCCESS, response));
    }

    /**
     * Plate 삭제
     */
    @DeleteMapping("")
    public ResponseEntity<ResultResponse> deletePlate(@RequestParam("plateId") Long plateId) {
        plateService.deletePlate(plateId);
        return ResponseEntity.ok(ResultResponse.of(PLATE_DELETE_SUCCESS));
    }





}
