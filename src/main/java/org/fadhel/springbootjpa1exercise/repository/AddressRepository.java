package org.fadhel.springbootjpa1exercise.repository;

import org.fadhel.springbootjpa1exercise.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findAddressById(Integer id);
}
