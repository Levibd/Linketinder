import { Pessoa } from "./pessoa.model";

export interface Candidato extends Pessoa {
  sobrenome: string;
  cpf: string;
  dataNascimento: string; 
  skills: string[];
}