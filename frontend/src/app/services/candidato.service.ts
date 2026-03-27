import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Candidato } from "../models/candidato.model"
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class CandidatoService {
  private http = inject(HttpClient)

  private apiUrl = 'http://localhost:8080/candidatos';

  cadastrar(candidato: Candidato): Observable<any> {
    return this.http.post(this.apiUrl, candidato)
  }

  listar(): Observable<Candidato[]>{
    return this.http.get<Candidato[]>(this.apiUrl)
  }
}
