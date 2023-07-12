package com.shopp.Shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopp.Shopping.model.Register;
import java.util.List;


@Repository
public interface RegisterRepository extends JpaRepository<Register, Long>{
	Register findByEmail(String email);
}
