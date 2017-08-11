package com.zhyzhko.util;

import org.apache.log4j.Logger;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * Created by user on 03.07.17.
 */
public class TransactionProxyManager {

    private static final Logger LOG = Logger.getLogger(TransactionProxyManager.class);

    @SuppressWarnings(value = "unchecked")
    public static <T> T getTransactionalWrapper(Object object) {
        return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(), (proxy, method, args) -> {

                    Connection con = DBManager.getConnection();
                    LOG.info("Invoke transaction handler. Connection isClosed: " + con.isClosed()
                            + ". Connection hashCode: " + con.hashCode());

                    T result = null;

                    try {
                        LOG.info("Begin of transaction executing");
                        result = (T) method.invoke(object, args);

                        DBManager.commit(con);
                        LOG.info("Transaction executed successfully");

                    } catch (Throwable th) {
                        DBManager.rollback(con);
                        LOG.error("Errors during executing transaction. Initiate rollback", th);
                        throw th;

                    } finally {
                        DBManager.closeCon(con);
                        LOG.info("Close connection");
                    }
                    return result;
                });
    }
}
