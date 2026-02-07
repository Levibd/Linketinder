package dao

import model.Competencia

import java.sql.*

class CompetenciaDAO {

    private Connection conn

    CompetenciaDAO() {
        this.conn = DatabaseConnection.getConnection()
    }

    CompetenciaDAO(Connection conn) {
        this.conn = conn
    }

    void adicionar(String nome) {
        try {
            String sql = "INSERT INTO competencias (nome) VALUES (?)"
            PreparedStatement stmt = this.conn.prepareStatement(sql)
            stmt.setString(1, nome)
            stmt.executeUpdate()
            println "✅ Competência '${nome}' adicionada!"
        } catch (SQLException e) {
            println "❌ Erro: Provavelmente a competência já existe."
        }
    }

    List<Competencia> listar() {
        List<Competencia> lista = []
        String sql = "SELECT * FROM competencias ORDER BY id"
        Statement stmt = this.conn.createStatement()
        ResultSet rs = stmt.executeQuery(sql)

        while (rs.next()) {
            lista.add(new Competencia(
                    id: rs.getInt("id"),
                    nome: rs.getString("nome")
            ))
        }
        return lista
    }

    void atualizar(int id, String novoNome) {
        String sql = "UPDATE competencias SET nome = ? WHERE id = ?"
        PreparedStatement stmt = this.conn.prepareStatement(sql)
        stmt.setString(1, novoNome)
        stmt.setInt(2, id)
        int rows = stmt.executeUpdate()
        if (rows > 0) println "✅ Competência atualizada!"
        else println "⚠️ Nenhuma competência encontrada com ID ${id}."
    }

    void deletar(int id) {
        try {
            String sql = "DELETE FROM competencias WHERE id = ?"
            PreparedStatement stmt = this.conn.prepareStatement(sql)
            stmt.setInt(1, id)
            int rows = stmt.executeUpdate()
            if (rows > 0) println "✅ Competência removida!"
            else println "⚠️ ID não encontrado."
        } catch (SQLException e) {
            println "❌ NÃO FOI POSSÍVEL DELETAR:"
            println "Esta competência está vinculada a Candidatos ou Vagas."
            println "Remova as dependências antes de excluir."
        }
    }

    int buscarOuCriar(String nome) {
        String sqlBusca = "SELECT id FROM competencias WHERE nome = ?"
        PreparedStatement stmt = conn.prepareStatement(sqlBusca)
        stmt.setString(1, nome)
        ResultSet rs = stmt.executeQuery()

        if (rs.next()) {
            return rs.getInt("id")
        } else {
            String sqlInsert = "INSERT INTO competencias (nome) VALUES (?) RETURNING id"
            PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)
            stmtInsert.setString(1, nome)
            ResultSet rsInsert = stmtInsert.executeQuery()
            rsInsert.next()
            return rsInsert.getInt("id")
        }
    }

    void vincularCandidato(int idCandidato, int idCompetencia) {
        String sql = "INSERT INTO competencias_candidatos (id_candidato, id_competencia) VALUES (?, ?) ON CONFLICT DO NOTHING"
        PreparedStatement stmt = conn.prepareStatement(sql)
        stmt.setInt(1, idCandidato)
        stmt.setInt(2, idCompetencia)
        stmt.executeUpdate()
    }

}
