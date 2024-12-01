package com.project.tgecoil.repository;

import com.project.tgecoil.model.dto.Address;
import com.project.tgecoil.model.dto.CollectionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}