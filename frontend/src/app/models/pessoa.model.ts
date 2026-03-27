export interface Pessoa {
    id? : number;
    nome: string;
    email: string;
    pais: string;
    cep: string;
    descricao?: string;
    senha?: string;
}