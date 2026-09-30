package org.fadhel.springbootjpa1exercise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.AddressDTO;
import org.fadhel.springbootjpa1exercise.api.ApiResponse;
import org.fadhel.springbootjpa1exercise.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.addAddress(addressDTO);
        return ResponseEntity.status(201).body(new ApiResponse("Teacher address added successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateAddress(@Valid @RequestBody AddressDTO addressDTO) {
        addressService.updateAddress(addressDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher address updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher address deleted successfully"));
    }
}
