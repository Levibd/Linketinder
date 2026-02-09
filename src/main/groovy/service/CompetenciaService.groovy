package service

import dao.CompetenciaDAO
import model.Competencia

class CompetenciaService {

    private CompetenciaDAO dao


    CompetenciaService(CompetenciaDAO dao) {
        this.dao = dao
    }


    List<Competencia> listarCompetencias() {
        return dao.listar()
    }


    void adicionar(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da competência não pode ser vazio.")
        }
        dao.adicionar(nome.trim())
    }


    void atualizar(int id, String novoNome) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido.")
        }
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo nome não pode ser vazio.")
        }
        dao.atualizar(id, novoNome.trim())
    }


    void deletar(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.")
        }
        dao.deletar(id)
    }
}