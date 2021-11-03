export class Proveedor {

  // tslint:disable-next-line: variable-name
  id_proveedor: number;
  nombre: string;
  ruc: string;
  direccion: string;
  celular: string;
  // tslint:disable-next-line: variable-name
  created_at: Date;
  // tslint:disable-next-line: variable-name
  updated_at: Date;

  // tslint:disable-next-line: variable-name
  constructor(id_proveedor, nombre, ruc, direccion, celular, created_at , updated_at) {
      this.id_proveedor = id_proveedor;
      this.nombre = nombre;
      this.ruc = ruc;
      this.direccion = direccion;
      this.celular = celular;
      this.created_at = created_at;
      this.updated_at = updated_at;

  }
}
