import { Pessoa } from './pessoa.model';

export interface Empresa extends Pessoa {
  cnpj: string;
}