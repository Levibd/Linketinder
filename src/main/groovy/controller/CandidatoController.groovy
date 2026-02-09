package controller

import model.Candidato
import service.CandidatoService
import service.InputService

class CandidatoController {

    private CandidatoService service
    private InputService input

    CandidatoController(CandidatoService service, InputService input) {
        this.service = service
        this.input = input
    }

    void processarCadastro() {
        println "\n--- Novo Candidato ---"
        try {
            Candidato novo = new Candidato(
                    nome: input.lerTexto("Nome"),
                    sobrenome: input.lerTexto("Sobrenome"),
                    email: input.lerTexto("Email"),
                    cpf: input.lerTexto("CPF"),
                    dataNascimento: input.lerData("Data Nascimento"),
                    pais: input.lerTexto("País"),
                    cep: input.lerTexto("CEP"),
                    senha: input.lerTexto("Senha"),
                    descricao: "Via MVC",
                    skills: input.lerListaSeparadaPorVirgula("Skills")
            )
            service.cadastrarCandidato(novo)
        } catch (Exception e) {
            println "❌ Erro no cadastro: ${e.message}"
        }
    }

    void processarListagem() {
        println "\n--- 🧑‍💻 Lista de Candidatos ---"
        List<Candidato> lista = service.listarCandidatos()
        if (lista.isEmpty()) {
            println "Nenhum candidato encontrado."
        } else {
            lista.each { c ->
                println "ID: ${c.id} | ${c.nome} ${c.sobrenome} | ${c.email}"
            }
        }
    }
}