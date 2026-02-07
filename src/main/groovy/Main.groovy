import dao.CandidatoDAO
import dao.CompetenciaDAO
import dao.EmpresaDAO
import dao.VagaDAO
import model.Candidato
import model.Competencia
import model.Empresa
import model.Vaga

import java.time.LocalDate

class Main {
    static void main(String[] args) {

        Scanner scanner = new Scanner(System.in)
        CandidatoDAO candidatoDAO = new CandidatoDAO()
        EmpresaDAO empresaDAO = new EmpresaDAO()
        VagaDAO vagaDAO = new VagaDAO()
        CompetenciaDAO compDAO = new CompetenciaDAO()
        boolean running = true

        println "🚀 Bem-vindo ao Linketinder (Versão PostgreSQL)"

        while (running) {
            println "\n1. Listar Candidatos"
            println "2. Listar Empresas"
            println "3. Cadastrar Candidato"
            println "4. Cadastrar Empresa"
            println "5. Listar Vagas"
            println "6. Curtir Vaga (em construção)"
            println "7. 🧠 Gerenciar Competências (CRUD)"
            println "0. Sair"
            print "> "

            def opcao = scanner.nextLine()

            switch (opcao) {
                case "1":
                    println "\n--- 🧑‍💻 Candidatos ---"
                    List<Candidato> lista = candidatoDAO.listar()
                    if (lista.isEmpty()) println "Nenhum candidato encontrado."

                    lista.each { c ->
                        println "ID: ${c.id} | Nome: ${c.nome} ${c.sobrenome} | Email: ${c.email}"
                    }
                    break
                case "2":
                    println "\n--- 🏢 Empresas (Do Banco de Dados) ---"
                    List<Empresa> listaEmp = empresaDAO.listar()
                    if (listaEmp.isEmpty()) println "Nenhuma empresa encontrada."

                    listaEmp.each { e ->
                        println "ID: ${e.id} | Nome: ${e.nome} | CNPJ: ${e.cnpj}"
                    }
                    break
                case "3":
                    println "\n--- Novo Cadastro de Candidato ---"
                    print "Nome: "
                    def nome = scanner.nextLine()
                    print "Sobrenome: "
                    def sobrenome = scanner.nextLine()
                    print "Email: "
                    def email = scanner.nextLine()
                    print "CPF: "
                    def cpf = scanner.nextLine()
                    print "Data Nascimento (AAAA-MM-DD): "
                    def dataStr = scanner.nextLine()
                    print "País: "
                    def pais = scanner.nextLine()
                    print "CEP: "
                    def cep = scanner.nextLine()
                    print "Senha: "
                    def senha = scanner.nextLine()
                    print "Skills (separe por vírgula): "
                    def skills = scanner.nextLine().split(",").collect { it.trim() }

                    def novo = new Candidato(
                            nome: nome,
                            sobrenome: sobrenome,
                            email: email,
                            cpf: cpf,
                            dataNascimento: LocalDate.parse(dataStr),
                            pais: pais,
                            cep: cep,
                            descricao: "Cadastrado via Console",
                            senha: senha,
                            skills: skills
                    )

                    candidatoDAO.save(novo)
                    break


                case "4":
                    println "\n--- Novo Cadastro de Empresa ---"
                    print "Nome: "
                    def nomeEmp = scanner.nextLine()
                    print "CNPJ: "
                    def cnpj = scanner.nextLine()
                    print "Email: "
                    def emailEmp = scanner.nextLine()
                    print "País: "
                    def paisEmp = scanner.nextLine()
                    print "CEP: "
                    def cepEmp = scanner.nextLine()
                    print "Senha: "
                    def senhaEmp = scanner.nextLine()
                    print "Descrição: "
                    def descEmp = scanner.nextLine()

                    def novaEmp = new Empresa(
                            nome: nomeEmp,
                            email: emailEmp,
                            cnpj: cnpj,
                            pais: paisEmp,
                            cep: cepEmp,
                            descricao: descEmp,
                            senha: senhaEmp
                    )

                    empresaDAO.save(novaEmp)
                    break

                case "5":
                    println "\n--- 💼 VAGAS DISPONÍVEIS ---"
                    List<Vaga> vagas = vagaDAO.listar()
                    if (vagas.isEmpty()) println "Nenhuma vaga cadastrada."

                    vagas.each { v ->
                        println "Vaga #${v.id}: ${v.nome} [${v.empresa.nome}] - Local: ${v.local}"
                    }
                    break

                case "6":
                    println "\n--- Cadastrar Vaga ---"

                    print "Digite o ID da Empresa dona da vaga: "
                    int idEmpresa = scanner.nextLine().toInteger()

                    print "Nome da Vaga: "
                    String nomeVaga = scanner.nextLine()
                    print "Descrição: "
                    String descVaga = scanner.nextLine()
                    print "Local: "
                    String localVaga = scanner.nextLine()


                    Empresa dona = new Empresa()
                    dona.id = idEmpresa

                    Vaga novaVaga = new Vaga(
                            nome: nomeVaga,
                            descricao: descVaga,
                            local: localVaga,
                            empresa: dona
                    )

                    vagaDAO.salvar(novaVaga)
                    break

                case "7":
                    println "\n--- GERENCIAMENTO DE COMPETÊNCIAS ---"
                    println "a. Listar"
                    println "b. Adicionar"
                    println "c. Atualizar (Renomear)"
                    println "d. Deletar"
                    print "> "
                    def subOpcao = scanner.nextLine()

                    switch (subOpcao) {
                        case "a":
                            List<Competencia> skills = compDAO.listar()
                            println "\n📋 Lista de Competências:"
                            skills.each { s -> println "ID: ${s.id} | Nome: ${s.nome}" }
                            break

                        case "b":
                            print "Nome da nova competência: "
                            String nome = scanner.nextLine()
                            compDAO.adicionar(nome)
                            break

                        case "c":
                            print "ID da competência para alterar: "
                            int id = scanner.nextLine().toInteger()
                            print "Novo nome: "
                            String novoNome = scanner.nextLine()
                            compDAO.atualizar(id, novoNome)
                            break

                        case "d":
                            print "ID da competência para deletar: "
                            int idDel = scanner.nextLine().toInteger()
                            compDAO.deletar(idDel)
                            break
                        default:
                            println "Opção inválida."
                    }
                    break

                case "0":
                    running = false
                    break
                default:
                    println "Opção inválida"
            }
        }
    }
}