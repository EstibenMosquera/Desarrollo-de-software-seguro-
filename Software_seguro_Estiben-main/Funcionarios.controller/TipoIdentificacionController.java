
package com.iudigital.func.center.controller;

import com.iudigital.func.center.dao.TipoIdentificacionDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import com.iudigital.func.center.model.TipoIdentificacion;



/**
 *
 * @author Eliath
 */
public class TipoIdentificacionController {
    
    private TipoIdentificacionDao tipoIdentificacionDao;

    public TipoIdentificacionController(TipoIdentificacionDao tipoIdentificacionDao) {
        this.tipoIdentificacionDao = tipoIdentificacionDao;
    }
    
    public DefaultComboBoxModel llenarSelector(){
        DefaultComboBoxModel selectorTipoID = new DefaultComboBoxModel();
        List<TipoIdentificacion> tiposID = tipoIdentificacionDao.findAll();
        for(TipoIdentificacion elem: tiposID){
            selectorTipoID.addElement(elem.getNombre());
        }
        return selectorTipoID;
    }
}
