
package com.iudigital.func.center.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.iudigital.func.center.model.Conexion;
import com.iudigital.func.center.utils.Mensajes;

/**
 *
 * @author Eliath
 */
public class FuncionarioService {

    private Conexion conexion;
    private Statement query;
    private ResultSet result;

    public List<FuncionarioDto> buscarFuncionarios() {
        List<FuncionarioDto> funcionariosDto = new ArrayList<>();
        try {

            String sql = """
                         SELECT funcionarios.*, estados_civiles.nombre EstadoCivil, tipos_identificaciones.nombre TipoID FROM funcionarios
                         INNER JOIN estados_civiles
                         ON funcionarios.estados_civiles_id=estados_civiles.id
                         INNER JOIN tipos_identificaciones
                         ON funcionarios.tipos_identificaciones_id=tipos_identificaciones.id""";
            conexion = new Conexion();
            query = conexion.getCon().createStatement();
            result = query.executeQuery(sql);

            while (result.next()) {
                FuncionarioDto funcionarioDto = new FuncionarioDto(
                        result.getInt("id"), result.getString("numero_identificacion"),
                        result.getString("nombres"), result.getString("apellidos"), result.getString("sexo"),
                        result.getString("direccion"), result.getString("telefono"),
                        LocalDate.parse(result.getString("fecha_nacimiento")), result.getString("EstadoCivil"),
                        result.getString("TipoID"));
                funcionariosDto.add(funcionarioDto);
            }

            query.close();
            result.close();
            conexion.getCon().close();

        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }
        return funcionariosDto;
    }

}
