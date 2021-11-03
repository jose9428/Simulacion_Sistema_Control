package com.edu.pe.service.impl;

import com.edu.pe.models.Proveedor;
import com.edu.pe.repository.ProveedorRepository;
import com.edu.pe.service.IProveedorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private ProveedorRepository repo;

    @Override
    public Proveedor Guardar(Proveedor e) {
        return repo.save(e);
    }

    @Override
    public void Eliminar(Integer id) {
         repo.deleteById(id);
    }

    @Override
    public List<Proveedor> Listado() {
        return repo.findAll();
    }

    @Override
    public Proveedor BuscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

}
