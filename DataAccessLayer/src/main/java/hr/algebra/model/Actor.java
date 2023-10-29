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
public class Actor {

    @XmlElement(name = "idactor")
    private int idActor;
    @XmlElement(name = "actorfullname")
    private String actorFullName;

    public Actor() {
    }
    
    public Actor(String actorFullName) {
        this.actorFullName = actorFullName;
    }

    public Actor(int idActor, String actorFullName) {
        this.idActor = idActor;
        this.actorFullName = actorFullName;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getActorFullName() {
        return actorFullName;
    }

    public void setActorFullName(String actorFullName) {
        this.actorFullName = actorFullName;
    }

    @Override
    public String toString() {
        return actorFullName;
    }
}
