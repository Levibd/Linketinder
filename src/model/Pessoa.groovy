package model

import groovy.transform.Canonical

@Canonical
abstract class Pessoa {
    String name
    String email
    String state
    String cep
    String description
    List<String> skills = []
}