# 🔥 Linketinder MVP (Groovy)

> Desafio ZG-Hero (Trilha Groovy).
> Um sistema de "Match" corporativo inspirado no Tinder e LinkedIn.

![Badge Concluído](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Badge Groovy](http://img.shields.io/static/v1?label=LANGUAGE&message=GROOVY&color=ORANGE&style=for-the-badge)
![Badge Spock](https://img.shields.io/badge/Testing-Spock-green?style=for-the-badge)
![Badge Gradle](https://img.shields.io/badge/Build-Gradle-blue?style=for-the-badge)

## 🎯 Sobre o Projeto
O Linketinder tem como objetivo facilitar a contratação de talentos através do cruzamento de competências (skills). Neste MVP, focamos na estruturação dos dados, listagem de **Candidatos** e **Empresas** e validação de regras de negócio utilizando **TDD**.

## 🛠 Funcionalidades
* **Listagem de Candidatos:** Exibe dados pessoais e lista de competências técnicas.
* **Listagem de Empresas:** Exibe dados corporativos e quais skills a empresa busca.
* **Cadastro Dinâmico:** Permite adicionar novos candidatos e empresas.
* **Sistema de Match:** Lógica para identificar interesses mútuos.
* **Testes Automatizados:** Cobertura de testes unitários para garantir a integridade do cadastro.

## 💻 Tecnologias e Conceitos
* **Groovy:** Linguagem dinâmica rodando na JVM.
* **Gradle:** Gerenciamento de dependências e build.
* **Spock Framework:** Testes unitários com sintaxe expressiva (BDD/TDD).
* **POO:** Uso de Herança, Polimorfismo e Encapsulamento.
* **Arquitetura MVC:** Separação entre Model, Service e View (Main).

## 📂 Estrutura do Projeto (Gradle Standard)
```text
Linketinder/
├── build.gradle           # Configurações do projeto e dependências
├── src/
│   ├── main/groovy/
│   │   ├── model/         # Classes (Candidato, Empresa, Pessoa)
│   │   ├── service/       # Regras de Negócio e Listas
│   │   └── Main.groovy    # Menu e interação com usuário
│   └── test/groovy/
│       └── service/       # Testes Unitários (LinketinderServiceSpec)
└── .gitignore

🧪 Como Rodar os Testes (TDD)
O projeto foi desenvolvido seguindo a metodologia TDD. Para executar os testes do Spock:

Via Terminal:

Bash
./gradlew test   # Linux/Mac
gradlew.bat test # Windows
Via IntelliJ:

Navegue até src/test/groovy/service.

Clique com o botão direito em LinketinderServiceSpec.

Selecione Run 'LinketinderServiceSpec'.

🚀 Como Executar o Projeto
Pré-requisito: Ter o Java (JDK 17+) instalado.

Clone o repositório.

Abra o projeto no IntelliJ IDEA (aguarde a importação do Gradle).

Navegue até src/main/groovy/Main.groovy.

Clique no Play (▶️) ao lado da função main.

👤 Autor
Levi Desenvolvido durante o bootcamp Acelera ZG.


### O que eu atualizei:
1.  **Badges:** Adicionei Gradle e Spock.
2.  **Tecnologias:** Incluí Gradle e Spock Framework.
3.  **Estrutura:** Atualizei para mostrar a pasta `test` e `main` (padrão Gradle).
4.  **Execução:** Removi o comando manual antigo (`groovy -cp`) e coloquei o jeito moderno via Gradle/IntelliJ.
5.  **Testes:** Criei uma seção específica ensinando a rodar o Spock.

Pode subir esse que está completão! 🚀