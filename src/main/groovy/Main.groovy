import controller.CandidatoController
import controller.CompetenciaController
import controller.EmpresaController
import controller.VagaController
import dao.CandidatoDAO
import dao.CompetenciaDAO
import dao.DatabaseConnection
import dao.EmpresaDAO
import dao.VagaDAO
import service.CandidatoService
import service.CompetenciaService
import service.EmpresaService
import service.InputService
import service.VagaService

import java.sql.Connection

/*
 * Autor: Levi
 * Projeto: Linketinder (Refatorado: MVC Architecture)
 */
class Main {

    // --- DEPENDÊNCIAS GLOBAIS (Controllers & Utils) ---
    static Connection conexao
    static InputService input

    // Controllers
    static CandidatoController candidatoController
    static EmpresaController empresaController
    static VagaController vagaController
    static CompetenciaController competenciaController

    static void main(String[] args) {
        // 1. Inicialização
        inicializarDependencias()

        boolean executando = true
        println "🚀 Bem-vindo ao Linketinder (MVC Edition)"

        // 2. View Loop
        while (executando) {
            exibirMenu()

            String opcao = input.lerTexto("Escolha uma opção")

            try {
                switch (opcao) {
                // --- ROTA DE CANDIDATOS ---
                    case "1":
                        candidatoController.processarListagem()
                        break
                    case "3":
                        candidatoController.processarCadastro()
                        break

                        // --- ROTA DE EMPRESAS ---
                    case "2":
                        empresaController.processarListagem()
                        break
                    case "4":
                        empresaController.processarCadastro()
                        break

                        // --- ROTA DE VAGAS ---
                    case "5":
                        vagaController.processarListagem()
                        break
                    case "6":
                        vagaController.processarCadastro()
                        break

                        // --- ROTA DE COMPETÊNCIAS ---
                    case "7":
                        competenciaController.iniciarGerenciamento()
                        break

                        // --- SISTEMA ---
                    case "0":
                        println "👋 Encerrando o sistema..."
                        encerrarSistema()
                        executando = false
                        break

                    default:
                        println "⚠️ Opção inválida. Tente novamente."
                }
            } catch (Exception e) {
                println "🔴 Erro fatal na camada de apresentação: ${e.message}"
                e.printStackTrace()
            }
        }
    }


    static void inicializarDependencias() {
        try {
            // 1. Infraestrutura (Singleton e Utils)
            conexao = DatabaseConnection.getInstancia()
            input = new InputService()

            // 2. Camada de Persistência (DAOs) - Só falam com o Banco
            CandidatoDAO candidatoDAO = new CandidatoDAO(conexao)
            EmpresaDAO empresaDAO = new EmpresaDAO(conexao)
            VagaDAO vagaDAO = new VagaDAO(conexao)
            CompetenciaDAO competenciaDAO = new CompetenciaDAO(conexao)

            // 3. Camada de Serviço (Services) - Só falam com DAOs
            CandidatoService candidatoService = new CandidatoService(candidatoDAO)
            EmpresaService empresaService = new EmpresaService(empresaDAO)
            VagaService vagaService = new VagaService(vagaDAO)
            CompetenciaService competenciaService = new CompetenciaService(competenciaDAO)

            // 4. Camada de Controle (Controllers) - Recebem Services e Input
            candidatoController = new CandidatoController(candidatoService, input)
            empresaController = new EmpresaController(empresaService, input)

            // Atenção: VagaController precisa de VagaService E EmpresaService (para listar empresas no cadastro)
            vagaController = new VagaController(vagaService, empresaService, input)

            competenciaController = new CompetenciaController(competenciaService, input)

            println "✅ MVC Inicializado: Todas as dependências foram injetadas."

        } catch (Exception e) {
            println "❌ Falha crítica ao iniciar dependências: ${e.message}"
            System.exit(1) // Aborta se não conseguir conectar
        }
    }

    static void encerrarSistema() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close()
                println "🔌 Conexão com o banco encerrada."
            }
        } catch (Exception e) {
            println "Erro ao fechar conexão: ${e.message}"
        }
    }

    static void exibirMenu() {
        println "\n==============================="
        println "      MENU PRINCIPAL (MVC)     "
        println "==============================="
        println "1. Listar Candidatos"
        println "2. Listar Empresas"
        println "3. Cadastrar Candidato"
        println "4. Cadastrar Empresa"
        println "5. Listar Vagas"
        println "6. Cadastrar Vaga"
        println "7. Gerenciar Competências"
        println "0. Sair"
        println "==============================="
    }
}