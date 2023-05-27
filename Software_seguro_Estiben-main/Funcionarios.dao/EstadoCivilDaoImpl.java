
package com.iudigital.func.center.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.iudigital.func.center.model.Conexion;
import com.iudigital.func.center.model.EstadoCivil;
import com.iudigital.func.center.utils.Mensajes;

/**
 *
 * @author Eliath
 */
public class EstadoCivilDaoImpl implements EstadoCivilDao {

    Conexion conexion;
    private Statement query;
    private ResultSet result;

    @Override
    public List<EstadoCivil> findAll() {
        List<EstadoCivil> tipoEstado = new ArrayList<>();
        try {
            String sql = "SELECT * FROM estados_civiles";
            conexion = new Conexion();
            query = conexion.getCon().createStatement();
            result = query.executeQuery(sql);
            while (result.next()) {
                EstadoCivil estadoCivil = new EstadoCivil(result.getInt("id"), result.getString("nombre"));
                tipoEstado.add(estadoCivil);
            }
            query.close();
            result.close();
            conexion.getCon().close();
        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }
        return tipoEstado;
    }

}
