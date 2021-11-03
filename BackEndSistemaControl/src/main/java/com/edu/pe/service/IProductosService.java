package com.edu.pe.service;

import com.edu.pe.models.*;
import java.util.List;

public interface IProductosService {

    public Productos Guardar(Productos e);

    public void Eliminar(Integer id);

    public List<Productos> Listado();

    public Productos BuscarPorId(Integer id);
}
