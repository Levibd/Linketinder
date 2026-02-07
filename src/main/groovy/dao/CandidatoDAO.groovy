package dao

import model.Candidato

import java.sql.*

class CandidatoDAO {

    void save(Candidato candidato){
        Connection conn = DatabaseConnection.getConnection()

        try {
            conn.setAutoCommit(false)


            String sqlCandidato = """
                INSERT INTO candidatos 
                (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) 
                RETURNING id
            """
            PreparedStatement stmt = conn.prepareStatement(sqlCandidato)

            stmt.setString(1, candidato.nome)
            stmt.setString(2, candidato.sobrenome)
            stmt.setDate(3, java.sql.Date.valueOf(candidato.dataNascimento))
            stmt.setString(4, candidato.email)
            stmt.setString(5, candidato.cpf)
            stmt.setString(6, candidato.pais)
            stmt.setString(7, candidato.cep)
            stmt.setString(8, candidato.descricao)
            stmt.setString(9, candidato.senha)

            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                int idCandidato = rs.getInt("id")
                candidato.id = idCandidato

                // 2. Lidar com Competências (N:N)
                CompetenciaDAO competenciaDAO = new CompetenciaDAO(conn)
                for (String skill : candidato.skills) {
                    int idCompetencia = competenciaDAO.buscarOuCriar(skill)
                    competenciaDAO.vincularCandidato(idCandidato, idCompetencia)
                }
            }

            conn.commit()
            println "✅ Candidato ${candidato.nome} salvo com sucesso!"

        } catch (Exception e) {
            conn.rollback()
            println "❌ Erro ao salvar candidato: " + e.getMessage()
        } finally {
            conn.close()


        }
    }

    List<Candidato> listar() {
        List<Candidato> lista = []
        Connection conn = DatabaseConnection.getConnection()
        String sql = "SELECT * FROM candidatos"
        PreparedStatement stmt = conn.prepareStatement(sql)
        ResultSet rs = stmt.executeQuery()

        while(rs.next()) {
            Candidato c = new Candidato()
            c.id = rs.getInt("id")
            c.nome = rs.getString("nome")
            c.sobrenome = rs.getString("sobrenome")
            c.email = rs.getString("email")
            // ... preencha o resto ...
            lista.add(c)
        }
        conn.close()
        return lista
    }
}
