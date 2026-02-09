package service

import dao.CandidatoDAO
import dao.EmpresaDAO
import model.Candidato
import model.Empresa

class EmpresaService {

    EmpresaDAO dao

    EmpresaService(EmpresaDAO dao) {
        this.dao = dao
    }

    void cadastrarEmpresa(Empresa empresa) {
        if (empresa.nome == null || empresa.nome.isEmpty()) {
            throw new IllegalArgumentException("Nome da empresa é obrigatório")
        }
        dao.salvar(empresa)
    }

    List<Empresa> listarEmpresas() {
        return dao.listar()
    }
}
