# 📦 Payloads JSON para Teste (Linketinder)

Este arquivo contém os modelos de JSON para testar os endpoints da API via Postman ou Insomnia.

---

## 1. 🧑‍💻 Candidato

**Endpoint:** `POST /candidatos`
**URL:** `http://localhost:8080/candidatos`

json
{
    "nome": "Levi",
    "sobrenome": "Highlander",
    "email": "levi@ackerman.com",
    "cpf": "123.456.789-00",
    "dataNascimento": "1998-05-20",
    "pais": "Brasil",
    "cep": "01001-000",
    "descricao": "Dev Backend Groovy com sangue nos olhos",
    "senha": "123",
    "skills": ["Java", "Groovy", "SQL"]
}

{
    "nome": "Tech Solutions",
    "cnpj": "12.345.678/0001-99",
    "email": "rh@techsolutions.com",
    "descricao": "Empresa líder em inovação e tecnologia",
    "pais": "Brasil",
    "cep": "02002-000",
    "senha": "123"
}

2. 🏢 Empresa
Endpoint: POST /empresas URL: http://localhost:8080/empresas

JSON
{
    "nome": "Tech Solutions",
    "cnpj": "12.345.678/0001-99",
    "email": "rh@techsolutions.com",
    "descricao": "Empresa líder em inovação e tecnologia",
    "pais": "Brasil",
    "cep": "02002-000",
    "senha": "123"
}

3. 💼 Vaga
   Endpoint: POST /vagas URL: http://localhost:8080/vagas

JSON
{
"nome": "Desenvolvedor Backend Java Junior",
"descricao": "Vaga para atuar com Java, Spring e PostgreSQL.",
"estado": "SP",
"cep": "01001-000",
"email": "rh@techsolutions.com",
"competencias": ["Java", "SQL", "Git"],
"empresa": {
"id": 1
}
}