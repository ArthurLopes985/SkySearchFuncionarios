package DAO;

import Model.Funcionarios;
import Util.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosDAO {

    public void login(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("select * FROM funcionario WHERE Id = ?");
        comando.setInt(1, f.getId());
        ResultSet rs = comando.executeQuery();

        String senha = null;
        if (rs.next()) {
            f.setSenha(rs.getString("Senha"));
            f.setNome(rs.getString("nome"));
        }
        rs.close();
        comando.close();
        con.close();
    }

    public boolean verificacpf(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("select * FROM funcionario WHERE cpf = ?");
        comando.setString(1, f.getCpf());
        ResultSet rs = comando.executeQuery();
        boolean aux = rs.next();
        rs.close();
        con.close();
        comando.close();
        return aux;
    }

    public void cadastrar(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL Cadastrar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        comando.setString(1, f.getNome());
        comando.setString(2, f.getSenha());
        comando.setString(3, f.getCpf());
        comando.setString(4, f.getEmail());
        comando.setString(5, f.getTelefone());
        comando.setString(6, f.getGenero());
        comando.setDate(7, java.sql.Date.valueOf(f.getData_nasc()));
        comando.setDate(8, java.sql.Date.valueOf(f.getData_admissao()));
        comando.setString(9, f.getSetor());
        comando.setString(10, f.getCargo());
        comando.setDouble(11, f.getSalario());
        comando.setString(12, f.getEndereco());
        comando.execute();
        PreparedStatement comando2 = con.prepareStatement("select Id from Funcionario where cpf = ?");
        comando2.setString(1, f.getCpf());
        ResultSet rs = comando2.executeQuery();
        if (rs.next()) {
            f.setId(rs.getInt("Id"));
        }
        rs.close();
        con.close();
    }

    public void atualizar(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL Editar(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        comando.setInt(1, f.getId());
        comando.setString(2, f.getNome());
        comando.setString(3, f.getSenha());
        comando.setString(4, f.getEmail());
        comando.setString(5, f.getTelefone());
        comando.setString(6, f.getGenero());
        comando.setDate(7, java.sql.Date.valueOf(f.getData_nasc()));
        comando.setDate(8, java.sql.Date.valueOf(f.getData_admissao()));
        comando.setString(9, f.getSetor());
        comando.setString(10, f.getCargo());
        comando.setDouble(11, f.getSalario());
        comando.setString(12, f.getEndereco());
        comando.execute();
        con.close();
    }

    public void deletar(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        CallableStatement comando = con.prepareCall("{CALL Apagar(?)}");
        comando.setInt(1, f.getId());
        comando.execute();
        con.close();
    }
    
    public Funcionarios PreencherPorID(int id) throws ClassNotFoundException, SQLException {
    Connection con = ConnectionFactory.getConexao();
    PreparedStatement comando = con.prepareStatement("SELECT * FROM Funcionario WHERE id = ?");
    comando.setInt(1, id);
    ResultSet rs = comando.executeQuery();

    if (rs.next()) {
        Funcionarios fun = new Funcionarios();
        fun.setId(rs.getInt("id"));
        fun.setNome(rs.getString("nome"));
        fun.setCpf(rs.getString("cpf"));
        fun.setEmail(rs.getString("email"));
        fun.setTelefone(rs.getString("telefone"));
        fun.setGenero(rs.getString("genero"));
        fun.setData_nasc(rs.getObject("data_nasc", LocalDate.class));
        fun.setData_admissao(rs.getObject("data_admissao", LocalDate.class));
        fun.setSetor(rs.getString("setor"));
        fun.setCargo(rs.getString("cargo"));
        fun.setSalario(rs.getDouble("salario"));
        fun.setEndereco(rs.getString("endereco"));
        return fun;
    } else {
        return null;
    }
}


    public List<Funcionarios> consultarPorID(Funcionarios f) throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from Funcionario WHERE id = ?");
        comando.setInt(1, f.getId());
        ResultSet rs = comando.executeQuery();

        List<Funcionarios> listaid = new ArrayList<Funcionarios>();
        while (rs.next()) {
            Funcionarios fun = new Funcionarios();
            fun.setId(rs.getInt("Id"));
            fun.setNome(rs.getString("nome"));
            fun.setCpf(rs.getString("cpf"));
            fun.setEmail(rs.getString("email"));
            fun.setTelefone(rs.getString("telefone"));
            fun.setGenero(rs.getString("genero"));
            fun.setData_nasc(rs.getObject("data_nasc", LocalDate.class));
            fun.setData_admissao(rs.getObject("data_admissao", LocalDate.class));
            fun.setSetor(rs.getString("setor"));
            fun.setCargo(rs.getString("cargo"));
            fun.setSalario(rs.getDouble("salario"));
            fun.setEndereco(rs.getString("endereco"));
            listaid.add(fun);
        }
        return listaid;
    }

    public List<Funcionarios> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = ConnectionFactory.getConexao();
        PreparedStatement comando = con.prepareStatement("select * from Funcionario");
        ResultSet rs = comando.executeQuery();

        List<Funcionarios> lista = new ArrayList<Funcionarios>();
        while (rs.next()) {
            Funcionarios fun = new Funcionarios();
            fun.setId(rs.getInt("Id"));
            fun.setNome(rs.getString("nome"));
            fun.setCpf(rs.getString("cpf"));
            fun.setEmail(rs.getString("email"));
            fun.setTelefone(rs.getString("telefone"));
            fun.setGenero(rs.getString("genero"));
            fun.setData_nasc(rs.getObject("data_nasc", LocalDate.class));
            fun.setData_admissao(rs.getObject("data_admissao", LocalDate.class));
            fun.setSetor(rs.getString("setor"));
            fun.setCargo(rs.getString("cargo"));
            fun.setSalario(rs.getDouble("salario"));
            fun.setEndereco(rs.getString("endereco"));
            lista.add(fun);
        }
        return lista;
    }
}
