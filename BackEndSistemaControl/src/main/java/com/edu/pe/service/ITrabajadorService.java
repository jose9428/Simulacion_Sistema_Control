package com.edu.pe.service;

import com.edu.pe.models.*;
import java.util.List;

public interface ITrabajadorService {
     public Trabajador Guardar(Trabajador e);

    public void Eliminar(Integer id);

    public List<Trabajador> Listado();

    public Trabajador BuscarPorId(Integer id);
}
