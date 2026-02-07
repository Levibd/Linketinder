package model

import groovy.transform.Canonical

@Canonical
abstract class Pessoa {
    Integer id
    String nome
    String email
    String pais
    String cep
    String descricao
    String senha
}