/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.Utilities;

import hr.algebra.factory.UrlConnectionFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Leonardo
 */
public class FileUtils {
    
    private static final String UPLOAD = "Upload image";

    public static File uploadFile(String description, String... extensions) {
        JFileChooser chooser = new JFileChooser(
                FileSystemView.getFileSystemView()
                        .getHomeDirectory()
        );
        chooser.setFileFilter(new FileNameExtensionFilter(
                description, extensions));
        chooser.setDialogTitle(UPLOAD);
        chooser.setApproveButtonText(UPLOAD);
        chooser.setApproveButtonToolTipText(UPLOAD);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            File file = chooser.getSelectedFile();
            String ext = file.getName()
                    .substring(file.getName().lastIndexOf(".") + 1);

            return file.exists() && Arrays.asList(extensions).contains(ext)
                    ? file
                    : null;
        }
        return null;
    }

    public static void copy(String source, String destination) throws IOException {
        createDirectory(destination);
        Files.copy(Paths.get(source), Paths.get(destination));
    }

    private static void createDirectory(String destination) throws IOException {
        String direktori = destination.substring(0, destination.lastIndexOf(
                File.separator));
        if (!Files.exists(Paths.get(direktori))) {
            Files.createDirectories(Paths.get(direktori));
        }
    }

    public static void copyFromUrl(String url, String dst) throws IOException {
        createDirectory(dst);
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(url);
        try (InputStream is = con.getInputStream();) {
            Files.copy(is, Paths.get(dst));
        }
    }
}
