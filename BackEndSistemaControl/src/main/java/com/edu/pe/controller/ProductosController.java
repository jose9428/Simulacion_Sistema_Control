package com.edu.pe.controller;

import com.edu.pe.models.*;
import com.edu.pe.service.*;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductosController {

    @Autowired
    private IProductosService serviceProd;

    @GetMapping("/listar")
    public ResponseEntity<List<Productos>> Listar() {
        List<Productos> lista = serviceProd.Listado();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Productos> BuscarPorId(@PathVariable("id") int id) {
        Productos p = serviceProd.BuscarPorId(id);

        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Productos> Guardar(@RequestParam(name = "file", required = false) MultipartFile file,
            Productos p) {
        Productos obj = null;

        try {
            if (p.getId_prod() != 0) {
                obj = serviceProd.BuscarPorId(p.getId_prod());
                p.setCreated_at(obj.getCreated_at());
                p.setUpdated_at(Calendar.getInstance());
            } else {
                p.setCreated_at(Calendar.getInstance());
            }

            if (file != null) {
                p.setImagen(ConvertirImagen(file));
            } else {
                if (obj != null) {
                    p.setImagen(obj.getImagen());
                }
            }

            serviceProd.Guardar(p);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity Eliminar(@PathVariable("id") int id) {
        Productos e = serviceProd.BuscarPorId(id);

        if (e != null) {
            serviceProd.Eliminar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    public static byte[] ConvertirImagen(MultipartFile imagen) {
        byte[] byteObjects = null;

        if (!imagen.isEmpty()) {
            try {
                byteObjects = new byte[imagen.getBytes().length];

                int i = 0;

                for (byte b : imagen.getBytes()) {
                    byteObjects[i++] = b;
                }

            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }

        return byteObjects;
    }
}
