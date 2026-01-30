package service

import model.Candidato
import model.Empresa

class LinketinderService {

    List<Candidato> candidatesList = []
    List<Empresa> companies = []


    LinketinderService() {
        initData()
    }

    void initData() {

        candidatesList << new Candidato(name: "Levi Dantas", email: "levi@email.com", cpf: "123.456.789-00", age: 25, state: "SP", cep: "01000-000", description: "Dev Backend", skills: ["Java", "Groovy", "Spring"])
        candidatesList << new Candidato(name: "Ana Pereira", email: "ana@email.com", cpf: "222.222.222-22", age: 30, state: "RJ", cep: "02000-000", description: "Data Scientist", skills: ["Python", "Pandas", "SQL"])
        candidatesList << new Candidato(name: "Carlos Lima", email: "carlos@email.com", cpf: "333.333.333-33", age: 22, state: "MG", cep: "03000-000", description: "Frontend Dev", skills: ["Angular", "TypeScript"])
        candidatesList << new Candidato(name: "Beatriz Souza", email: "bia@email.com", cpf: "444.444.444-44", age: 28, state: "PR", cep: "04000-000", description: "Fullstack", skills: ["Java", "React", "Docker"])
        candidatesList << new Candidato(name: "João Silva", email: "joao@email.com", cpf: "555.555.555-55", age: 20, state: "BA", cep: "05000-000", description: "Estagiário", skills: ["Lógica", "C", "Java"])


        companies << new Empresa(name: "ZG Hero", email: "rh@zghero.com", cnpj: "00.000.000/0001-00", country: "Brasil", state: "SP", cep: "01000-000", description: "Educação Tech", skills: ["Groovy", "Java"])
        companies << new Empresa(name: "Tech Solutions", email: "contato@tech.com", cnpj: "11.111.111/0001-11", country: "EUA", state: "CA", cep: "90000", description: "Consultoria", skills: ["AWS", "Python"])
        companies << new Empresa(name: "Data Corp", email: "jobs@data.com", cnpj: "22.222.222/0001-22", country: "Brasil", state: "SC", cep: "88000-000", description: "Big Data", skills: ["Spark", "Hadoop"])
        companies << new Empresa(name: "Web House", email: "rh@webhouse.com", cnpj: "33.333.333/0001-33", country: "Brasil", state: "RJ", cep: "20000-000", description: "Agência Digital", skills: ["HTML", "CSS", "Design"])
        companies << new Empresa(name: "Soft Systems", email: "info@soft.com", cnpj: "44.444.444/0001-44", country: "Brasil", state: "RS", cep: "90000-000", description: "ERP", skills: ["Java", "SQL"])
    }

    List<Candidato> getCandidatos() {
        return candidatesList
    }

    List<Empresa> getEmpresas() {
        return companies
    }


    void adicionarCandidato(Candidato c) {
        candidatesList << c
    }
}