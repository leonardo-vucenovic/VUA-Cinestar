/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author Leonardo
 */
public class User {

    private int idUser;
    private String userName;
    private String password;
    private String roleType;

    public User(String userName, String password, String roleType) {
        this.userName = userName;
        this.password = password;
        this.roleType = roleType;
    }

    public User(int idUser, String userName, String password, String roleType) {
        this.idUser = idUser;
        this.userName = userName;
        this.password = password;
        this.roleType = roleType;
    }

    public int getiUser() {
        return idUser;
    }

    public void setiUser(int iUser) {
        this.idUser = iUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return idUser + userName;
    }
}
