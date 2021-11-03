package com.edu.pe.service.impl;

import com.edu.pe.models.*;
import com.edu.pe.repository.*;
import com.edu.pe.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

    @Autowired
    private TrabajadorRepository repo;

    @Override
    public Trabajador Guardar(Trabajador e) {
        return repo.save(e);
    }

    @Override
    public void Eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Trabajador> Listado() {
        return repo.findAll();
    }

    @Override
    public Trabajador BuscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }
}
