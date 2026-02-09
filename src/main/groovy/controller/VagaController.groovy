package controller

import model.Vaga
import model.Empresa
import service.VagaService
import service.EmpresaService
import service.InputService

class VagaController {

    private VagaService vagaService
    private EmpresaService empresaService
    private InputService input

    VagaController(VagaService vagaService, EmpresaService empresaService, InputService input) {
        this.vagaService = vagaService
        this.empresaService = empresaService
        this.input = input
    }


    void processarListagem() {
        println "\n--- 💼 Mural de Vagas ---"
        try {
            List<Vaga> vagas = vagaService.listarVagas()

            if (vagas.isEmpty()) {
                println "⚠️ Nenhuma vaga disponível no momento."
            } else {
                vagas.each { vaga ->
                    println "------------------------------------------------"
                    println "ID Vaga: ${vaga.id}"
                    println "Cargo:   ${vaga.nome}"
                    println "Empresa: ${vaga.empresa?.nome ?: 'Desconhecida'} (ID: ${vaga.empresa?.id})"
                    println "Local:   ${vaga.local}"
                    println "Desc.:   ${vaga.descricao}"
                }
                println "------------------------------------------------"
            }
        } catch (Exception e) {
            println "❌ Erro ao listar vagas: ${e.message}"
        }
    }


    void processarCadastro() {
        println "\n--- 💼 Publicar Nova Vaga ---"

        try {

            println "Para cadastrar uma vaga, informe o ID da empresa responsável:"
            List<Empresa> empresas = empresaService.listarEmpresas()

            if (empresas.isEmpty()) {
                println "⛔ Ação Bloqueada: Não existem empresas cadastradas."
                println "   Cadastre uma empresa antes de criar vagas."
                return
            }


            println String.format("%-5s | %-20s", "ID", "EMPRESA")
            empresas.each { println String.format("%-5d | %-20s", it.id, it.nome) }
            println "-" * 30


            int idEmpresa = input.lerInteiro("Digite o ID da Empresa")
            String nomeVaga = input.lerTexto("Título da Vaga (Cargo)")
            String descricao = input.lerTexto("Descrição das Atividades")
            String local = input.lerTexto("Local de Trabalho (Cidade/Remoto)")


            Empresa empresaVinculada = new Empresa()
            empresaVinculada.id = idEmpresa

            Vaga novaVaga = new Vaga(
                    nome: nomeVaga,
                    descricao: descricao,
                    local: local,
                    empresa: empresaVinculada
            )


            vagaService.cadastrarVaga(novaVaga)
            println "✅ Vaga '${nomeVaga}' publicada com sucesso!"

        } catch (Exception e) {
            println "❌ Falha ao publicar vaga: ${e.message}"
            println "   (Verifique se o ID da empresa informado é válido)"
        }
    }
}