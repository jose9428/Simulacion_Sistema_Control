import { LiteralMapEntry } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/services/login.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  miFormulario: FormGroup;
  constructor( private formBuilder: FormBuilder,
               private service: LoginService,
               private toastr: ToastrService,
               private router: Router) { }

  ngOnInit(): void {
    this.CrearFormulario();
  }

  CrearFormulario(){
    this.miFormulario = this.formBuilder.group({
      username: [ '' ,  Validators.required ],
      contrasenia: [ '' ,  Validators.required ] ,
    });
  }

  Login(){
    this.service.IniciarSesion(this.miFormulario.value).subscribe(data => {
      localStorage.setItem('usuario', JSON.stringify(data));
      this.router.navigate(['/producto']);
    },
    error => {
      this.toastr.error('Usuario y/o clave incorrecto', 'Error!');
    });
  }

}
