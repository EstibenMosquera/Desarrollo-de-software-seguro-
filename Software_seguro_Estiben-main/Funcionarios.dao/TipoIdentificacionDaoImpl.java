
package com.iudigital.func.center.dao;

import java.util.ArrayList;
import java.util.List;
import com.iudigital.func.center.model.Conexion;
import com.iudigital.func.center.model.TipoIdentificacion;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.iudigital.func.center.utils.Mensajes;

/**
 *
 * @author Eliath
 */
public class TipoIdentificacionDaoImpl implements TipoIdentificacionDao {

    private Conexion conexion;
    private Statement query;
    private ResultSet result;

    @Override
    public List<TipoIdentificacion> findAll() {
        List<TipoIdentificacion> tiposID = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tipos_identificaciones";
            conexion = new Conexion();
            query = conexion.getCon().createStatement();
            result = query.executeQuery(sql);

            while (result.next()) {
                TipoIdentificacion tipoIdentificacion = new TipoIdentificacion(
                        result.getInt("id"), result.getString("nombre"), result.getString("descripcion"));
                tiposID.add(tipoIdentificacion);
            }
            query.close();
            result.close();
            conexion.getCon().close();

        } catch (SQLException ex) {
            Mensajes.mensajeError(ex.getMessage(), "Error De Base De Datos");
        }
        return tiposID;
    }

}
