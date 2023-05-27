
package com.iudigital.func.center.controller;

import com.iudigital.func.center.dao.EstadoCivilDao;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import com.iudigital.func.center.model.EstadoCivil;

/**
 *
 * @author Eliath
 */
public class EstadoCivilController {

    private EstadoCivilDao estadoCivilDao;

    public EstadoCivilController(EstadoCivilDao estadoCivilDao) {
        this.estadoCivilDao = estadoCivilDao;
    }

    public DefaultComboBoxModel llenarSelector() {
        DefaultComboBoxModel selectorEstadosCiviles = new DefaultComboBoxModel();

        List<EstadoCivil> estadosCiviles = estadoCivilDao.findAll();
        for (EstadoCivil elem : estadosCiviles) {
            selectorEstadosCiviles.addElement(elem.getNombre());
        }
        return selectorEstadosCiviles;
    }

}
