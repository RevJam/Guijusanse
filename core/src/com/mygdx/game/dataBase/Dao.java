package com.mygdx.game.dataBase;

import com.badlogic.gdx.Gdx;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by sebastien on 26/12/14.
 */
public class Dao extends DataBaseMaker{

    private static ConnectionFactory connectionFactory = null;
    private static Statement statement  = null;
    private static ResultSet resultSet  = null;
    private static Connection connection = null;

    private static final Logger LOGGER = Logger.getLogger(Dao.class.getName());
    /**
     * Execute a query to create or drop a table in the database
     * @param query query to execute
     */
    protected static void createDrop(String query) {
        LOGGER.finest(Dao.class.getName() + "debut: createDrop.\n");
        try {
            // Creation de la connexion à la bdd
            Connection connection= ConnectionFactory.getConnection();
            // creation de  la requête
            statement  = connection.createStatement();
            statement.execute(query);
        } catch(SQLException e) {
            LOGGER.finest(Dao.class.getName() + "erreur: createDrop.\n" + e);
        }
    }
    /**
     * Execute a query to add an element in the database
     * @param query query to execute
     * @return      generated id
     */
    protected static int add(String query) {
        LOGGER.finest(Dao.class.getName() + "debut: add.\n");
        int ret = -1;
        try {
            // Creation de la connexion à la bdd
            Connection connection = connectionFactory.getConnection();
            // creation de  la requête
            statement  = connection.createStatement();
            statement.executeUpdate(query);

            resultSet  = statement.getGeneratedKeys();
            if (resultSet.next()){
                ret = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.finest(Dao.class.getName() + "erreur: add.\n"+e);
        } finally {
            LOGGER.finest(Dao.class.getName() + "fin: close toutes les connections add.\n");
            connectionFactory.close(resultSet);
            connectionFactory.close(statement);
            connectionFactory.close(connection);
        }
        LOGGER.finest(Dao.class.getName() + "fin: add.\n");
        return ret;
    }
    /**
     * Execute a query to delete or update an element from the database
     * @param query query to execute
     * @return      number of rows affected
     */
    protected static int deleteUpdate(String query) {
        int ret = -1;
        try {
            Connection connection = connectionFactory.getConnection();
            // creation de  la requête
            statement  = connection.createStatement();
            ret = statement.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.finest(Dao.class.getName() +"ERROR: Unable to delete or update element: " + query + "\n" + e);
        } finally {
            connectionFactory.close(resultSet);
            connectionFactory.close(statement);
            connectionFactory.close(connection);
        }
        return ret;
    }

    /**
     * Execute a query to get an element from the database
     * @param query query to execute
     * @return      Object array containing element's informations
     */
    protected static Object[] getUniqueElement(String query, int nbColumns) {
        Object[] object = null;
        try {

            // Creation de la connexion à la bdd
            Connection connection= ConnectionFactory.getConnection();
            // creation de  la requête
            statement  = connection.createStatement();

            resultSet  = statement.executeQuery(query);
            // S'il y a un enregistrement correspondant à la requête
            if(resultSet.next()) {
                // Placer toutes ses informations dans object[]
                object = new Object[nbColumns];
                for(int i = 0; i < nbColumns; i++){
                    object[i] = resultSet.getObject(i+1);
                }
            }
        } catch (SQLException e) {
            LOGGER.finest(Dao.class.getName() +"ERROR: Unable to get element: " + query + "\n" + e);
        } finally {
            connectionFactory.close(resultSet);
            connectionFactory.close(statement);
            connectionFactory.close(connection);
        }
        return object;
    }

    /**
     * Executre a query to get all elements from the database
     * @param query query to execute
     * @return      List of object array containig element's informations
     */
    protected static List<Object[]> getSeveralsElements(String query, int nbColumns) {
        List<Object[]> list = new ArrayList<Object[]>();
        Object[] object;
        try {
            // Creation de la connexion à la bdd
            Connection connection= ConnectionFactory.getConnection();
            // creation de  la requête
            statement  = connection.createStatement();

            resultSet  = statement.executeQuery(query);
            // Pour chaque enregistrement
            while(resultSet.next()) {
                // Placer toutes ses informations dans object[]
                object = new Object[nbColumns];
                for(int i = 0; i < nbColumns; i++) {
                    object[i] = resultSet.getObject(i+1);
                }
                list.add(object);
            }
        } catch (SQLException e) {
            LOGGER.finest(Dao.class.getName() +"ERROR: Unable to get all elements: " + query + "\n" + e);
        } finally {
            connectionFactory.close(resultSet);
            connectionFactory.close(statement);
            connectionFactory.close(connection);
        }
        return list;
    }
}
