import dao.*
import model.*
import service.InputService
import java.sql.Connection

/*
 * Autor: Levi
 * Projeto: Linketinder (Refatorado: SOLID + Clean Code)
 */
class Main {

    // Dependências (Injetadas)
    static Connection conexao
    static InputService input
    static CandidatoDAO candidatoDAO
    static EmpresaDAO empresaDAO
    static VagaDAO vagaDAO
    static CompetenciaDAO competenciaDAO

    static void main(String[] args) {
        // 1. Configuração Inicial (Bootstrap)
        inicializarDependencias()

        println "🚀 Bem-vindo ao Linketinder (Versão PostgreSQL + SOLID)"
        boolean executando = true

        // 2. Loop Principal
        while (executando) {
            exibirMenu()

            String opcao = input.lerTexto("Escolha uma opção")

            try {
                switch (opcao) {
                    case "1": listarCandidatos(); break
                    case "2": listarEmpresas(); break
                    case "3": cadastrarCandidato(); break
                    case "4": cadastrarEmpresa(); break
                    case "5": listarVagas(); break
                    case "6": cadastrarVaga(); break
                    case "7": gerenciarCompetencias(); break
                    case "0":
                        println "👋 Encerrando sistema..."
                        encerrarSistema()
                        executando = false
                        break
                    default:
                        println "⚠️ Opção inválida."
                }
            } catch (Exception e) {
                println "🔴 Erro inesperado no sistema: ${e.message}"
                e.printStackTrace()
            }
        }
    }

    // --- CONFIGURAÇÃO ---

    static void inicializarDependencias() {
        try {
            conexao = DatabaseConnection.getInstancia()
            input = new InputService()

            candidatoDAO = new CandidatoDAO(conexao)
            empresaDAO = new EmpresaDAO(conexao)
            vagaDAO = new VagaDAO(conexao)
            competenciaDAO = new CompetenciaDAO(conexao)

        } catch (Exception e) {
            println "❌ Falha crítica ao iniciar o sistema: ${e.message}"
            System.exit(1)
        }
    }

    static void encerrarSistema() {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close()
            println "🔌 Conexão encerrada."
        }
    }

    static void exibirMenu() {
        println "\n============================="
        println "1. Listar Candidatos"
        println "2. Listar Empresas"
        println "3. Cadastrar Candidato"
        println "4. Cadastrar Empresa"
        println "5. Listar Vagas"
        println "6. Cadastrar Vaga"
        println "7. 🧠 Gerenciar Competências"
        println "0. Sair"
        println "============================="
    }

    // --- MÉTODOS DE CANDIDATO ---

    static void listarCandidatos() {
        println "\n--- 🧑‍💻 Candidatos ---"
        List<Candidato> lista = candidatoDAO.listar()
        if (lista.isEmpty()) println "Nenhum candidato encontrado."

        lista.each { c ->
            println "ID: ${c.id} | Nome: ${c.nome} ${c.sobrenome} | Email: ${c.email}"
        }
    }

    static void cadastrarCandidato() {
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
                    descricao: "Cadastrado via Console",
                    skills: input.lerListaSeparadaPorVirgula("Skills")
            )
            candidatoDAO.salvar(novo)
        } catch (Exception e) {
            println "❌ Erro ao cadastrar: ${e.message}"
        }
    }

    // --- MÉTODOS DE EMPRESA ---

    static void listarEmpresas() {
        println "\n--- 🏢 Empresas ---"
        List<Empresa> lista = empresaDAO.listar()
        if (lista.isEmpty()) println "Nenhuma empresa encontrada."

        lista.each { e ->
            println "ID: ${e.id} | Nome: ${e.nome} | CNPJ: ${e.cnpj}"
        }
    }

    static void cadastrarEmpresa() {
        println "\n--- Nova Empresa ---"
        try {
            Empresa nova = new Empresa(
                    nome: input.lerTexto("Nome"),
                    cnpj: input.lerTexto("CNPJ"),
                    email: input.lerTexto("Email"),
                    pais: input.lerTexto("País"),
                    cep: input.lerTexto("CEP"),
                    senha: input.lerTexto("Senha"),
                    descricao: input.lerTexto("Descrição")
            )
            empresaDAO.salvar(nova)
        } catch (Exception e) {
            println "❌ Erro ao cadastrar empresa: ${e.message}"
        }
    }

    // --- MÉTODOS DE VAGA ---

    static void listarVagas() {
        println "\n--- 💼 Vagas Disponíveis ---"
        List<Vaga> vagas = vagaDAO.listar()
        if (vagas.isEmpty()) println "Nenhuma vaga cadastrada."

        vagas.each { v ->
            println "Vaga #${v.id}: ${v.nome} [${v.local}]"
        }
    }

    static void cadastrarVaga() {
        println "\n--- Nova Vaga ---"
        listarEmpresas()

        try {
            int idEmpresa = input.lerInteiro("ID da Empresa")

            Empresa dona = new Empresa()
            dona.id = idEmpresa

            Vaga nova = new Vaga(
                    nome: input.lerTexto("Nome da Vaga"),
                    descricao: input.lerTexto("Descrição"),
                    local: input.lerTexto("Local"),
                    empresa: dona
            )
            vagaDAO.salvar(nova)
            println "✅ Vaga criada com sucesso!"
        } catch (Exception e) {
            println "❌ Erro ao criar vaga (Empresa existe?): ${e.message}"
        }
    }

    // --- MÉTODOS DE COMPETÊNCIA ---

    static void gerenciarCompetencias() {
        println "\n--- 🧠 Gestão de Competências ---"
        println "a. Listar"
        println "b. Adicionar"
        println "c. Atualizar"
        println "d. Deletar"

        String subOpcao = input.lerTexto("Opção")

        switch (subOpcao) {
            case "a":
                competenciaDAO.listar().each { println "ID: ${it.id} | ${it.nome}" }
                break
            case "b":
                String nome = input.lerTexto("Nome da competência")
                competenciaDAO.adicionar(nome)
                break
            case "c":
                int id = input.lerInteiro("ID para alterar")
                String novoNome = input.lerTexto("Novo nome")
                competenciaDAO.atualizar(id, novoNome)
                break
            case "d":
                int idDel = input.lerInteiro("ID para deletar")
                competenciaDAO.deletar(idDel)
                break
            default:
                println "Opção inválida."
        }
    }
}