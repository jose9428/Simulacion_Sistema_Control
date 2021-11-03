import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  url = 'http://localhost:9090/api/usuario/';

  constructor(private http: HttpClient) { }

  IniciarSesion(data: any): Observable<any> {
    const API_URL = `${this.url}login`;
    return this.http.post(API_URL , data);
  }

}
