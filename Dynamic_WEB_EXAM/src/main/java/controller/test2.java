package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class test2 {
	public int insertNotice(Connection conn, Notice n){
		PreparedStatement pstat=null;
		int r= 0;
		String query="insert into notice values(seq_nno.nextval,?,?,?,default,?,default)";
		try{
			pstat=conn.prepareStatement(query);
			pstat.setString(1,n.getBoardTitle());
			pstat.setString(2,n.getBoardContent());
			pstat.setString(3,n.getBoardWriter());
			pstat.setDate(4,n.getBoardDate());
			r=pstat.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			pstat.close();
			conn.close();
		}

	}
}
