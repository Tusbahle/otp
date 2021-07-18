package util;

import javax.swing.*;
import java.awt.print.Book;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConn {

	private static DatabaseConn databaseConn = null;

	private static final String DB_URL = "jdbc:derby:database;create=true";
	private static Connection conn = null;
	private static Statement stmt = null;

	private DatabaseConn() {
		createConnection();
		setupUserTable();
	}

	public static DatabaseConn getInstance() {
		if (databaseConn == null) {
			databaseConn = new DatabaseConn();
		}
		return databaseConn;
	}

	public static Connection getConnection() {
		if (conn == null) {
			new DatabaseConn();
		}
		return conn;
	}

	void createConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
		} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
		}
	}

	void setupUserTable() {
		String TABLE_NAME = "users";
		try {
			stmt = conn.createStatement();

			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toLowerCase(), null);

			if (tables.next()) {
				System.out.println("Table " + TABLE_NAME + " is ready.");
			} else {
				stmt.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "	id  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
						+ "	username VARCHAR(200),\n" + "	password VARCHAR(200),\n" + "	mobile VARCHAR(100),\n"
						+ "	otpCode VARCHAR(100) DEFAULT NULL,\n email VARCHAR(100) DEFAULT NULL,\n"
						+ "	isActive boolean DEFAULT true" + ")");
				System.out.println("User table has been created");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "Database Error");
		} finally {
		}
	}

//    public boolean deleteBook(listBookController.Book book) {
//        try {
//            String deleteStatement = "DELETE FROM BOOK WHERE ID = ?";
//            PreparedStatement stmt = conn.prepareStatement(deleteStatement);
//            stmt.setString(1, book.getId());
//            int  res = stmt.executeUpdate();
//            if (res == 1) {
//                return true;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

//    public boolean isBookAlreadyIssued( book) {
//        try {
//            String checkstmt = "SELECT COUNT(*) FROM ISSUE WHERE bookID=?";
//            PreparedStatement stmt = conn.prepareStatement(checkstmt);
//            stmt.setString(1, book.getId());
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                int count = rs.getInt(1);
//                System.out.println(count);
//                return (count > 0);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseConn.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }

	public ResultSet execQuery(String query) {
		ResultSet result;
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
		} catch (SQLException ex) {
			System.out.println(ex.getLocalizedMessage());
			return null;
		} finally {
		}
		return result;
	}

	public boolean execAction(String qu) {
		try {
			stmt = conn.createStatement();
			stmt.execute(qu);
			return true;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occurred",
					JOptionPane.ERROR_MESSAGE);
			System.out.println(ex.getLocalizedMessage());
			return false;
		} finally {
		}
	}
}