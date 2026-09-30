package org.fadhel.springbootjpa1exercise.service;

import lombok.RequiredArgsConstructor;
import org.fadhel.springbootjpa1exercise.DTO.AddressDTO;
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

    public void addAddress(AddressDTO dto) {
        Teacher teacher = teacherRepository.findTeacherById(dto.getTeacher_id());
        if (teacher == null) {
            throw new ApiException("Cannot add address: Teacher not found with ID: " + dto.getTeacher_id());
        }
        if (teacher.getAddress() != null) {
            throw new ApiException("Address already exists for this teacher. Use update instead.");
        }

        Address address = new Address(null, dto.getArea(), dto.getStreet(), dto.getBuildingNumber(), teacher);
        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO dto) {
        Address address = addressRepository.findAddressById(dto.getTeacher_id());
        if (address == null) {
            throw new ApiException("Address not found for Teacher ID: " + dto.getTeacher_id());
        }

        address.setArea(dto.getArea());
        address.setStreet(dto.getStreet());
        address.setBuildingNumber(dto.getBuildingNumber());

        addressRepository.save(address);
    }

    public void deleteAddress(Integer teacherId) {
        Address address = addressRepository.findAddressById(teacherId);
        if (address == null) {
            throw new ApiException("Address not found for Teacher ID: " + teacherId);
        }

        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        if (teacher != null) {
            teacher.setAddress(null);
            teacherRepository.save(teacher);
        }

        addressRepository.delete(address);
    }
}
