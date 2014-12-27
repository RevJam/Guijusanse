package com.mygdx.game.dataBase;

import com.badlogic.gdx.Gdx;

import java.sql.*;
import java.util.logging.Logger;

/**
 * Created by sebastien on 26/12/14.
 */
public class ConnectionFactory extends Exception {
    /**
     * le driver de la bd
     */
    private static final String DRIVER = "SQLite.JDBCDriver";
    /**
     * le login de connection a la bdd
     */
    private static final String DATABASE_USERNAME = "admin";
    /**
     * le mot de passe de la connection à la bdd
     */
    private static final String DATABASE_PASSWORD = "admin";
    /**
     * le nom de la BDD
     */
    private static final String DATABASE_NAME = "guijusanseBase";
    /**
     * l'url de la bdd
     */
    private static final String DATABASE_URL = "jdbc:sqlite:/sdcard/" + DATABASE_NAME;
    /**
     * instance pointant sur elle meme
     */
    private static ConnectionFactory instance;

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

    static {
        try {
            instance = new ConnectionFactory();
        } catch (SQLException e) {
            LOGGER.finest(ConnectionFactory.class.getName() + " erreur connectionFactory\""+ e);
        }
    }

    public ConnectionFactory() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.finest(ConnectionFactory.class.getName() + "ERROR: Unable to load database driver.\n" + e);
        }
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            LOGGER.finest(ConnectionFactory.class.getName() + "ERROR: createConnection.\n" + e);
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.finest(ConnectionFactory.class.getName() + "ERROR: close connection.\n" + e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.finest(ConnectionFactory.class.getName() + "ERROR: close statement.\n" + e);
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.finest(ConnectionFactory.class.getName() + "ERROR: close resultSet.\n" + e);
            }
        }
    }
}