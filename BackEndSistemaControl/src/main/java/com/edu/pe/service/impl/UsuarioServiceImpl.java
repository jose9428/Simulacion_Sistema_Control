package com.edu.pe.service.impl;

import com.edu.pe.models.Proveedor;
import com.edu.pe.models.Usuario;
import com.edu.pe.repository.*;
import com.edu.pe.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public Usuario Guardar(Usuario e) {
        return repo.save(e);
    }

    @Override
    public void Eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Usuario> Listado() {
        return repo.findAll();
    }

    @Override
    public Usuario BuscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

	@Override
	public Usuario BuscarUsuario(String username, String pass) {
		return repo.BuscarUsuario(username, pass);
	}
}
