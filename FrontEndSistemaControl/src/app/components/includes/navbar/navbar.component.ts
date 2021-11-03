import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  username: string;
  constructor(private router: Router) { }

  ngOnInit(): void {
    const logeado = localStorage.getItem('usuario');
    if (logeado == null){
      this.router.navigate(['/login']);
    }else{
      const usuario = JSON.parse(localStorage.getItem('usuario'));
      this.username = usuario.username;
    }
  }

  CerrarSesion(){
    localStorage.removeItem('usuario');
    this.router.navigate(['/login']);
  }

}
