# 🔥 Linketinder

> Desafio ZG-Hero.
> Um sistema de "Match" corporativo inspirado no Tinder e LinkedIn.

![Badge Concluído](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Badge Groovy](http://img.shields.io/static/v1?label=BACKEND&message=GROOVY&color=ORANGE&style=for-the-badge)
![Badge Spock](https://img.shields.io/badge/Testing-Spock-green?style=for-the-badge)
![Badge TypeScript](https://img.shields.io/badge/FRONTEND-TYPESCRIPT-blue?style=for-the-badge)
![Badge Vite](https://img.shields.io/badge/Build-Vite-purple?style=for-the-badge)
![Badge Regex](https://img.shields.io/badge/Validation-Regex-red?style=for-the-badge)

## 🎯 Sobre o Projeto
O **Linketinder** tem como objetivo facilitar a contratação de talentos através do cruzamento de competências (skills). 
O projeto evoluiu de um MVP Backend para uma aplicação **Fullstack**, contando agora com uma interface moderna, validação de dados robusta e visualização analítica.

---

## 🏗 Arquitetura & Estrutura
O projeto funciona no formato **Monorepo**, dividido em:

### 🎨 Frontend (Interface & Dashboards)
Localizado na pasta `/frontend`. A interface moderna sai do terminal e vai para o navegador.
* **Tecnologias:** TypeScript, Vite, Chart.js, HTML5/CSS3.
* **Principais Features:**
    * 📊 **Dashboard Analítico:** Gráfico de barras exibindo skills mais procuradas/ofertadas.
    * 🔒 **Anonimato (LGPD):** Sistema de "máscara" onde nomes de empresas/candidatos são ocultos até o match.
    * ✨ **UX Interativa:** Navegação por abas e feedback visual instantâneo.

### 🛡️ Validação de Dados (Regex)
Implementação de segurança na entrada de dados utilizando **Expressões Regulares** no TypeScript.
* **Candidato:** Validação rigorosa de CPF, Telefone, LinkedIn (URL) e E-mail.
* **Empresa:** Validação de CNPJ, CEP e E-mail Corporativo.
* **Skills:** Sanitização de tags e formatação automática.

### ⚙️ Backend (Core Logic)
Localizado na raiz. O núcleo da lógica de negócios focado em POO.
* **Tecnologias:** Groovy, Gradle, Spock Framework.
* **Principais Features:**
    * 🧠 **Algoritmo de Match:** Lógica para identificar interesses mútuos.
    * 🧪 **TDD & Testes:** Regras de negócio blindadas por testes unitários com Spock.
    * 🗂️ **Gerenciamento:** CRUD em memória de perfis.

---

## 📂 Estrutura de Arquivos
Linketinder/
├── build.gradle            # Configuração Backend (Groovy)
├── src/                    # Código Fonte Backend
│   ├── main/groovy/        # Model, Service e Main
│   └── test/groovy/        # Testes Spock (LinketinderServiceSpec)
├── frontend/               # PROJETO FRONTEND
│   ├── src/
│   │   ├── main.ts         # Lógica Frontend + Regex Validators
│   │   └── interfaces.ts   # Tipagem TS
│   ├── index.html          # Entry point
│   └── package.json        # Dependências NPM
└── README.md

🚀 Como Executar
1️⃣ Rodando o Frontend (Visual)
Pré-requisito: Node.js (v18+ recomendado via NVM).

Bash
# Entre na pasta do frontend
cd frontend

# Instale as dependências
npm install

# Rode o servidor de desenvolvimento
npm run dev
O terminal exibirá um link (ex: http://localhost:5173) para acessar a aplicação.

2️⃣ Rodando o Backend (Terminal)
Pré-requisito: Java (JDK 17+).

Via Terminal:

Bash
# Na raiz do projeto
./gradlew run      # Linux/Mac
gradlew.bat run    # Windows
Via IntelliJ IDEA:

Abra o projeto e aguarde a importação do Gradle.

Navegue até src/main/groovy/Main.groovy.

Clique no Play (▶️) ao lado da função main.

🧪 Testes Automatizados (Backend)
O Backend foi desenvolvido seguindo a metodologia TDD com Spock Framework.

Bash
./gradlew test      # Linux/Mac
gradlew.bat test    # Windows
Ou no IntelliJ: Botão direito em LinketinderServiceSpec > Run.

👤 Autor
Levi - Desenvolvido durante a aceleração ZG Hero.
