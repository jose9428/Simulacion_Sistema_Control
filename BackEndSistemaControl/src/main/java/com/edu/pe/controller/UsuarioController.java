package com.edu.pe.controller;

import com.edu.pe.models.*;
import com.edu.pe.service.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private IUsuarioService serviceUser;
    
    @PostMapping("/login")
    public ResponseEntity<Usuario> IniciarSesion(@RequestBody Usuario obj) {
    	Usuario u = serviceUser.BuscarUsuario(obj.getUsername(), obj.getContrasenia());
    	
    	 if (u != null) {
             return ResponseEntity.ok(u);
         } else {
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> Listar() {
        List<Usuario> lista = serviceUser.Listado();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Usuario> BuscarPorId(@PathVariable("id") int id) {
        Usuario p = serviceUser.BuscarPorId(id);

        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity Eliminar(@PathVariable("id") int id) {
        Usuario e = serviceUser.BuscarPorId(id);

        if (e != null) {
            serviceUser.Eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
