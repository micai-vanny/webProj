package fileBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBCon;

public class FileDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (psmt != null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean updateFile(FileVO vo) {
		conn = DBCon.getConnect();
		String sql = "update file_board set author = ?, title = ?, file_name=? where num = ?";
		int modiCnt = 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getAuthor());
			psmt.setString(2, vo.getTitle());
			psmt.setString(3, vo.getFileName());
			psmt.setInt(4, vo.getNum());
			
			modiCnt = psmt.executeUpdate();
			System.out.println(modiCnt + "건이 수정되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return modiCnt == 0 ? false : true;
	}
	
	public FileVO delFile(int num) {
		conn = DBCon.getConnect();
		String sql = "delete from file_board where num = ?";
		FileVO file = new FileVO();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			
			int del = psmt.executeUpdate();
			System.out.println(del+"건이 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return file;
	}

	public FileVO getFile(int num) {	// num값으로 1건 조회하는 기능
		conn = DBCon.getConnect();
		String sql = "select * from file_board where num = ?";
		FileVO file = new FileVO();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,num);
			rs = psmt.executeQuery();

			if(rs.next()){
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setFileName(rs.getString("file_name"));
				file.setNum(rs.getInt("num"));
				file.setTitle(rs.getString("title"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return file;
	}
	
	public List<FileVO> getFileList() {
		conn = DBCon.getConnect();
		List<FileVO> list = new ArrayList<FileVO>();
		try {
			psmt = conn.prepareStatement("select * from file_board");
			rs = psmt.executeQuery();
			while(rs.next()) {
				FileVO vo = new FileVO();
				vo.setAuthor(rs.getString("author"));
				vo.setDay(rs.getString("day"));
				vo.setFileName(rs.getString("file_name"));
				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	public FileVO getInsertKeyVal(FileVO vo) {
		conn = DBCon.getConnect();
		// 신규번호, 한 건 입력, 한 건 조회
		String selectKey = "select nvl(max(num),0)+1 from file_board";
		String insertSql = "insert into file_board values(?,?,?,?,to_char(sysdate,'YYYY-MM-DD'))";
		String selectSql = "select * from file_board where num = ?";

		FileVO file = new FileVO();

		int key = 0;

		try {// key값을 가져오는 쿼리
			psmt = conn.prepareStatement(selectKey);
			rs = psmt.executeQuery();

			if (rs.next()) {
				key = rs.getInt(1);
			}

			// 새로운 키값으로 1건 새로 입력하는 쿼리
			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, key);
			psmt.setString(2, vo.getAuthor());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getFileName());

			int in = psmt.executeUpdate();
			System.out.println(in + "건 입력 완료.");

			// 신규 입력 된 전체 정보 가져오는 쿼리
			psmt = conn.prepareStatement(selectSql);
			psmt.setInt(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setAuthor(rs.getString("author"));
				file.setDay(rs.getString("day"));
				file.setFileName(rs.getString("file_name"));
				file.setTitle(rs.getString("title"));
				file.setNum(rs.getInt("num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return file;
	}
}
