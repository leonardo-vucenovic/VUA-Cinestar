/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Director {

    @XmlElement(name = "iddirector")
    private int idDirector;
    @XmlElement(name = "directorfullname")
    private String directorFullName;
    
    public Director() {
    }

    public Director(String directorFullName) {
        this.directorFullName = directorFullName;
    }

    public Director(int idDirector, String directorFullName) {
        this.idDirector = idDirector;
        this.directorFullName = directorFullName;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public String getDirectorFullName() {
        return directorFullName;
    }

    public void setDirectorFullName(String directorFullName) {
        this.directorFullName = directorFullName;
    }

    @Override
    public String toString() {
        return idDirector + directorFullName;
    }

}
