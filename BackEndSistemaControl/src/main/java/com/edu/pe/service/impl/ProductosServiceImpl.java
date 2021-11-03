package com.edu.pe.service.impl;

import com.edu.pe.models.Productos;
import com.edu.pe.repository.*;
import com.edu.pe.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductosServiceImpl implements IProductosService {

    @Autowired
    private ProductosRepository repo;

    @Override
    public Productos Guardar(Productos e) {
        return repo.save(e);
    }

    @Override
    public void Eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Productos> Listado() {
        return repo.findAll();
    }

    @Override
    public Productos BuscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

}
