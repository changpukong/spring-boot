package yfu.practice.springboot.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 產生DB初始Script
 * @author yfu
 */
public class GenInitScript {

	/** DB URL */
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

	/** DB帳號 */
	private static final String USER = "DAVID";

	/** DB密碼 */
	private static final String PASSWORD = "123456";

	/** 資料夾路徑 */
	private static final String DIR_PATH = "D:/";

	/** 匯入檔案清單 */
	private static final List<String> IMPORT_FILES = Arrays.asList("EMPLOYEE.txt", "EMPLOYEE.txt", "EMPLOYEE.txt", "EMPLOYEE.txt");
	
	/** 匯出檔案 */
	private static final String EXPORT_FILE = "initScript.sql";
	
	/** DB連線 */
	private Connection conn;

	public static void main(String[] args) throws Exception {
		new GenInitScript().execute();
	}
	
	private void execute() throws IOException, SQLException {
		BufferedReader br = null;
		try (PrintWriter pw = new PrintWriter(
				Files.newBufferedWriter(Paths.get(DIR_PATH, EXPORT_FILE), StandardOpenOption.CREATE))) {

			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
			for (String fileName : IMPORT_FILES) {
				String tableName = fileName.split("\\.")[0];

				br = Files.newBufferedReader(Paths.get(DIR_PATH, fileName));
				String querySql = getQuerySql(tableName, br.readLine());
				System.out.println("Query SQL: " + querySql);
				
				pw.println("-- " + fileName);
				br.lines()
					.flatMap(e -> getInsertSqls(tableName, querySql, e).stream())
					.peek(System.out::println)
					.forEach(pw::println);
				pw.println();
				System.out.println();
				
				br.close();
			}
		} finally {
			if (br != null) {
				br.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}
	
	/**
	 * 取得Query SQL
	 * @param tableName
	 * @param condition
	 * @return
	 */
	private String getQuerySql(String tableName, String condition) {
		StringBuilder sb = new StringBuilder()
				.append("SELECT * FROM ").append(tableName)
				.append(" WHERE 1 = 1");
		
		String[] columns = condition.split(",");
		for (String column : columns) {
			sb.append(" AND ").append(column).append(" = ?");
		}
		
		return sb.toString();
	}
	
	/**
	 * 取得Insert SQL
	 * @param tableName
	 * @param querySql
	 * @param condition
	 * @return
	 */
	private List<String> getInsertSqls(String tableName, String querySql, String condition) {
		try (PreparedStatement pstmt = conn.prepareStatement(querySql)) {
			// 設值
			String[] values = condition.split(",");
			for (int i = 0; i < values.length; i++) {
				pstmt.setString(i + 1, values[i]);
			}
			
			// 查詢
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int nColumn = metaData.getColumnCount();
			
			// 組第一段SQL
			StringBuilder sb = new StringBuilder().append("INSERT INTO ").append(tableName).append(" (");
			for (int i = 1; i <= nColumn; i++) {
				sb.append(metaData.getColumnName(i)).append(i == nColumn ? ")" : ",");
			}
			String insertSqlSection1 = sb.append(" VALUES (").toString();
			
			List<String> insertSqlList = new ArrayList<>();
			while (rs.next()) {
				sb.setLength(0);
				// 組第二段SQL
				for (int i = 1; i <= nColumn; i++) {
					String columnType = metaData.getColumnTypeName(i);
					Object value = rs.getObject(i);
					sb.append(getValueStr(columnType, value)).append(i == nColumn ? ");" : ",");
				}
				insertSqlList.add(sb.insert(0, insertSqlSection1).toString());
			}
			
			return insertSqlList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 取得各欄的Insert語句value字串
	 * @param columnType
	 * @param value
	 * @return
	 */
	private String getValueStr(String columnType, Object value) {
		switch (columnType) {
			case "CHAR":
			case "NCHAR":
			case "VARCHAR2":
			case "NVARCHAR2":
			case "CLOB":
				return new StringBuilder().append('\'').append(value).append('\'').toString();
			case "DATE":
			case "TIMESTAMP":
				return new StringBuilder().append("TO_TIMESTAMP('").append(value)
						.append("','yyyy-mm-dd hh24:mi:ss.ff')").toString();
			case "NUMBER":
				return ((BigDecimal) value).toPlainString();
			default:
				return value.toString();
		}
	}

}
