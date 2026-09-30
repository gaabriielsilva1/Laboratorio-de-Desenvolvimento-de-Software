package dao;

import beans.Aluno;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Conexao conexao;
    private Connection conn;

    public AlunoDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Aluno aluno) {
        if (this.conn == null) {
            System.out.println("Falha na conexão com o banco de dados.");
            return;
        }

        String sql = "INSERT INTO aluno (nome, idade, curso) VALUES (?, ?, ?);";
        try (PreparedStatement stmt = this.conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdade());
            stmt.setString(3, aluno.getCurso());
            stmt.execute();
            System.out.println("Aluno cadastrado com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir aluno: " + ex.getMessage());
        }
    }

    // MÉTODO PARA CONSULTAR OS DADOS DO BANCO
    public List<Aluno> listar() {
        List<Aluno> lista = new ArrayList<>();
        
        if (this.conn == null) {
            System.out.println("Falha na conexão com o banco de dados.");
            return lista;
        }

        String sql = "SELECT * FROM aluno";
        
        try (PreparedStatement stmt = this.conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setIdade(rs.getInt("idade"));
                a.setCurso(rs.getString("curso"));
                lista.add(a);
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao listar alunos: " + ex.getMessage());
        }
        return lista;
    }
}