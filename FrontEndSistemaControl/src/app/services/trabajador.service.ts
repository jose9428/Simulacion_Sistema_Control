import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrabajadorService {


  url = 'http://localhost:9090/api/trabajador/';

  constructor(private http: HttpClient) { }

  getListar(){
    return this.http.get(this.url + 'listar');
  }

  Guardar(form: any){
    const API_URL = `${this.url}guardar`;
    return this.http.post(API_URL , form);
  }

  BuscarPorId(id: number): Observable<any> {
    return this.http.get(this.url + 'buscar/' + id);
  }

  Eliminar(id: number){
    return this.http.delete(this.url + 'eliminar/' + id );
  }
}
