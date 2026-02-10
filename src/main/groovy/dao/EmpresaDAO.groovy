package dao

import model.Empresa
import java.sql.*

class EmpresaDAO implements Repositorio<Empresa> {

    private Connection conexao

    EmpresaDAO() {
        try {

            this.conexao = DatabaseConnection.getInstancia()


        } catch (Exception e) {
            println "⚠️ ERRO GRAVE: Não foi possível iniciar a conexão no DAO!"
            e.printStackTrace()
        }
    }

    @Override
    void salvar(Empresa empresa) {
        try {
            String sql = """
                INSERT INTO empresas 
                (nome, cnpj, email, descricao, pais, cep, senha) 
                VALUES (?, ?, ?, ?, ?, ?, ?) 
                RETURNING id
            """

            PreparedStatement stmt = this.conexao.prepareStatement(sql)

            stmt.setString(1, empresa.nome)
            stmt.setString(2, empresa.cnpj)
            stmt.setString(3, empresa.email)
            stmt.setString(4, empresa.descricao)
            stmt.setString(5, empresa.pais)
            stmt.setString(6, empresa.cep)
            stmt.setString(7, empresa.senha)

            ResultSet rs = stmt.executeQuery()

            if (rs.next()) {
                empresa.id = rs.getInt("id")
                println "✅ Empresa ${empresa.nome} salva com sucesso!"
            }
        } catch (SQLException e) {
            println "❌ Erro ao salvar empresa: " + e.message
            e.printStackTrace()
        }

    }

    @Override
    List<Empresa> listar() {
        List<Empresa> lista = []

        try {
            String sql = "SELECT * FROM empresas"

            PreparedStatement stmt = this.conexao.prepareStatement(sql)
            ResultSet rs = stmt.executeQuery()

            while (rs.next()) {
                Empresa emp = new Empresa(
                        id: rs.getInt("id"),
                        nome: rs.getString("nome"),
                        cnpj: rs.getString("cnpj"),
                        email: rs.getString("email"),
                        pais: rs.getString("pais"),
                        cep: rs.getString("cep"),
                        descricao: rs.getString("descricao")
                )
                lista.add(emp)
            }
        } catch (SQLException e) {
            println "❌ Erro ao listar empresas: " + e.message
            e.printStackTrace()
        }

        return lista
    }
}