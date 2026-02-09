package controller

import model.Empresa
import service.EmpresaService
import service.InputService

class EmpresaController {

    private EmpresaService service
    private InputService input

    EmpresaController(EmpresaService service, InputService input) {
        this.service = service
        this.input = input
    }


    void processarListagem() {
        println "\n--- 🏢 Lista de Empresas Cadastradas ---"
        try {
            List<Empresa> lista = service.listarEmpresas()

            if (lista.isEmpty()) {
                println "⚠️ Nenhuma empresa encontrada no banco de dados."
            } else {
                println String.format("%-5s | %-20s | %-18s | %-15s", "ID", "NOME", "CNPJ", "PAÍS")
                println "-" * 65
                lista.each { empresa ->
                    println String.format("%-5d | %-20s | %-18s | %-15s",
                            empresa.id,
                            empresa.nome,
                            empresa.cnpj,
                            empresa.pais)
                }
            }
        } catch (Exception e) {
            println "❌ Erro ao listar empresas: ${e.message}"
        }
    }


    void processarCadastro() {
        println "\n--- 🏢 Cadastro de Nova Empresa ---"
        println "Por favor, forneça os dados abaixo:"

        try {
            String nome = input.lerTexto("Nome da Empresa")
            String cnpj = input.lerTexto("CNPJ (apenas números)")
            String email = input.lerTexto("Email Corporativo")
            String pais = input.lerTexto("País Sede")
            String cep = input.lerTexto("CEP")
            String descricao = input.lerTexto("Descrição da Empresa")
            String senha = input.lerTexto("Senha de Acesso")


            Empresa novaEmpresa = new Empresa(
                    nome: nome,
                    cnpj: cnpj,
                    email: email,
                    pais: pais,
                    cep: cep,
                    descricao: descricao,
                    senha: senha
            )


            service.cadastrarEmpresa(novaEmpresa)
            println "✅ Sucesso! A empresa '${nome}' foi registrada no sistema."

        } catch (Exception e) {
            println "❌ Falha ao cadastrar empresa: ${e.message}"
            println "   (Dica: Verifique se o CNPJ já não existe no sistema)"
        }
    }
}