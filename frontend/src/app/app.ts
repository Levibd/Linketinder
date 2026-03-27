import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CadastroCandidato } from "./pages/cadastro-candidato/cadastro-candidato";
import { ListaCandidatos } from "./pages/lista-candidatos/lista-candidatos";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CadastroCandidato, ListaCandidatos],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('linketinder-project');
}
