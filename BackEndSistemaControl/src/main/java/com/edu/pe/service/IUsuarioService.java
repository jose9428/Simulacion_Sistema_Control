package com.edu.pe.service;

import com.edu.pe.models.*;
import java.util.List;

public interface IUsuarioService {

    public Usuario Guardar(Usuario e);

    public void Eliminar(Integer id);

    public List<Usuario> Listado();

    public Usuario BuscarPorId(Integer id);
    
    public Usuario BuscarUsuario(String username , String pass);
}
