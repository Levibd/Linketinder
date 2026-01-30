package model

import groovy.transform.Canonical
import groovy.transform.ToString

@Canonical
class Candidato extends Pessoa {
    String cpf
    int age
}

