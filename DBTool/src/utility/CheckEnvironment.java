package utility;

import java.io.File;

//実行に必要な条件をチェックする
public class CheckEnvironment {

	//ファイルの存在チェック
	public static boolean IsFileExist(String FilePass) {

		File file = new File(FilePass);

		return file.exists();
	}
}
