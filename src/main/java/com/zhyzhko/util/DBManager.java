package com.zhyzhko.util;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * Created by user on 03.07.17.
 */
public class DBManager {
    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();
    private static DataSource dataSource;

    static {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/InitialTaskDB");
        } catch (NamingException e) {
            LOG.error("Can't get DataSource from JNDI", e);
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionThreadLocal.get();
            if (connection != null && !connection.isClosed()) {
                LOG.info("Connection exists. Return existed connection. IsClosed: " + connection.isClosed()
                        + ". HashCode: " + connection.hashCode());
                return connection;
            }
            LOG.info("Connection doesn't exists, take connection from DataSource.");
            connection = dataSource.getConnection();
            connectionThreadLocal.set(connection);

        } catch (SQLException e) {
            LOG.error("Can't get connection from DataSource", e);
        }
        return connection;
    }

    public static void commit(Connection con) {
        try {
            if (con != null) {
                con.commit();
            }
        } catch (SQLException e) {
            LOG.error("Can't commit", e);
        }
    }

    public static void rollback(Connection con) {
        try {
            if (con != null) {
                con.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Can't rollback", e);
        }
    }

    public static void closeCon(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            LOG.error("Can't close connection", e);
        }
    }

    public static void closeRs(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOG.error("Can't close ResultSet", e);
        }
    }

    public static void closeStat(Statement stat) {
        try {
            if (stat != null) {
                stat.close();
            }
        } catch (SQLException e) {
            LOG.error("Can't close Statement", e);
        }
    }
}
