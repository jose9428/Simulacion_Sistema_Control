import { Proveedor } from './../../../models/Proveedor';
import { ProductoService } from './../../../services/producto.service';
import { Component, OnInit } from '@angular/core';
import { ProveedorService } from 'src/app/services/proveedor.service';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';
import * as $ from 'jquery';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent implements OnInit {
  miFormulario: FormGroup;
  selectedFile: any;
  proveedores: Proveedor[] = [];
  productos: any;
  titulo: string;
  idProveedor: number;

  constructor(private serviceProveedor: ProveedorService,
              private serviceProducto: ProductoService,
              private formBuilder: FormBuilder,
              private toastr: ToastrService) { }

  ngOnInit(): void {
    this.listProveedor();
    this.listar();
    this.CrearFormulario();
  }


  listProveedor() {
    this.serviceProveedor.getListar().subscribe(data => {
      this.proveedores = data;
    });
  }

  listar() {
    this.serviceProducto.getListar().subscribe(data => {
      this.productos = data;
    });
  }

  CrearFormulario() {
    this.miFormulario = this.formBuilder.group({
      id_prod: [0],
      proveedor: [],
      descripcion: ['', Validators.required],
      cantidad: ['', Validators.required],
      precio: ['', Validators.required],
      created_at: [''],
      updated_at: [''],
      imagen: ['']
    });
    this.titulo = 'Nuevo';
    this.idProveedor = 0;
  }


  Guardar() {
    const formData = new FormData();
    formData.append('id_prod', this.miFormulario.controls.id_prod.value);
    formData.append('proveedor', this.miFormulario.controls.proveedor.value);
    formData.append('descripcion', this.miFormulario.controls.descripcion.value);
    formData.append('cantidad', this.miFormulario.controls.cantidad.value);
    formData.append('precio', this.miFormulario.controls.precio.value);
    formData.append('file', this.selectedFile);

    this.serviceProducto.Guardar(formData).subscribe(data => {
      this.miFormulario.reset();
      this.miFormulario.get('proveedor').setValue(this.idProveedor);
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
    this.serviceProducto.BuscarPorId(id).subscribe(data => {

      this.miFormulario.setValue(data);
      this.idProveedor = data.proveedor.id_proveedor;
      this.titulo = 'Editar';
    });
  }

  Eliminar(id: number){
    // tslint:disable-next-line: deprecation
    this.serviceProducto.Eliminar(id).subscribe(data => {
      this.listar();
      this.toastr.success('Registro eliminado correcto!', 'Exito!');
    },
    error => {
      this.toastr.error('No se ha podido eliminar!', 'Error!');
    });
  }

  ArchivoSeleccionado(event){
    this.selectedFile = event.target.files[0];
  }

  MostrarImagen( base64String ){
    try{
      return `data:image/png;base64,${base64String}`;
    }catch (e){}
    return '';
  }

}
