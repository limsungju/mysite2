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
import kr.co.itcen.web.PaginationUtil;

public class BoardDao {
	// 게시글 작성
	public Boolean insert(BoardVo boardVo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = getConnection();
			
			String sql = "insert into board(no, title, contents, hit, reg_date, g_no, o_no, depth, user_no, status)" +
					 "    values(null, ?, ?, 0, now(), (select ifnull(max(b.g_no)+1,1) from board b), ?, ?, ?, 'i')";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getoNo());
			pstmt.setInt(4, boardVo.getDepth());
			pstmt.setLong(5, boardVo.getuNo());

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
	
	// 게시글에 답글달기
	public Boolean insertBoard(BoardVo boardVo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;

		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = getConnection();
			
			String sql = "insert into board values(null, ?, ?, 0, now(), ?, ?, ?, 'i', ?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setInt(3, boardVo.getgNo());
			pstmt.setInt(4, boardVo.getoNo());
			pstmt.setInt(5, boardVo.getDepth());
			pstmt.setLong(6, boardVo.getuNo());

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
	
	// 게시글 답글달기
	public Boolean update(Integer gNo, Integer oNo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = "update board set o_no=o_no+1 where g_no = ? and o_no >= ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, gNo);
			pstmt.setInt(2, oNo);

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
	
	// 게시글 수정
	public Boolean update(BoardVo boardVo) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = "update board  set title = ?, contents = ? where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContents());
			pstmt.setLong(3, boardVo.getNo());

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
	
	// 조회수 증가
	public Boolean update(Long no) {
		Boolean result = false;

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			String sql = "update board  set hit = hit+1 where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();

			result = (count == 1);

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
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
	
	// 게시글 삭제
	public void delete(BoardVo boardVo) {
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board b inner join user u" +
				    "         on u.no = b.user_no" +
				    "        set b.status = 'd', b.title = '삭제된 글입니다.'" +
				    "      where b.no = ?" +
				    "        and u.no = ?";

			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, boardVo.getNo());
			pstmt.setLong(2, boardVo.getuNo());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
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
	}
	
	// 게시글 번호에 대한 정보 출력
	public BoardVo getList(Long no) {
		BoardVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select b.user_no as uno, b.no as no, b.title, b.contents" +
			        "       from user u, board b" +
					"      where u.no = b.user_no" +
			        "        and b.no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new BoardVo();
				
				result.setuNo(rs.getLong("uno"));
				System.out.println(rs.getLong("uno"));
				result.setNo(rs.getLong("no"));
				System.out.println(rs.getLong("no"));
				result.setTitle(rs.getString("title"));
				System.out.println(rs.getString("title"));
				result.setContents(rs.getString("contents"));
				System.out.println(rs.getString("contents"));
				
				
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
	
	// 게시글 리스트
	public List<BoardVo> getList(String kwd, PaginationUtil pagination) {
		List<BoardVo> result = new ArrayList<BoardVo>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select b.no as no, title, name, contents, hit, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date, depth, status" +
			        "       from user u, board b" +
					"      where u.no = b.user_no" +
			        "        and (b.title like ? or b.contents like ?)" +
			        "   order by g_no desc, o_no asc" +
			        "      limit ?, ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + ((kwd == null) ? "" : kwd) + "%");
			pstmt.setString(2, "%" + ((kwd == null) ? "" : kwd) + "%");
			pstmt.setInt(3, (pagination.getCurrentPage() - 1) * pagination.getListSize());
			pstmt.setInt(4, pagination.getListSize());
//			5. limit 수정
//			ex ) limit (보여줄 페이지 - 1) * 한 페이지에 보여줄 게시글의 수, 한 페이지에 보여줄 게시글의 수
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVo boardVo = new BoardVo();
				
				boardVo.setNo(rs.getLong("no"));
				boardVo.setTitle(rs.getString("title"));
				boardVo.setuName(rs.getString("name"));
				boardVo.setContents(rs.getString("contents"));
				boardVo.setHit(rs.getInt("hit"));
				boardVo.setRegDate(rs.getString("reg_date"));
				boardVo.setDepth(rs.getInt("depth"));
				boardVo.setStatus(rs.getString("status"));
				
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
	
	// 전체 게시글 수 구하기
	public int getListCount(String kwd) {
		int count = -1;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select count(*) as 'cnt'" +
			        "       from user u, board b" +
					"      where u.no = b.user_no" +
			        "        and (b.title like ? or b.contents like ?)" +
			        "   order by g_no desc, o_no asc";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, "%" + ((kwd == null) ? "" : kwd) + "%");
			pstmt.setString(2, "%" + ((kwd == null) ? "" : kwd) + "%");
			
//			5. limit 수정
//			ex ) limit (보여줄 페이지 - 1) * 한 페이지에 보여줄 게시글의 수, 한 페이지에 보여줄 게시글의 수
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("cnt");
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
		return count;
	}
	
	
	// 게시글에 대한 g_no, o_no, depth 출력
	public BoardVo select(Long no) {
		BoardVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();
			
			String sql = "select g_no, o_no, depth" +
			        "       from board" +
					"      where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = new BoardVo();
				
				result.setgNo(rs.getInt("g_no"));
				result.setoNo(rs.getInt("o_no"));
				result.setDepth(rs.getInt("depth"));
				
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
