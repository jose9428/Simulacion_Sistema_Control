
package com.edu.pe.repository;

import com.edu.pe.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
	@Query("select u from Usuario u where u.username=?1 and u.contrasenia=?2")
	public Usuario BuscarUsuario(String username , String pass);
}
