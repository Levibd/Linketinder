package model

import groovy.transform.Canonical
import groovy.transform.ToString

import java.time.LocalDate

@Canonical
class Candidato extends Pessoa {
    String sobrenome
    String cpf
    LocalDate dataNascimento
    List<String> skills = []
}

