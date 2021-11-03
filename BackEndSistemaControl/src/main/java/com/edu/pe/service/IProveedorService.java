package com.edu.pe.service;

import com.edu.pe.models.Proveedor;
import java.util.List;

public interface IProveedorService {

    public Proveedor Guardar(Proveedor e);

    public void Eliminar(Integer id);

    public List<Proveedor> Listado();

    public Proveedor BuscarPorId(Integer id);
}
