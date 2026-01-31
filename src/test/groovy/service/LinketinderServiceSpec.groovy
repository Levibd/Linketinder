package service

import model.Candidato
import model.Empresa
import spock.lang.Specification

class LinketinderServiceSpec extends Specification {

    def service = new LinketinderService()

    def "Should add a new candidate"() {
        given: "a valid candidate"
        def candidate = new Candidato(
                name: "John Doe",
                email: "john@test.com",
                cpf: "123.456.789-00",
                age: 25,
                state: "CA",
                cep: "90000-000",
                description: "Backend Developer",
                skills: ["Groovy", "Spock", "Java"]
        )
        def initialSize = service.candidatos.size()

        when: "service adds the candidate"
        service.addCandidate(candidate)

        then: "the list size should increase by 1"
        service.candidatos.size() == initialSize + 1

        and: "the last candidate in the list should be John"
        service.candidatos.last().name == "John Doe"
        service.candidatos.last().skills.contains("Spock")

    }

    def "Should add a new company to the list"() {
        given: "a valid company"
        def newCompany = new Empresa(
                name: "Tech Solutions Inc.",
                email: "contact@techsolutions.com",
                cnpj: "12.345.678/0001-90",
                country: "USA",
                state: "NY",
                cep: "10001",
                description: "Software House",
                skills: ["Java", "Cloud"]
        )
        def initialSize = service.companies.size()

        when: "the service adds the company"
        service.addCompany(newCompany)

        then: "the company list size should increase"
        service.companies.size() == initialSize + 1

        and: "the company should be stored correctly"
        service.companies.contains(newCompany)
    }

    def "Should not add a candidate with empty name"() {
        given: "an invalid candidate with empty name"
        def invalidCandidate = new Candidato(
                name: "",
                email: "invalid@test.com",
                cpf: "000.000.000-00",
                age: 20,
                state: "SP",
                cep: "00000-000",
                description: "Invalid",
                skills: []
        )

        when: "the service tries to add the candidate"
        service.addCandidate(invalidCandidate)

        then: "an exception should be thrown"
        thrown(IllegalArgumentException)
    }

    def "Should not add a company with invalid CNPJ"() {
        given: "an invalid company with malformed CNPJ"
        def invalidCompany = new Empresa(
                name: "Invalid Co",
                email: "corp@gmail.com",
                cnpj: "",
                country: "USA",
                state: "NY",
                cep: "10001",
                description: "Software House",
                skills: ["Java", "Cloud"]
        )

        when: "the service tries to add the company"
        service.addCompany(invalidCompany)

        then: "an exception should be thrown"
        thrown(IllegalArgumentException)
    }
}