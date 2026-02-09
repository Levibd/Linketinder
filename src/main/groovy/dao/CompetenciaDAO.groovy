package dao

import model.Competencia
import java.sql.*

class CompetenciaDAO {

    private Connection conexao

    CompetenciaDAO(Connection conexao) {
        this.conexao = conexao
    }

    // Método para cadastrar nova competência
    void adicionar(String nome) {
        try {
            String sql = "INSERT INTO competencias (nome) VALUES (?) ON CONFLICT (nome) DO NOTHING"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setString(1, nome)
            int rows = stmt.executeUpdate()

            if (rows > 0) println "✅ Competência '${nome}' adicionada!"
            else println "⚠️ Competência '${nome}' já existe."

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
            println "❌ Erro ao listar: " + e.message
        }
        return lista
    }

    void atualizar(int id, String novoNome) {
        try {
            String sql = "UPDATE competencias SET nome = ? WHERE id = ?"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setString(1, novoNome)
            stmt.setInt(2, id)

            int rows = stmt.executeUpdate()
            if (rows > 0) println "✅ Competência atualizada!"
            else println "⚠️ Nenhuma competência encontrada com ID ${id}."

        } catch (SQLException e) {
            println "❌ Erro ao atualizar: " + e.message
        }
    }

    void deletar(int id) {
        try {
            String sql = "DELETE FROM competencias WHERE id = ?"
            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            stmt.setInt(1, id)

            int rows = stmt.executeUpdate()
            if (rows > 0) println "✅ Competência removida!"
            else println "⚠️ ID não encontrado."

        } catch (SQLException e) {
            println "❌ NÃO FOI POSSÍVEL DELETAR:"
            println "Esta competência está vinculada a Candidatos."
        }
    }
}