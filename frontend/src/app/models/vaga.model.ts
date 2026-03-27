import { Empresa } from "./empresa.model";

export interface Vaga {
    id?: number;
    nome: string;
    descricao: string;
    local: string;
    empresa?: Empresa;
    skills: string[];
}