
package com.iudigital.func.center.dao;

import com.iudigital.func.center.dto.FuncionarioDto;
import com.iudigital.func.center.dto.FuncionarioService;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.iudigital.func.center.model.Conexion;
import com.iudigital.func.center.model.EstadoCivil;
import com.iudigital.func.center.model.Funcionario;
import com.iudigital.func.center.model.TipoIdentificacion;
import com.iudigital.func.center.utils.Mensajes;

/**
 *
 * @author Eliath
 */
public class FuncionarioDaoImpl implements FuncionarioDao {

    private FuncionarioService funcionarioService;
    private Conexion conexion;
    private ResultSet result;
    private PreparedStatement preparedQuery;

    @Override
    public List<Funcionario> findAll() {
        funcionarioService = new FuncionarioService();
        List<Funcionario> funcionarios = new ArrayList<>();
        List<FuncionarioDto> funcionariosDto = funcionarioService.buscarFuncionarios();
        for (FuncionarioDto f : funcionariosDto) {
            Funcionario funcionario = new Funcionario(f.getId(), f.getNumeroIdentificacion(),
                    f.getNombres(), f.getApellidos(), f.getSexo(), f.getDireccion(), f.getTelefono(),
                    f.getFechaNacimiento(), new EstadoCivil(f.getEstadoCivil()), new TipoIdentificacion(f.getTipoId()));
            funcionarios.add(funcionario);
        }
        return funcionarios;
    }

    @Override
    public Funcionario findByDocumento(String documento) {

        Funcionario funcionario = new Funcionario();
        String sql = "SELECT * FROM funcionarios WHERE "
                + "numero_identificacion=?";
        try {
            conexion = new Conexion();
            preparedQuery = conexion.getCon().prepareStatement(sql);
            preparedQuery.setString(1, documento);
            result = preparedQuery.executeQuery();

            if (result.first()) {
                funcionario.setNumeroIdentificacion(result.getString("numero_identificacion"));
                funcionario.setNombres(result.getString("nombres"));
                funcionario.setApellidos(result.getString("apellidos"));
                funcionario.setSexo(result.getString("sexo"));
                funcionario.setDireccion(result.getString("direccion"));
                funcionario.setTelefono(result.getString("telefono"));
                funcionario.setFechaNacimiento(LocalDate.parse(result.getString("fecha_nacimiento")));
                funcionario.setTipoIdentificacionId(new TipoIdentificacion(result.getInt("tipos_identificaciones_id")));
                funcionario.setEstadoCivilId(new EstadoCivil(result.getInt("estados_civiles_id")));
            }

            preparedQuery.close();
            result.close();
            conexion.getCon().close();

        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }
        return funcionario;
    }

    @Override
    public int save(Funcionario funcionario) {
        int res = 0;
        String sql = "INSERT INTO funcionarios(numero_identificacion,nombres,apellidos,sexo,direccion,telefono,fecha_nacimiento,"
                + "estados_civiles_id,tipos_identificaciones_id) "
                + "VALUES(?,?,?,?,?,?,?,?,?);";
        conexion = new Conexion();
        try {
            preparedQuery = conexion.getCon().prepareStatement(sql);
            preparedQuery.setString(1, funcionario.getNumeroIdentificacion());
            preparedQuery.setString(2, funcionario.getNombres());
            preparedQuery.setString(3, funcionario.getApellidos());
            preparedQuery.setString(4, funcionario.getSexo());
            preparedQuery.setString(5, funcionario.getDireccion());
            preparedQuery.setString(6, funcionario.getTelefono());
            preparedQuery.setString(7, funcionario.getFechaNacimiento().toString());
            preparedQuery.setInt(8, funcionario.getEstadoCivilId().getId());
            preparedQuery.setInt(9, funcionario.getTipoIdentificacionId().getId());

            res = preparedQuery.executeUpdate();
            preparedQuery.close();
            conexion.getCon().close();

        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }
        return res;
    }

    @Override
    public int update(Funcionario funcionario) {
        int res = 0;
        String sql = "UPDATE funcionarios "
                + "SET numero_identificacion=?, nombres=?, apellidos=?, "
                + "sexo=?, direccion=?, telefono=?, fecha_nacimiento=?, "
                + "estados_civiles_id=?,tipos_identificaciones_id=?, fecha_actualizacion=now() "
                + "WHERE numero_identificacion=?";
        conexion = new Conexion();
        try {
            preparedQuery = conexion.getCon().prepareStatement(sql);
            preparedQuery.setString(1, funcionario.getNumeroIdentificacion());
            preparedQuery.setString(2, funcionario.getNombres());
            preparedQuery.setString(3, funcionario.getApellidos());
            preparedQuery.setString(4, funcionario.getSexo());
            preparedQuery.setString(5, funcionario.getDireccion());
            preparedQuery.setString(6, funcionario.getTelefono());
            preparedQuery.setString(7, funcionario.getFechaNacimiento().toString());
            preparedQuery.setInt(8, funcionario.getEstadoCivilId().getId());
            preparedQuery.setInt(9, funcionario.getTipoIdentificacionId().getId());
            preparedQuery.setString(10, funcionario.getNumeroIdentificacion());

            res = preparedQuery.executeUpdate();
            preparedQuery.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }

        return res;
    }

    @Override
    public void delete(String documento) {

        String sql = "DELETE FROM funcionarios WHERE "
                + "numero_identificacion=?";
        conexion = new Conexion();
        try {
            preparedQuery = conexion.getCon().prepareStatement(sql);
            preparedQuery.setString(1, documento);
            preparedQuery.executeUpdate();

            preparedQuery.close();
            conexion.getCon().close();

        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }

    }

}
