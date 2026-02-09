package dao

import factory.ConnectionFactory
import factory.PostgresFactory
import java.sql.Connection
import java.sql.SQLException

class DatabaseConnection {


    private static Connection instancia


    private DatabaseConnection() {}


    static Connection getInstancia() {
        try {

            if (instancia == null || instancia.isClosed()) {

                ConnectionFactory fabrica = new PostgresFactory()

                instancia = fabrica.criarConexao()
                println "🔌 [Singleton] Nova conexão com banco criada."
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerenciar conexão Singleton: " + e.message)
        }

        return instancia
    }
}