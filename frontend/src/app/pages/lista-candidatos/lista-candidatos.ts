import { Component, inject, OnInit } from '@angular/core';
import { CandidatoService } from '../../services/candidato.service';
import { Candidato } from '../../models/candidato.model';
import { JsonPipe } from '@angular/common';


@Component({
  selector: 'app-lista-candidatos',
  imports: [],
  templateUrl: './lista-candidatos.html',
  styleUrl: './lista-candidatos.css',
})
export class ListaCandidatos implements OnInit {

  private candidatoService = inject(CandidatoService)

  candidatos: Candidato[] = [];

  ngOnInit() {
      this.candidatoService.listar().subscribe({
        next: (dados) => {
          this.candidatos = dados;
        },
        error: (erro) => {
          console.error('Erro ao buscar candidatos', erro);
        }
      });
  }
}
