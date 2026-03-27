import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CandidatoService } from '../../services/candidato.service';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-cadastro-candidato',
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './cadastro-candidato.html',
  styleUrl: './cadastro-candidato.css',
})
export class CadastroCandidato {

  private fb = inject(FormBuilder)
  private candidatoService = inject(CandidatoService)

  formCandidato: FormGroup = this.fb.group({
    nome: ['', Validators.required],
    sobrenome: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    cpf: ['', Validators.required],
    dataNascimento: ['', Validators.required], 
    pais: ['Brasil', Validators.required],
    cep: ['', Validators.required],
    senha: ['', Validators.required],
    descricao: ['']
  });

  salvar(){
    if (this.formCandidato.valid){
      const novoCandidato = this.formCandidato.value;
      novoCandidato.skills = [];

      this.candidatoService.cadastrar(novoCandidato).subscribe({
        next: (resposta) => {
          alert('Candidato cadastrado com sucesso');
          this.formCandidato.reset();
        },
        error: (erro) => {
          console.error('Erro na requisição:', erro);
          alert('Erro ao cadastrar')
        }
      });
    } else {
        alert('Preencha os campos corretamente');
      }
  }

}
