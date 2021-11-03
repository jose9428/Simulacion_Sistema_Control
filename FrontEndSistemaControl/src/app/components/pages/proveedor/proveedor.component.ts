import { Component, OnInit } from '@angular/core';
import { Proveedor } from 'src/app/models/Proveedor';
import { ProveedorService } from 'src/app/services/proveedor.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as $ from 'jquery';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-proveedor',
  templateUrl: './proveedor.component.html',
  styleUrls: ['./proveedor.component.css']
})
export class ProveedorComponent implements OnInit {
  proveedores: Proveedor[] = [];
  miFormulario: FormGroup;
  titulo: string;

  constructor(private service: ProveedorService,
              private formBuilder: FormBuilder,
              private toastr: ToastrService
              ) { }
//
  ngOnInit(): void {
    this.listar();
    this.CrearFormulario();
  }

  listar() {
    this.service.getListar().subscribe(data => {
      this.proveedores = data;
    });
  }

  CrearFormulario() {
    this.miFormulario = this.formBuilder.group({
      id_proveedor: [0],
      nombre: ['', Validators.required],
      ruc: ['', Validators.required],
      direccion: ['', Validators.required],
      celular: ['', Validators.required],
      created_at: [''],
      updated_at: ['']
    });
    this.titulo = 'Nuevo';
  }

  Guardar() {
    this.service.Guardar(this.miFormulario.value).subscribe(data => {
      this.miFormulario.reset();
      this.listar();
      this.CerrarModal();
      this.toastr.success('Datos guardados correctamente!', 'Exito!');
    },
    error => {
      this.toastr.error('No se ha podido guardar datos!', 'Error!');
    });
  }

  CerrarModal() {
    $('#modalForm').hide();
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
  }
  CargarDatos(id: number) {
    this.service.BuscarPorId(id).subscribe(data => {
      this.miFormulario.setValue(data);
      this.titulo = 'Editar';
    });
  }

  Eliminar(id: number){
    // tslint:disable-next-line: deprecation
    this.service.Eliminar(id).subscribe(data => {
      this.listar();
      this.toastr.success('Registro eliminado correcto!', 'Exito!');
    },
    error => {
      this.toastr.error('No se ha podido eliminar!', 'Error!');
    });
  }
}
