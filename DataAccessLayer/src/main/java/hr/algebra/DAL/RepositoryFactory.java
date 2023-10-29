/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.DAL;

import hr.algebra.DAL.SQL.DataSourceSingelton;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo
 */
public class RepositoryFactory {

    private static final String PATH = "/config/RepositoryFactoryInformation.properties";
    private static final String CLASS_NAME = "CLASS_NAME";

    private RepositoryFactory() {
    }

    private static final Properties properties = new Properties();
    private static Repository repository;

    static {
        try (InputStream is = DataSourceSingelton.class
                .getResourceAsStream(PATH);) {
            properties.load(is);
            repository = (Repository) Class.forName(properties.getProperty(CLASS_NAME))
                    .getDeclaredConstructor()
                    .newInstance();
        } catch (Exception ex) {
            Logger.getLogger(DataSourceSingelton.class.getName()).log(Level.SEVERE, "Problem with Repository Factory");
        }

    }

    public static Repository getRepository() {
        return repository;
    }
}
