package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public Connection getConexao() {
        try {
            // Conectando ao banco "escola" em vez de "bdaula01"
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/escola?useTimezone=true&serverTimezone=UTC",
                "root", "laboratorio" // Altere para a senha do seu lab, se necessário
            );
            System.out.println("Conexão realizada com sucesso!");
            return conn;
        } catch (Exception e) {
            System.out.println("Erro ao conectar no BD: " + e.getMessage());
            return null;
        }
    }
}