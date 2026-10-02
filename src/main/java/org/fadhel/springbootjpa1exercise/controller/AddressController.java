package org.fadhel.springbootjpa1exercise.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.AddressRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.AddressResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiResponse;
import org.fadhel.springbootjpa1exercise.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponseDTO> addAddress(@Valid @RequestBody AddressRequestDTO dto) {
        return ResponseEntity.status(200).body(addressService.addAddress(dto));
    }

    @PutMapping
    public ResponseEntity<AddressResponseDTO> updateAddress(@Valid @RequestBody AddressRequestDTO dto) {
        return ResponseEntity.status(200).body(addressService.updateAddress(dto));
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer teacherId) {
        addressService.deleteAddress(teacherId);
        return ResponseEntity.status(200).body(new ApiResponse("Address deleted successfully"));
    }

}
