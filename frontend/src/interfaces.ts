export interface ICandidate {
    id: number;
    name: string;
    email: string;
    cpf: string;
    age: number;
    phone: string;
    linkedin: string;
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
    cep: string;
    state: string;
    description: string;
    vacancies: string[];
}