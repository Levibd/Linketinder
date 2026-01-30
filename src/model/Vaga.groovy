package model

import groovy.transform.Canonical

@Canonical
class Vaga {
    String name
    String description
    String state
    Empresa empresa
    List<String> skills = []
}