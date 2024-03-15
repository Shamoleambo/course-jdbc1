package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			/*
			st = conn.prepareStatement(
					"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)" + "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Truta Humano");
			st.setString(2, "truta@mail.com");
			st.setDate(3, new Date(
					LocalDate.of(1994, 7, 18).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()));
			st.setDouble(4, 200000.98);
			st.setInt(5, 1);
			*/
			
			st = conn.prepareStatement("INSERT INTO department (Name) VALUES ('Amor'), ('Paz')", Statement.RETURN_GENERATED_KEYS);
			
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done. Id = " + id);
				}
			} else
				System.out.println("No rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
