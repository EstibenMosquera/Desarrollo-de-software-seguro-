
package com.iudigital.func.center.dao;

import java.util.List;
import com.iudigital.func.center.model.Funcionario;

/**
 *
 * @author Eliath
 */
public interface FuncionarioDao {
 
    //Se declaran los métodos del CRUD
    List<Funcionario> findAll();
    
    Funcionario findByDocumento(String documento);
    
    int save(Funcionario funcionario);
    
    int update(Funcionario funcionario);
    
    void delete(String documento);
}
