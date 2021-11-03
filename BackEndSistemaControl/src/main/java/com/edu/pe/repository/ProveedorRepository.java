package com.edu.pe.repository;

import com.edu.pe.models.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends  JpaRepository<Proveedor, Integer>{
    
}
