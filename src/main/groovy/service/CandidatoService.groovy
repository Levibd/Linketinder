package service

import dao.CandidatoDAO
import model.Candidato

class CandidatoService {
    CandidatoDAO dao

    CandidatoService() {
        this.dao = new CandidatoDAO()
    }

    CandidatoService(CandidatoDAO dao) {
        this.dao = dao
    }

    void cadastrarCandidato(Candidato candidato) {
        if (candidato.nome == null || candidato.nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do candidato é obrigatório")
        }
        dao.salvar(candidato)
    }

    List<Candidato> listarCandidatos() {
        return dao.listar()
    }
}