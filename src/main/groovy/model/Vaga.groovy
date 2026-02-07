package model

import groovy.transform.Canonical

@Canonical
class Vaga {
    Integer id
    String nome
    String descricao
    String local
    Empresa empresa
    List<String> skills = []
}