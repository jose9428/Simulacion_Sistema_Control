package com.edu.pe.repository;

import com.edu.pe.models.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends  JpaRepository<Administrador, Integer>{
    
}
