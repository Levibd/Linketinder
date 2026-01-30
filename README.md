# 🔥 Linketinder MVP (Groovy)

> Desafio ZG-Hero (Trilha Groovy).
> Um sistema de "Match" corporativo inspirado no Tinder e LinkedIn

![Badge Concluído](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Badge Groovy](http://img.shields.io/static/v1?label=LANGUAGE&message=GROOVY&color=ORANGE&style=for-the-badge)

## 🎯 Sobre o Projeto
O Linketinder tem como objetivo facilitar a contratação de talentos através do cruzamento de competências (skills). Neste MVP, focamos na estruturação dos dados e na listagem de **Candidatos** e **Empresas** utilizando o paradigma Orientado a Objetos.

## 🛠 Funcionalidades
* **Listagem de Candidatos:** Exibe dados pessoais e lista de competências técnicas.
* **Listagem de Empresas:** Exibe dados corporativos e quais skills a empresa busca.
* **Cadastro Dinâmico:** Permite adicionar novos candidatos via terminal.
* **Banco de Dados em Memória:** O sistema inicia com 5 candidatos e 5 empresas pré-carregados para facilitar os testes.

## 💻 Tecnologias e Conceitos
* **Groovy:** Linguagem dinâmica rodando na JVM.
* **POO (Programação Orientada a Objetos):** Uso de Herança, Polimorfismo e Encapsulamento.
* **Arquitetura MVC:** Separação clara entre Modelo (`model`), Regras de Negócio (`service`) e Interface (`Main`).
* **Groovy Annotations:** Uso de `@Canonical` para geração automática de `toString`, `equals` e `hashCode`.

## 📂 Estrutura do Projeto
```text
Linketinder/
├── src/
│   └── groovy/
│       ├── model/          # Classes (Candidato, Empresa, Pessoa)
│       ├── service/        # Lógica de negócio e listas
│       └── Main.groovy     # Menu e interação com usuário
└── .gitignore              # Arquivos ignorados pelo Git


🚀 Como Executar
Pré-requisito: Ter o Groovy instalado e configurado no PATH.

Clone o repositório.

Abra o terminal na pasta raiz do projeto (Linketinder).

Execute o comando abaixo (necessário para reconhecer os pacotes):

Bash
groovy -cp src/groovy src/groovy/Main.groovy
(O parâmetro -cp indica o classpath onde estão as classes de modelo e serviço).

👤 Autor
Levi Desenvolvido durante o bootcamp Acelera ZG.
