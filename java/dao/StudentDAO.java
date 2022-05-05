package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.StudentDTO;

public class StudentDAO {
	
	public StudentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws Exception{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "kh";
		String pw = "kh";
		return DriverManager.getConnection(url,id,pw);
	}
	
	public int insert(StudentDTO dto) throws Exception{
		String sql = "insert into student values(student_seq.nextval, ?,?,?,?,sysdate)";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1,dto.getName());
			pstat.setInt(2, dto.getKor());
			pstat.setInt(3, dto.getEng());
			pstat.setInt(4, dto.getMath());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public List<StudentDTO> selectAll() throws Exception{
		String sql = "select rank() over(order by kor+eng+math desc) rank, sid,name,kor,eng,math from student";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();){
			
			List<StudentDTO> list = new ArrayList<>();
			while(rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setRank(rs.getInt("rank"));
				dto.setSid(rs.getInt("sid"));
				dto.setName(rs.getString("name"));
				dto.setKor(rs.getInt("kor"));
				dto.setEng(rs.getInt("eng"));
				dto.setMath(rs.getInt("math"));
				
				list.add(dto);
			}
			return list;
		}
		
	}
	
	public int delete(int sid) throws Exception{
		String sql = "delete from student where sid=?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setInt(1, sid);
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public int update(StudentDTO dto) throws Exception{
		String sql = "update student set name=?, kor=?,eng=?,math=? where sid=?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql)){
			pstat.setString(1,dto.getName());
			pstat.setInt(2, dto.getKor());
			pstat.setInt(3, dto.getEng());
			pstat.setInt(4, dto.getMath());
			pstat.setInt(5, dto.getSid());
			int result = pstat.executeUpdate();
			con.commit();
			return result;
		}
	}
	
	public List<StudentDTO> search(String name) throws Exception{
		
		String sql = "select rank() over(order by kor+eng+math desc) rank, sid,name,kor,eng,math from student where name=?";
		try(Connection con = this.getConnection();
			PreparedStatement pstat = con.prepareStatement(sql);){
			
			pstat.setString(1, name);

			try(ResultSet rs = pstat.executeQuery()){

				List<StudentDTO> list = new ArrayList<>();

				while(rs.next()) {
					StudentDTO dto = new StudentDTO();
					dto.setRank(rs.getInt("rank"));
					dto.setSid(rs.getInt("sid"));
					dto.setName(rs.getString("name"));
					dto.setKor(rs.getInt("kor"));
					dto.setEng(rs.getInt("eng"));
					dto.setMath(rs.getInt("math"));
					
					list.add(dto);
				}
				return list;
			}
			
		}
		
	}

	
	
	
}











