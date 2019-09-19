package kr.co.itcen.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.itcen.mysite.vo.BoardVo;

public class BoardDao {
	public Boolean insert(BoardVo boardVo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = getConnection();
			
			String sql = "insert into board(no, title, contents, hit, reg_date, g_no, o_no, depth, user_no)" +
					 "    values(null, ?, ?, 0, now(), (select ifnull(max(b.g_no)+1,1) from board b), 1, 0, ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getuNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

			stmt = connection.createStatement();
			// mysql에만 있는 함수
			rs = stmt.executeQuery("select last_insert_id()");
			if (rs.next()) {
				Long no = rs.getLong(1);
				boardVo.setNo(no);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//	public Boolean update(BoardVo boardVo) {
//		Boolean result = false;
//
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			connection = getConnection();
//
//			String sql = "update user set name = ?, password = ?, gender = ? where no = ?";
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setString(1, boardVo.getName());
//			pstmt.setString(2, boardVo.getPassword());
//			pstmt.setString(3, boardVo.getGender());
//			pstmt.setLong(4, boardVo.getNo());
//
//			int count = pstmt.executeUpdate();
//
//			result = (count == 1);
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (rs != null) {
//					rs.close();
//				}
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
//
//	public void delete(BoardVo boardVo) {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			connection = getConnection();
//
//			String sql = "delete from guestbook where no=? and password=?";
//
//			pstmt = connection.prepareStatement(sql);
//			pstmt.setLong(1, boardVo.getNo());
//			pstmt.setString(2, boardVo.getPassword());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public BoardVo getList(Long no) {
		BoardVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select u.no as uno, b.no as no, b.title, b.contents" +
			        "       from user u, board b" +
					"      where u.no = b.user_no" +
			        "        and b.no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new BoardVo();
				
				result.setTitle(rs.getString("title"));
				result.setContents(rs.getString("contents"));
				result.setuNo(rs.getLong("no"));
				result.setNo(rs.getLong("no"));
				
			}
						
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public List<BoardVo> getList() {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select b.no as no, title, name, contents, hit, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date" +
			        "       from user u, board b" +
					"      where u.no = b.user_no" +
			        "   order by reg_date desc";
			pstmt = connection.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo boardVo = new BoardVo();
				
				boardVo.setNo(rs.getLong("no"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setuName(rs.getString("name"));
				boardVo.setContents(rs.getString("contents"));
				boardVo.setHit(rs.getInt("hit"));
				boardVo.setRegDate(rs.getString("reg_date"));
				
				result.add(boardVo);
			}
						
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mariadb://192.168.1.84:3306/webdb?characterEncoding=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("Fail to Loading Driver:" + e);
		}
		return connection;
	}

	

}
