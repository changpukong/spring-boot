package yfu.practice.springboot.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yfu.practice.springboot.jpa.entity.Card;

@Slf4j
@Service
public class TestIsolationSvc {

	@Autowired
	private DataSource dataSource;

	private static final String QUERY_SQL = "select * from YFU_CARD";

	private static final String UPDATE_SQL = "update YFU_CARD set TYPE = ?";
	
	/**
	 * 成功：2個交易互相獨立無重疊
	 * @throws SQLException
	 */
	public void scenario() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Connection conn2 = dataSource.getConnection();) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			query(conn);			// Transaction1 開始
			update(conn, "B");
			conn.commit();			// Transaction1 結束
			query(conn);

			query(conn2);			// Transaction2 開始
			update(conn2, "C");	
			conn2.commit();			// Transaction2 結束
			query(conn2);
			
			conn.setAutoCommit(true);
			conn2.setAutoCommit(true);
		}
	}
	
	/**
	 * 成功：2個交易重疊，且前交易未修改資料
	 * @throws SQLException
	 */
	public void scenario2() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Connection conn2 = dataSource.getConnection();) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			query(conn);			// Transaction1 開始
			query(conn2);			// Transaction2 開始
			
			update(conn, "B");
			conn.rollback();		// Transaction1 結束
			query(conn);

			update(conn2, "C");	
			conn2.commit();			// Transaction2 結束
			query(conn2);
			
			conn.setAutoCommit(true);
			conn2.setAutoCommit(true);
		}
	}

	/**
	 * 失敗：2個交易重疊，但後交易試圖修改前交易已commit的資料
	 * @throws SQLException
	 */
	public void scenario3() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Connection conn2 = dataSource.getConnection();) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			query(conn);			// Transaction1 開始
			query(conn2);			// Transaction2 開始
			
			update(conn, "B");
			conn.commit();			// Transaction1 結束
			query(conn);

			update(conn2, "C");	
			conn2.commit();			// Transaction2 結束；試圖修改前交易已commit的資料，拋錯
			query(conn2);
			
			conn.setAutoCommit(true);
			conn2.setAutoCommit(true);
		}
	}
	
	/**
	 * DeadLock：等待另一條連線釋出exclusive lock
	 * @throws SQLException
	 */
	public void scenario4() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Connection conn2 = dataSource.getConnection();) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			query(conn);			// Transaction1 開始
			query(conn2);			// Transaction2 開始
			
			update(conn, "B");
			update(conn2, "C");		// 等待前交易commit或rollback，無窮等待

			conn.commit();			// Transaction1 結束
			query(conn);
			conn2.commit();			// Transaction2 結束
			query(conn2);

			conn.setAutoCommit(true);
			conn2.setAutoCommit(true);
		}
	}
	
	/**
	 * 連線2查到舊資料
	 * @throws SQLException
	 */
	public void scenario5() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Connection conn2 = dataSource.getConnection();) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			query(conn);			// Transaction1 開始
			query(conn2);			// Transaction2 開始
			
			update(conn, "B");
			conn.commit();			// Transaction1 結束

			query(conn);
			query(conn2);			// 仍屬於Transaction2，查到的是舊資料
			conn.setAutoCommit(true);
			conn2.setAutoCommit(true);
		}
	}
	
	/**
	 * 連線2查到連線1修改後的資料
	 * @throws SQLException
	 */
	public void scenario6() throws SQLException {
		try (Connection conn = dataSource.getConnection();
				Connection conn2 = dataSource.getConnection();) {
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn2.setAutoCommit(false);
			conn2.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			query(conn);			// Transaction1 開始
			query(conn2);			// Transaction2 開始
			
			update(conn, "B");
			conn.commit();			// Transaction1 結束
			
			conn2.commit();			// Transaction2 結束

			query(conn);
			query(conn2);			// 新交易，查到的是更新後的資料
			conn.setAutoCommit(true);
			conn2.setAutoCommit(true);
		}
	}
	
	/**
	 * 查詢
	 * @param conn
	 * @throws SQLException
	 */
	private void query(Connection conn) throws SQLException {
		log.info("{} 查詢", conn);
		
		try (PreparedStatement ps = conn.prepareStatement(QUERY_SQL);
				ResultSet rs = ps.executeQuery();) {
			List<Card> yfuCardList = new ArrayList<>();
			while (rs.next()) {
				Card yfuCard = new Card();
				yfuCard.setCardId(rs.getString("CARD_ID"));
				yfuCard.setType(rs.getString("TYPE"));
				yfuCardList.add(yfuCard);
			}
			log.info("{}, Data: {}", conn, yfuCardList);
		}
	}
	
	/**
	 * 更新
	 * @param conn
	 * @param type
	 * @throws SQLException
	 */
	private void update(Connection conn, String type) throws SQLException {
		log.info("{} 更新", conn);
		
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, type);
			int count = ps.executeUpdate();
			log.info("{} 異動{}筆資料", conn, count);
		}
	}

}