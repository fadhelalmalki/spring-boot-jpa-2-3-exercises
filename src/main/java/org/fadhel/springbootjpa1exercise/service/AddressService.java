package org.fadhel.springbootjpa1exercise.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.AddressRequestDTO;
import org.fadhel.springbootjpa1exercise.DTO.AddressResponseDTO;
import org.fadhel.springbootjpa1exercise.api.ApiException;
import org.fadhel.springbootjpa1exercise.model.Address;
import org.fadhel.springbootjpa1exercise.model.Teacher;
import org.fadhel.springbootjpa1exercise.repository.AddressRepository;
import org.fadhel.springbootjpa1exercise.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public AddressResponseDTO addAddress(AddressRequestDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ApiException("Teacher not found with ID: " + dto.getTeacherId()));

        Address address = new Address();
        address.setArea(dto.getArea());
        address.setStreet(dto.getStreet());
        address.setBuildingNumber(dto.getBuildingNumber());
        address.setTeacher(teacher);

        Address saved = addressRepository.save(address);
        return mapToAddressResponse(saved);
    }

    public AddressResponseDTO updateAddress(AddressRequestDTO dto) {
        Address address = addressRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new ApiException("Address not found for Teacher ID: " + dto.getTeacherId()));

        address.setArea(dto.getArea());
        address.setStreet(dto.getStreet());
        address.setBuildingNumber(dto.getBuildingNumber());

        Address updated = addressRepository.save(address);
        return mapToAddressResponse(updated);
    }

    public void deleteAddress(Integer teacherId) {
        Address address = addressRepository.findById(teacherId)
                .orElseThrow(() -> new ApiException("Address not found for Teacher ID: " + teacherId));

        Teacher teacher = address.getTeacher();
        if (teacher != null) {
            teacher.setAddress(null);
        }
        addressRepository.delete(address);
    }

    private AddressResponseDTO mapToAddressResponse(Address address) {
        return new AddressResponseDTO(
                address.getId(),
                address.getArea(),
                address.getStreet(),
                address.getBuildingNumber()
        );
    }
}
