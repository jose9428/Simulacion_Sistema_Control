import { AdministradorComponent } from './components/pages/administrador/administrador.component';
import { TrabajadorComponent } from './components/pages/trabajador/trabajador.component';
import { ProveedorComponent } from './components/pages/proveedor/proveedor.component';
import { ProductoComponent } from './components/pages/producto/producto.component';
import { LoginComponent } from './components/pages/login/login.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'producto', component: ProductoComponent},
  { path: 'proveedor', component: ProveedorComponent},
  { path: 'trabajador', component: TrabajadorComponent},
  { path: 'admin', component: AdministradorComponent},
  { path: '**', pathMatch: 'full' , redirectTo: 'login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
