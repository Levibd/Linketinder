package dao

import model.Vaga
import model.Empresa
import java.sql.*

class VagaDAO {

    List<Vaga> listar() {
        Connection conn = DatabaseConnection.getConnection()
        List<Vaga> vagas = []

        try {

            String sql = """
                SELECT v.id, v.nome, v.descricao, v.local, v.id_empresa, e.nome as nome_empresa, e.email 
                FROM vagas v
                INNER JOIN empresas e ON v.id_empresa = e.id
            """
            PreparedStatement stmt = conn.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {

                Empresa emp = new Empresa(
                        id: rs.getInt("id_empresa"),
                        nome: rs.getString("nome_empresa"),
                        email: rs.getString("email")
                )

                Vaga vaga = new Vaga(
                        id: rs.getInt("id"),
                        nome: rs.getString("nome"),
                        descricao: rs.getString("descricao"),
                        local: rs.getString("local"),
                        empresa: emp
                )
                vagas.add(vaga)
            }
        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            conn.close()
        }
        return vagas
    }


    void salvar(Vaga vaga) {
        Connection conn = DatabaseConnection.getConnection()
        try {
            if (vaga.empresa == null || vaga.empresa.id == null) {
                println "❌ Erro: Vaga precisa estar vinculada a uma empresa existente."
                return
            }

            String sql = "INSERT INTO vagas (id_empresa, nome, descricao, local) VALUES (?, ?, ?, ?)"
            PreparedStatement stmt = conn.prepareStatement(sql)
            stmt.setInt(1, vaga.empresa.id)
            stmt.setString(2, vaga.nome)
            stmt.setString(3, vaga.descricao)
            stmt.setString(4, vaga.local)

            stmt.executeUpdate()
            println "✅ Vaga '${vaga.nome}' cadastrada com sucesso!"

        } catch (Exception e) {
            e.printStackTrace()
        } finally {
            conn.close()
        }
    }
}