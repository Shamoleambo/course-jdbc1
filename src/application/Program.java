package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES (?, ?, ?, ?, ?)");
			st.setString(1, "Mano Maluco");
			st.setString(2, "mano@mail.com");
			st.setDate(3, new Date(
					LocalDate.of(1985, 2, 12).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()));
			st.setDouble(4, 45000.99);
			st.setInt(5, 1);

			int rowsAffected = st.executeUpdate();
			System.out.println("Done. Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

}
