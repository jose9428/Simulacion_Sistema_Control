package com.edu.pe.service;

import com.edu.pe.models.*;
import java.util.List;

public interface IAdministradorService {
     public Administrador Guardar(Administrador e);

    public void Eliminar(Integer id);

    public List<Administrador> Listado();

    public Administrador BuscarPorId(Integer id);
}
