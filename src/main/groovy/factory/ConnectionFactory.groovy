package factory

import java.sql.Connection

interface ConnectionFactory {
    Connection criarConexao()
}
