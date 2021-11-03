package com.edu.pe.service.impl;

import com.edu.pe.models.*;
import com.edu.pe.repository.*;
import com.edu.pe.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServiceImpl implements IAdministradorService {

    @Autowired
    private AdministradorRepository repo;

    @Override
    public Administrador Guardar(Administrador e) {
        return repo.save(e);
    }

    @Override
    public void Eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Administrador> Listado() {
        return repo.findAll();
    }

    @Override
    public Administrador BuscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }
}
