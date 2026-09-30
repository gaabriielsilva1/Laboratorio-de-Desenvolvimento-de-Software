package dao;

import beans.Professor;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {
    private Conexao conexao;
    private Connection conn;

    public ProfessorDAO() {
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }

    public void inserir(Professor professor) {
        String sql = "INSERT INTO professores (nome, idade, disciplina) VALUES (?, ?, ?);";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, professor.getNome());
            stmt.setInt(2, professor.getIdade());
            stmt.setString(3, professor.getDisciplina());
            stmt.execute();
            System.out.println("Professor inserido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir professor: " + ex.getMessage());
        }
    }
    
    public List<beans.Professor> listar() {
        List<beans.Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professor";

        try {
            if (this.conn == null) return lista; // Prevenção de falha

            java.sql.PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                beans.Professor p = new beans.Professor();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setIdade(rs.getInt("idade"));
                p.setDisciplina(rs.getString("disciplina"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
        return lista;
    }
}