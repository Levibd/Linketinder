export interface ICandidate {
    id: number;
    name: string;
    email: string;
    cpf: string;
    age: number;
    state: string;
    description: string;
    skills: string[];
}

export interface ICompany {
    id: number;
    name: string;
    email: string;
    cnpj: string;
    country: string;
    state: string;
    description: string;
    vacancies: string[];
}