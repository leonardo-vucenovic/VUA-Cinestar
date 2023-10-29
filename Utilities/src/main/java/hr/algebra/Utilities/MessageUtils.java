/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.Utilities;

import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class MessageUtils {

    private MessageUtils() {
    }

    public static void ShowInformationMessage(String title, String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void ShowErrornMessage(String title, String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                title,
                JOptionPane.ERROR_MESSAGE);
    }

    public static boolean ShowConfirmDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE)
                == JOptionPane.OK_OPTION;
    }

}
