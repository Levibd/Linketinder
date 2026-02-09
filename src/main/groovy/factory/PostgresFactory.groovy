package factory

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class PostgresFactory implements ConnectionFactory {


    String url = "jdbc:postgresql://localhost:5432/postgres";
    String usuario = "admin";
    String senha = "password123";

    @Override
    Connection criarConexao() {
        try {
            return DriverManager.getConnection(url, usuario, senha)
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar conexão Postgres: " + e.message)
        }
    }
}

