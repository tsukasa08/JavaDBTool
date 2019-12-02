package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ファイルユーティリティクラス
public class FileUtility {

	//読み込み対象ファイルパス
	String FilePass = "../01_Setting/";

	//読み込み対象ファイル名
	String FileName = "DBInfo.csv";

	//ファイル内容を保持
	private List<String> ConnectDBInfo = new ArrayList<String>();

	//ゲッター
	public List<String> GetConnectDBInfo(){
		return ConnectDBInfo;
	}

	//ファイル読み込み
	public Boolean ReadDBInfoFile() throws FileNotFoundException, IOException{

		File file = new File(FilePass + FileName);

		BufferedReader br = null;
		try {

			br = new BufferedReader(new FileReader(file));

			//基本的に１行しか読み込まない
			String str = br.readLine();

			//ファイルが空の場合
			if(str.isEmpty()) {
				return false;
			}

			ConnectDBInfo = Arrays.asList(str.split(","));

		}finally {
			br.close();
		}

		return true;
	}


	//スキーマ設定csvファイル読み込み
	public Map<String, String> ReadCSVFile(String Pass) throws IOException {

		File csvfile = new File(Pass);

		LineNumberReader br = null;
		try {

			//１行目のスキーマ読み込み
			br = new LineNumberReader(new FileReader(csvfile));
			br.setLineNumber(0);
			String SchemaStr = br.readLine();
			br.setLineNumber(1);
			String PasswordStr = br.readLine();

			//スキーマ行が空の場合
			if(SchemaStr.isEmpty()) {
				return null;
			}

			//パスワード行が空の場合
			if(PasswordStr.isEmpty()) {
				return null;
			}

			Map<String, String> SchemaPassMap = new HashMap<>();

			List<String> ShemaList = Arrays.asList(SchemaStr.split(","));
			List<String> PassList = Arrays.asList(PasswordStr.split(","));

			for(int i = 0; i < ShemaList.size(); i++) {
				SchemaPassMap.put(ShemaList.get(i), PassList.get(i));
			}

			return SchemaPassMap;

		}finally {
			br.close();
		}
	}


}
