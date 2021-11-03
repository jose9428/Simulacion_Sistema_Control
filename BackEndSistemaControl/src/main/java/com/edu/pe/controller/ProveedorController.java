package com.edu.pe.controller;

import com.edu.pe.models.Proveedor;
import com.edu.pe.service.IProveedorService;
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
@RequestMapping("/api/proveedor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProveedorController {

    @Autowired
    private IProveedorService serviceProv;

    @GetMapping("/listar")
    public ResponseEntity<List<Proveedor>> Listar() {
        List<Proveedor> lista = serviceProv.Listado();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Proveedor> BuscarPorId(@PathVariable("id") int id) {
        Proveedor p = serviceProv.BuscarPorId(id);

        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Proveedor> Guardar(@RequestBody Proveedor p) {
        Proveedor obj = null;
        try {
            if (p.getId_proveedor() == 0) {
                p.setCreated_at(Calendar.getInstance());
                obj = serviceProv.Guardar(p);
                return ResponseEntity.ok(obj);
            } else {
                obj = serviceProv.BuscarPorId(p.getId_proveedor());

                if (obj != null) {
                    p.setUpdated_at(Calendar.getInstance());
                    obj = serviceProv.Guardar(p);
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
        Proveedor e = serviceProv.BuscarPorId(id);

        if (e != null) {
            serviceProv.Eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
}
