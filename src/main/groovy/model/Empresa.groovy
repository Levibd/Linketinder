package model

import groovy.transform.Canonical

@Canonical
class Empresa extends Pessoa{
    String cnpj
    String country
}

