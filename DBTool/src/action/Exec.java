package action;

import java.io.IOException;

import window.WindowUtil;

public class Exec {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ


		try {
			//ウィンドウ作成
			WindowUtil windowObj = new WindowUtil();
			if(!windowObj.Initialize()) {
				return;
			}
		}catch(IOException e) {
			//TODO 例外処理きちんと考える
			e.printStackTrace();
		}



	}

}
