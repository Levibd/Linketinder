package dao

import model.Competencia
import java.sql.*

class CompetenciaDAO {

    private Connection conexao


    CompetenciaDAO(Connection conexao) {
        this.conexao = conexao
    }

    // --- MÉTODOS CRUD PADRÃO (Usados pelo Controller) ---

    void adicionar(String nome) {
        try {
            String sql = "INSERT INTO competencias (nome) VALUES (?) ON CONFLICT (nome) DO NOTHING"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setString(1, nome)
            stmt.executeUpdate()
        } catch (SQLException e) {
            println "❌ Erro ao adicionar competência: " + e.message
        }
    }

    List<Competencia> listar() {
        List<Competencia> lista = []
        String sql = "SELECT * FROM competencias ORDER BY id"
        try {
            Statement stmt = this.conexao.createStatement()
            ResultSet rs = stmt.executeQuery(sql)

            while (rs.next()) {
                lista.add(new Competencia(
                        id: rs.getInt("id"),
                        nome: rs.getString("nome")
                ))
            }
        } catch (SQLException e) {
            println "Erro ao listar: " + e.message
        }
        return lista
    }

    void atualizar(int id, String novoNome) {
        try {
            String sql = "UPDATE competencias SET nome = ? WHERE id = ?"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setString(1, novoNome)
            stmt.setInt(2, id)
            stmt.executeUpdate()
        } catch (SQLException e) {
            println "Erro ao atualizar: " + e.message
        }
    }

    void deletar(int id) {
        try {
            String sql = "DELETE FROM competencias WHERE id = ?"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setInt(1, id)
            stmt.executeUpdate()
        } catch (SQLException e) {
            println "Erro ao deletar (pode estar em uso): " + e.message
        }
    }


    int buscarOuCriar(String nome) {
        try {

            String sqlBusca = "SELECT id FROM competencias WHERE nome = ?"
            PreparedStatement stmt = this.conexao.prepareStatement(sqlBusca)
            stmt.setString(1, nome)
            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                return rs.getInt("id")
            } else {

                String sqlInsert = "INSERT INTO competencias (nome) VALUES (?) RETURNING id"
                PreparedStatement stmtInsert = this.conexao.prepareStatement(sqlInsert)
                stmtInsert.setString(1, nome)
                ResultSet rsInsert = stmtInsert.executeQuery()

                if (rsInsert.next()) {
                    return rsInsert.getInt("id")
                }
            }
        } catch (SQLException e) {

            println "Erro ao buscar/criar skill '${nome}': " + e.message
        }
        return -1
    }


    void vincularCandidato(int idCandidato, int idCompetencia) {
        if (idCompetencia == -1) return

        try {
            String sql = "INSERT INTO competencias_candidatos (id_candidato, id_competencia) VALUES (?, ?) ON CONFLICT DO NOTHING"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setInt(1, idCandidato)
            stmt.setInt(2, idCompetencia)
            stmt.executeUpdate()
        } catch (SQLException e) {
            println "Erro ao vincular skill: " + e.message
        }
    }
}