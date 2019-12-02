package dbUtil;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utility.FileUtility;


//DB接続クラス
public class Conn {

	//DB接続情報
	private List<String> ConnectDBInfo = new ArrayList<String>();

	Connection conn = null;
    Statement stmt = null;
    ResultSet rset = null;

    // サーバ名
    String servername = "";
    //ポート番号
    String port = "";
    // SID
    String sid = "";

	//コンストラクタ
	public Conn() throws FileNotFoundException, IOException{
		FileUtility fileutilObj = new FileUtility();
		if(!fileutilObj.ReadDBInfoFile()) {
			//ファイルが読み込めなかった場合

		}

		ConnectDBInfo = fileutilObj.GetConnectDBInfo();

		servername = ConnectDBInfo.get(0);
		port = ConnectDBInfo.get(1);
		sid = ConnectDBInfo.get(2);
	}

	//DB接続関数
	public Boolean ConnectDB(String user, String password) throws ClassNotFoundException, SQLException{

		//JBBCドライバクラスのロード
	    Class.forName("oracle.jdbc.driver.OracleDriver");

	    // Connectionの作成
	    conn = DriverManager.getConnection("jdbc:oracle:thin:@" + servername + ":" + port + ":" + sid, user, password);

	    if(conn == null) {
	    	return false;
	    }

//	 // Statementの作成
//	      stmt = conn.createStatement();
//
//	      // Resultsetの作成
//	      rset = stmt.executeQuery("select * from EMPLOYEE");
//
//	      // 取得したデータを出力する
//	      while (rset.next()) {
//	        System.out.println(rset.getString("EMP_NO") + "," + rset.getString("EMP_NAME") + "," + rset.getString("GENDER_F"));
//	      }


		return true;
	}


}
