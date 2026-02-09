package dao

import model.Candidato
import java.sql.*

class CandidatoDAO implements Repositorio<Candidato> {

    private Connection conexao

    CandidatoDAO(Connection conexao) {
        try {

            this.conexao = DatabaseConnection.getInstancia()


        } catch (Exception e) {
            println "⚠️ ERRO GRAVE: Não foi possível iniciar a conexão no DAO!"
            e.printStackTrace()
        }
    }

    @Override
    void salvar(Candidato candidato) {
        try {

            this.conexao.setAutoCommit(false)

            String sqlCandidato = """
                INSERT INTO candidatos 
                (nome, sobrenome, data_nascimento, email, cpf, pais, cep, descricao, senha) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) 
                RETURNING id
            """


            PreparedStatement stmt = this.conexao.prepareStatement(sqlCandidato)

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

                CompetenciaDAO competenciaDAO = new CompetenciaDAO(this.conexao)

                for (String skill : candidato.skills) {
                    int idCompetencia = competenciaDAO.buscarOuCriar(skill)
                    competenciaDAO.vincularCandidato(idCandidato, idCompetencia)
                }
            }

            this.conexao.commit()
            println "✅ Candidato ${candidato.nome} salvo com sucesso!"

        } catch (Exception e) {
            try {
                this.conexao.rollback()
            } catch (SQLException re) {

            }
            println "❌ Erro ao salvar candidato: " + e.getMessage()
            e.printStackTrace()
        } finally {
            try {
                this.conexao.setAutoCommit(true)
            } catch (SQLException e) {}


        }
    }

    @Override
    List<Candidato> listar() {
        List<Candidato> lista = []
        String sql = "SELECT * FROM candidatos"


        try {
            Statement stmt = this.conexao.createStatement()
            ResultSet rs = stmt.executeQuery(sql)

            while(rs.next()) {
                Candidato c = new Candidato()
                c.id = rs.getInt("id")
                c.nome = rs.getString("nome")
                c.sobrenome = rs.getString("sobrenome")
                c.email = rs.getString("email")
                c.cpf = rs.getString("cpf")

                lista.add(c)
            }
        } catch (SQLException e) {
            println "Erro ao listar: " + e.getMessage()
        }


        return lista
    }
}