package utility;

import javax.swing.JOptionPane;

//例外処理クラス
public class MyException {

	//エラー内容を出力するダイアログを作成
	public static void CreateDialog(String Message) {
		JOptionPane.showMessageDialog(null,
				"処理中にエラーが発生しました。\r\n" +  Message);
	}

}
