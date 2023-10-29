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
public class Genre {

    @XmlElement(name = "idgenre")
    private int idGenre;
    @XmlElement(name = "genrefullname")
    private String genreFullName;
    
    public Genre() {
    }

    public Genre(String genreFullName) {
        this.genreFullName = genreFullName;
    }

    public Genre(int idGenre, String genreFullName) {
        this.idGenre = idGenre;
        this.genreFullName = genreFullName;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenreFullName() {
        return genreFullName;
    }

    public void setGenreFullName(String genreFullName) {
        this.genreFullName = genreFullName;
    }

    @Override
    public String toString() {
        return idGenre + genreFullName;
    }

}
