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

## 🚀 Funcionalidades Implementadas
- **Arquitetura MVC Pura:** Separação clara entre Models, DAOs (Data Access Objects), Services e Controllers (Servlets).
- **CRUD de Candidatos:** Cadastro completo com persistência em banco relacional.
- **Tratamento de Dados:**
  - Conversão de JSON manual com GSON.
  - TypeAdapter customizado para lidar com `LocalDate` (Java 8 Time API).
- **Infraestrutura:**
  - Build automatizado com **Gradle**.
  - Servidor **Tomcat embarcado** (via plugin Gretty) para facilitar a execução.
  - Conexão **JDBC Singleton** com PostgreSQL.

## 🛠️ Tecnologias
- Java 17 / Groovy 4
- Servlet API 4.0.1
- PostgreSQL (Driver JDBC)
- Google GSON

## 🏛️ Refatoração MVC (Model-View-Controller)

Nesta etapa, o projeto foi reestruturado para seguir o padrão arquitetural **MVC**, separando claramente as responsabilidades e preparando a aplicação para escalabilidade.

### 🔄 Como o código foi organizado:

1.  **View (Camada de Apresentação):**
    * **Onde:** Classe `Main` e `InputService`.
    * **Responsabilidade:** A classe `Main` agora atua apenas como roteadora. Ela não contém lógica de negócio nem SQL. Ela exibe o menu e captura a intenção do usuário.

2.  **Controller (Camada de Controle):**
    * **Onde:** Pacote `controller` (`CandidatoController`, `EmpresaController`, etc.).
    * **Responsabilidade:** Recebe a entrada da View, orquestra a chamada para os serviços e decide qual resposta devolver para a tela. É o "maestro" da operação.

3.  **Service (Camada de Negócio):**
    * **Onde:** Pacote `service`.
    * **Responsabilidade:** Contém a lógica de negócio (ex: validações de campos obrigatórios). É a única camada autorizada a falar com o banco de dados (DAO).

4.  **Model/DAO (Camada de Dados):**
    * **Onde:** Pacotes `model` e `dao`.
    * **Responsabilidade:** O `DAO` (Data Access Object) persiste os dados no PostgreSQL. O `Model` representa a estrutura dos dados.

### 🚀 Benefício da Refatoração
A aplicação deixou de ser um script procedural monolítico e passou a ser um sistema modular. Agora, a troca de uma interface de linha de comando (Console) para uma API REST ou Web exigiria apenas a substituição da camada **View/Controller**, mantendo toda a regra de negócio (`Service`) e persistência (`DAO`) intactas.

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

      
 ## 🗄️ Arquitetura de Dados (Persistência)

Nesta etapa (K1-T9), o sistema migrou de armazenamento em memória para um Banco de Dados Relacional **PostgreSQL**.

### 🐳 Ambiente Docker
O projeto conta com containerização para facilitar a execução do banco e ferramentas de administração.

**Serviços:**
1.  **PostgreSQL 15:** Rodando na porta `5432`.
2.  **pgAdmin 4:** Interface web para gestão do banco, rodando na porta `5050`.

### 🐳 Como subir o Banco
Não é necessário instalar nada na máquina. Basta ter o Docker rodando:

docker compose up -d

O ambiente subirá:

Postgres na porta 5432.

pgAdmin (Interface Visual) na porta 5050 (Acesso: http://localhost:5050).

🗺️ Diagrama Entidade-Relacionamento (DER)
Modelagem contemplando as regras de negócio:

Relacionamento N:N entre Candidatos/Vagas e Competências.

Relacionamento 1:N entre Empresas e Vagas.

<img width="1061" height="789" alt="Diagram DB" src="https://github.com/user-attachments/assets/d4205c5c-26fa-452d-83d6-db48a342b77d" />

## 🧩 Design Patterns Aplicados

Nesta refatoração, a arquitetura do Linketinder evoluiu para utilizar padrões de projeto clássicos (GoF), visando desacoplamento e gestão eficiente de recursos.

### 1️⃣ Singleton Pattern
* **Onde:** Classe `DatabaseConnection`.
* **Problema:** Abertura indiscriminada de múltiplas conexões com o banco de dados, consumindo recursos desnecessários do servidor PostgreSQL.
* **Solução:** Implementação de uma instância estática única (`private static Connection instancia`). O método `getInstancia()` verifica se a conexão já existe antes de criar uma nova.
* **Benefício:** Controle centralizado de recursos e garantia de que toda a aplicação compartilha o mesmo contexto transacional.

### 2️⃣ Factory Method Pattern
* **Onde:** Interface `ConnectionFactory` e classe `PostgresFactory`.
* **Problema:** O código de conexão JDBC estava acoplado ("chumbado") diretamente na classe utilitária, dificultando a troca de banco de dados (ex: mudar para Oracle ou H2 para testes).
* **Solução:** Criação de uma interface fábrica que define o contrato de criação. A implementação concreta (`PostgresFactory`) contém os detalhes específicos do driver JDBC.
* **Benefício:** Segue o princípio Aberto/Fechado (OCP). Para mudar o banco, basta criar uma nova classe `MysqlFactory` sem alterar a lógica de negócio ou os DAOs.

### 3️⃣ DAO (Data Access Object)
* **Onde:** Pacote `dao` (`CandidatoDAO`, etc).
* **Conceito:** Abstração da camada de persistência.
* **Benefício:** Separa completamente a lógica de negócio (Model/Service) dos comandos SQL complexos.

### 4️⃣ Strategy Pattern (Via Injeção de Dependência)
* **Onde:** Interface `Repositorio<T>`.
* **Aplicação:** Ao definirmos um contrato comum para os repositórios, permitimos que a aplicação trate diferentes entidades (Candidato, Empresa) de forma polimórfica, facilitando a troca da estratégia de armazenamento no futuro.


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
