package service

import dao.CandidatoDAO
import dao.VagaDAO
import model.Candidato
import model.Vaga

class VagaService {
    VagaDAO dao

    VagaService(VagaDAO dao) {
        this.dao = dao
    }

    void cadastrarVaga(Vaga vaga) {
        if (vaga.nome == null || vaga.nome.isEmpty()) {
            throw new IllegalArgumentException("Nome da vaga é obrigatório")
        }
        dao.salvar(vaga)
    }

    List<Vaga> listarVagas() {
        return dao.listar()
    }
}