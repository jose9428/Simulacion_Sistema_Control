package com.edu.pe.repository;

import com.edu.pe.models.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends  JpaRepository<Trabajador, Integer>{
    
}
