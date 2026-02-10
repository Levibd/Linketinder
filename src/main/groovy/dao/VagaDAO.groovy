package dao

import model.Vaga
import model.Empresa
import java.sql.*

class VagaDAO implements Repositorio<Vaga> {

    private Connection conexao


    VagaDAO() {
        try {

            this.conexao = DatabaseConnection.getInstancia()


        } catch (Exception e) {
            println "⚠️ ERRO GRAVE: Não foi possível iniciar a conexão no DAO!"
            e.printStackTrace()
        }
    }

    @Override
    void salvar(Vaga vaga) {
        try {

            if (vaga.empresa == null || vaga.empresa.id == null) {
                println "❌ Erro: Vaga precisa estar vinculada a uma empresa (ID inválido)."
                return
            }

            String sql = "INSERT INTO vagas (id_empresa, nome, descricao, local) VALUES (?, ?, ?, ?)"


            PreparedStatement stmt = this.conexao.prepareStatement(sql)

            stmt.setInt(1, vaga.empresa.id)
            stmt.setString(2, vaga.nome)
            stmt.setString(3, vaga.descricao)
            stmt.setString(4, vaga.local)

            stmt.executeUpdate()
            println "✅ Vaga '${vaga.nome}' cadastrada com sucesso!"

        } catch (SQLException e) {
            println "❌ Erro ao salvar vaga: " + e.message
            e.printStackTrace()
        }

    }

    @Override
    List<Vaga> listar() {
        List<Vaga> vagas = []

        try {

            String sql = """
                SELECT v.id, v.nome, v.descricao, v.local, v.id_empresa, 
                       e.nome as nome_empresa, e.email as email_empresa
                FROM vagas v
                INNER JOIN empresas e ON v.id_empresa = e.id
            """


            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {

                Empresa emp = new Empresa()
                emp.id = rs.getInt("id_empresa")
                emp.nome = rs.getString("nome_empresa")
                emp.email = rs.getString("email_empresa")


                Vaga vaga = new Vaga(
                        id: rs.getInt("id"),
                        nome: rs.getString("nome"),
                        descricao: rs.getString("descricao"),
                        local: rs.getString("local"),
                        empresa: emp
                )
                vagas.add(vaga)
            }
        } catch (SQLException e) {
            println "❌ Erro ao listar vagas: " + e.message
            e.printStackTrace()
        }

        return vagas
    }
}