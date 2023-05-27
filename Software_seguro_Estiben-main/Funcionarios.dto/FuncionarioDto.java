
package com.iudigital.func.center.dto;

import java.time.LocalDate;
import com.iudigital.func.center.model.Funcionario;

/**
 *
 * @author Eliath
 */
public class FuncionarioDto extends Funcionario{

    private String estadoCivil;
    private String tipoId;

    public FuncionarioDto(){
        
    }
    
    public FuncionarioDto(int id, String numeroIdentificacion, String nombres, String apellidos, String sexo, String direccion, String telefono, LocalDate fechaNacimiento, String estadoCivil, String tipoId) {
        super(id, numeroIdentificacion, nombres, apellidos, sexo, direccion, telefono, fechaNacimiento);
        this.estadoCivil = estadoCivil;
        this.tipoId = tipoId;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }
    
}
