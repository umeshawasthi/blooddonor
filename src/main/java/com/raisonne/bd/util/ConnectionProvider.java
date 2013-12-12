package com.raisonne.bd.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {

public abstract Connection getConnection() throws SQLException;

public abstract void shutdown() throws SQLException;
}
