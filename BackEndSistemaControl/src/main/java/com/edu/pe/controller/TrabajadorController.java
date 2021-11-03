package com.edu.pe.controller;
import com.edu.pe.models.*;
import com.edu.pe.service.*;
import java.util.Calendar;
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
@RequestMapping("/api/trabajador")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrabajadorController {
    @Autowired
    private ITrabajadorService serviceTrab;

    @GetMapping("/listar")
    public ResponseEntity<List<Trabajador>> Listar() {
        List<Trabajador> lista = serviceTrab.Listado();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Trabajador> BuscarPorId(@PathVariable("id") int id) {
        Trabajador p = serviceTrab.BuscarPorId(id);

        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Trabajador> Guardar(@RequestBody Trabajador p) {
        Trabajador obj = null;
        try {
            if (p.getId_trab()== 0) {
                p.getUsuario().setCreated_at(Calendar.getInstance());
                p.setCreated_at(Calendar.getInstance());
                obj = serviceTrab.Guardar(p);
                return ResponseEntity.ok(obj);
            } else {
                obj = serviceTrab.BuscarPorId(p.getId_trab());

                if (obj != null) {
                    p.getUsuario().setUpdated_at(Calendar.getInstance());
                    p.setUpdated_at(Calendar.getInstance());
                    obj = serviceTrab.Guardar(p);
                    return ResponseEntity.ok(obj);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity Eliminar(@PathVariable("id") int id) {
        Trabajador e = serviceTrab.BuscarPorId(id);

        if (e != null) {
            serviceTrab.Eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
