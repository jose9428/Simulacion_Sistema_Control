import { TrabajadorService } from './../../../services/trabajador.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as $ from 'jquery';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-trabajador',
  templateUrl: './trabajador.component.html',
  styleUrls: ['./trabajador.component.css']
})
export class TrabajadorComponent implements OnInit {
  trabajadores: any;
  miFormulario: FormGroup;
  titulo: string;

  constructor(private service: TrabajadorService,
              private formBuilder: FormBuilder,
              private toastr: ToastrService
  ) { }
  //
  ngOnInit(): void {
    this.CrearFormulario();
    this.listar();
  }

  listar() {
    this.service.getListar().subscribe(data => {
      this.trabajadores = data;
    });
  }

  CrearFormulario() {
    this.miFormulario = this.formBuilder.group({
      id_trab: [0],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      dni: ['', Validators.required],
      direccion: ['', Validators.required],
      celular: ['', Validators.required],
      fecha_nacimiento: ['', Validators.required],
      created_at: [''],
      updated_at: [''],
      usuario: this.formBuilder.group({
        id_usuario: [0],
        username: ['', Validators.required],
        contrasenia: ['', Validators.required],
        created_at: [''],
        updated_at: [''],
      })
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

  Eliminar(id: number) {
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
