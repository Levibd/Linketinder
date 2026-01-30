import model.Candidato
import service.LinketinderService

class Main {
    static void main(String[] args) {
        LinketinderService service = new LinketinderService()
        Scanner scanner = new Scanner(System.in)
        boolean running = true

        println "🚀 Bem-vindo ao Linketinder (Groovy ZG-Hero)"

        while (running) {
            println "\n1. Listar Candidatos"
            println "2. Listar Empresas"
            println "3. Cadastrar Candidato"
            println "4. Listar Vagas"
            println "5. Curtir Vaga (em construção)"
            println "0. Sair"
            print "> "

            def opcao = scanner.nextLine()

            switch (opcao) {
                case "1":
                    println "\n--- 🧑‍💻 Candidatos ---"
                    service.candidatos.each { c ->
                        println "Nome: ${c.name} | Idade: ${c.age} | Skills: ${c.skills}"
                    }
                    break
                case "2":
                    println "\n--- 🏢 Empresas ---"
                    service.empresas.each { e ->
                        println "Nome: ${e.name} | CNPJ: ${e.cnpj} | Busca: ${e.skills}"
                    }
                    break
                case "3":
                    println "\n--- Novo Cadastro ---"
                    print "Nome: "
                    def nome = scanner.nextLine()
                    print "Email: "
                    def email = scanner.nextLine()
                    print "Idade: "
                    def idade = scanner.nextLine().toInteger()
                    print "Skills (separe por vírgula): "
                    def skills = scanner.nextLine().split(",").collect { it.trim() }


                    def novo = new Candidato(
                            name: nome, email: email, age: idade, skills: skills,

                            cpf: "000.000.000-00", state: "ND", cep: "00000-000", description: "Novo cadastro"
                    )

                    service.addCandidate(novo)
                    println "✅ Salvo com sucesso!"
                    break
                case "4":
                    println "\n--- 💼 VAGAS DISPONÍVEIS ---"
                    service.vagas.eachWithIndex { v, index ->
                        println "${index}. ${v.name} [${v.empresa.name}] - Skills: ${v.skills}"
                    }
                    break
                case "5": // Simular Fluxo de Match (Demo)
                    println "\n--- ❤️ SIMULAÇÃO DE MATCH ---"

                    def candidatoLevi = service.candidatos[0] // Levi
                    def vagaZG = service.vagas[0]             // Vaga da ZG Hero
                    def empresaZG = service.empresas[0]       // ZG Hero

                    println "1. ${candidatoLevi.name} entra no app e vê a vaga da ZG..."
                    service.likeJob(candidatoLevi, vagaZG)

                    println "\n2. Dias depois, a ZG Hero analisa o perfil do Levi..."
                    service.companyLikesCandidate(empresaZG, candidatoLevi)
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