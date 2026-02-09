package controller

import model.Competencia
import service.CompetenciaService
import service.InputService

class CompetenciaController {

    private CompetenciaService service
    private InputService input


    CompetenciaController(CompetenciaService service, InputService input) {
        this.service = service
        this.input = input
    }


    void iniciarGerenciamento() {
        boolean gerenciando = true
        while (gerenciando) {
            println "\n--- 🧠 Gestão de Competências ---"
            println "a. Listar Competências"
            println "b. Adicionar Nova"
            println "c. Atualizar (Renomear)"
            println "d. Deletar"
            println "0. Voltar ao Menu Principal"

            String opcao = input.lerTexto("Escolha uma opção")

            try {
                switch (opcao.toLowerCase()) {
                    case "a":
                        listarCompetencias()
                        break
                    case "b":
                        adicionarCompetencia()
                        break
                    case "c":
                        atualizarCompetencia()
                        break
                    case "d":
                        deletarCompetencia()
                        break
                    case "0":
                        gerenciando = false
                        break
                    default:
                        println "⚠️ Opção inválida."
                }
            } catch (Exception e) {
                println "❌ Erro na operação: ${e.message}"
            }
        }
    }

    private void listarCompetencias() {
        println "\n📋 Lista de Competências Cadastradas:"
        List<Competencia> lista = service.listarCompetencias()

        if (lista.isEmpty()) {
            println "   (Nenhuma competência cadastrada)"
        } else {
            lista.each { c ->
                println "   [ID: ${c.id}] ${c.nome}"
            }
        }
    }

    private void adicionarCompetencia() {
        String nome = input.lerTexto("Nome da nova competência")
        service.adicionar(nome)
        println "✅ Competência '${nome}' enviada para cadastro."
    }

    private void atualizarCompetencia() {
        listarCompetencias()
        int id = input.lerInteiro("Digite o ID da competência para alterar")
        String novoNome = input.lerTexto("Novo nome")

        service.atualizar(id, novoNome)
        println "✅ Solicitação de atualização enviada."
    }

    private void deletarCompetencia() {
        listarCompetencias()
        int id = input.lerInteiro("Digite o ID da competência para excluir")


        String confirmacao = input.lerTexto("Tem certeza? (S/N)")
        if (confirmacao.equalsIgnoreCase("S")) {
            service.deletar(id)
            println "✅ Solicitação de exclusão enviada."
        } else {
            println "🚫 Operação cancelada."
        }
    }
}