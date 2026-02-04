# 🔥 Linketinder

> Desafio ZG-Hero.
> Um sistema de "Match" corporativo inspirado no Tinder e LinkedIn.

![Badge Concluído](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Badge Groovy](http://img.shields.io/static/v1?label=BACKEND&message=GROOVY&color=ORANGE&style=for-the-badge)
![Badge Spock](https://img.shields.io/badge/Testing-Spock-green?style=for-the-badge)
![Badge Gradle](https://img.shields.io/badge/Build-Gradle-blue?style=for-the-badge)
![Badge TypeScript](https://img.shields.io/badge/FRONTEND-TYPESCRIPT-blue?style=for-the-badge)
![Badge Vite](https://img.shields.io/badge/Build-Vite-purple?style=for-the-badge)

## 🎯 Sobre o Projeto
O Linketinder tem como objetivo facilitar a contratação de talentos através do cruzamento de competências (skills). 
O projeto evoluiu de um MVP Backend para uma aplicação Fullstack, contando agora com uma interface moderna para visualização de dados.

## 🏗 Arquitetura do Projeto
O projeto funciona no formato **Monorepo**, contendo:
1.  **Backend (Raiz):** Lógica de negócios, validações e algoritmos de match escritos em Groovy.
2.  **Frontend (Pasta `/frontend`):** Interface visual interativa para candidatos e empresas.

---

## 🎨 Frontend (Novidade!)
A interface foi desenvolvida para modernizar a interação com o usuário, saindo do terminal para o navegador.

### 🛠 Tecnologias
- **TypeScript:** Tipagem estática e Interfaces para candidatos/empresas.
- **Vite:** Build tool ultra-rápida com HMR (Hot Module Replacement).
- **Chart.js:** Gráficos dinâmicos para análise de competências.
- **HTML5/CSS3:** Estilização responsiva.

### ✨ Funcionalidades Visuais
- **Dashboard da Empresa:** Gráfico de barras exibindo a quantidade de candidatos por competência.
- **Anonimato (LGPD):** Empresas veem candidatos sem nome, e candidatos veem vagas como "Empresa Confidencial" até ocorrer o Match.
- **Cadastro Dinâmico:** Formulários para inserção de novos perfis e vagas.

---

## ⚙️ Backend (Groovy MVP)
O núcleo da lógica de negócios focado em POO e testes automatizados.

### 🛠 Tecnologias
- **Groovy:** Linguagem dinâmica na JVM.
- **Gradle:** Gerenciamento de dependências.
- **Spock Framework:** Testes unitários BDD/TDD.

### 🧩 Funcionalidades
- **Listagem & Cadastro:** Gerenciamento de Candidatos e Empresas.
- **Algoritmo de Match:** Lógica para identificar interesses mútuos baseados em skills.
- **Validação TDD:** Regras de negócio blindadas por testes unitários.

---

## 📂 Estrutura de Pastas
Linketinder/
├── build.gradle            # Configuração Backend (Groovy)
├── src/                    # Código Fonte Backend
│   ├── main/groovy/        # Model, Service e Main
│   └── test/groovy/        # Testes Spock (LinketinderServiceSpec)
├── frontend/               # PROJETO FRONTEND
│   ├── src/
│   │   ├── main.ts         # Lógica Frontend
│   │   └── interfaces.ts   # Tipagem TS
│   ├── index.html          # Entry point
│   └── package.json        # Dependências NPM
└── README.md

🚀 Como Executar
1️⃣ Rodando o Frontend (Visual)
Necessário ter Node.js instalado (Recomendado v18+ via NVM).

Bash
cd frontend
npm install
npm run dev
O terminal exibirá um link (ex: http://localhost:5173) para acessar a aplicação.

2️⃣ Rodando o Backend (Lógica/Terminal)
Necessário ter Java (JDK 17+) instalado.

Via IntelliJ:

Abra o projeto e aguarde a importação do Gradle.

Navegue até src/main/groovy/Main.groovy.

Clique no Play (▶️) ao lado da função main.

Via Terminal:

Bash
./gradlew run      # Linux/Mac
gradlew.bat run    # Windows
🧪 Testes Automatizados (Backend)
O Backend foi desenvolvido seguindo a metodologia TDD com Spock.

Para rodar os testes:

Bash
./gradlew test      # Linux/Mac
gradlew.bat test    # Windows
Ou no IntelliJ: Clique com o botão direito em LinketinderServiceSpec > Run.

👤 Autor
Levi - Desenvolvido durante a aceleração ZG Hero.
