package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dbUtil.Conn;
import utility.FileUtility;

//ウィンドウユーティリティクラス
public class WindowUtil extends JFrame implements ChangeListener {

	private String m_WindowTitle = "DBTool";
	private String m_Version = " -ver 1.0";

	//プルダウンリストのファイルパス
	private String Pass = "../01_Setting/Schema.csv";

	//スキーマ選択用コンボボックス
	private JComboBox<String> m_SchemaCombo;

	//DB接続情報保持用
	private Map<String, String> m_SchemaPassMap;

	//ログ出力用テキストエリア
	private JTextArea m_Logarea;

	//従業員番号入力用テキストフィールド
	JTextField m_Te_EmpNo;


	//コンストラクタ
	 public WindowUtil() throws IOException{

		JTabbedPane tabbedPane = new JTabbedPane();

		//Importタブのパネル
		JPanel ImporttabPanel = new JPanel();
		//レイアウト設定
		ImporttabPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		ImporttabPanel.add(new JLabel("インポートファイルパス："));
		ImporttabPanel.add(new JTextField("", 40));






		//Exportタブのパネル
		JPanel ExporttabPanel = new JPanel();
		ExporttabPanel.setLayout(null);

		//ラベルの作成
		JLabel La_Schema = new JLabel("接続先スキーマ：");
		La_Schema.setBounds(10, 10, 150, 40);
		ExporttabPanel.add(La_Schema);

		//プルダウンの作成（スキーマ選択用）
		FileUtility fileutil = new FileUtility();
		m_SchemaPassMap = fileutil.ReadCSVFile(Pass);

		List<String> PdList = new ArrayList<String>();
		//スキーマの配列を作成
		for(String key:m_SchemaPassMap.keySet()) {
			PdList.add(key);
		}

		String[] ArrPdList = PdList.toArray(new String[PdList.size()]);
		m_SchemaCombo = new JComboBox<String>(ArrPdList);
		m_SchemaCombo.setBounds(120, 20, 150, 20);
		ExporttabPanel.add(m_SchemaCombo);



		//ラベルの作成
		JLabel La_EmpNo = new JLabel("従業員番号：");
		La_EmpNo.setBounds(10, 60, 100, 40);
		ExporttabPanel.add(La_EmpNo);

		//テキストフィールドの作成
		m_Te_EmpNo = new JTextField(10);
		m_Te_EmpNo.setBounds(100, 73, 200, 20);
		ExporttabPanel.add(m_Te_EmpNo);

		//ボタンの作成
		JButton BnExport = new JButton("エクスポート");
		BnExport.setBounds(310, 73, 150, 20);

		//ボタン押下時の処理
		BnExport.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String StrSchema = (String) m_SchemaCombo.getSelectedItem();
					String StrPass = m_SchemaPassMap.get(StrSchema);

					//DB接続
					m_Logarea.append("DB接続中…\r\n");
					Conn conn = new Conn();

					if(!conn.ConnectDB(StrSchema, StrPass)) {
						m_Logarea.append("DB接続に失敗しました。\r\n");
					}else {
						m_Logarea.append("DB接続に成功しました。\r\n");
						m_Logarea.append("スキーマ：" + StrSchema + "\r\n");
					}

					//テキストフィールドの値取得
					//TODO 複数の従業員番号入力可能対応
					String EmpNo = m_Te_EmpNo.getText();
					m_Logarea.append("従業員番号：" + EmpNo + "をエクスポートします。\r\n");


				}catch(IOException ioe) {
					ioe.printStackTrace();
				} catch (ClassNotFoundException cnfe) {
					cnfe.printStackTrace();
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

		});

		//ボタンの追加
		ExporttabPanel.add(BnExport);

		//ラベル追加
		JLabel La_Log = new JLabel("実行ログ：");
		La_Log.setBounds(40, 110, 80, 60);
		ExporttabPanel.add(La_Log);

		//ログ出力用テキストエリア作成
		m_Logarea = new JTextArea();
		m_Logarea.setLineWrap(true);
		JScrollPane scrollpane = new JScrollPane(m_Logarea);
		scrollpane.setBounds(40, 150, 450, 300);
		ExporttabPanel.add(scrollpane);


		//タブの追加
		tabbedPane.add("Import",ImporttabPanel);
		tabbedPane.add("Export",ExporttabPanel);

		//状態変更を受け取るリスナーにこのコンストラクタ追加
		tabbedPane.addChangeListener(this);
		getContentPane().add(tabbedPane,BorderLayout.WEST);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(m_WindowTitle + m_Version);
		setSize(800, 600);
		setVisible(true);
	}


	 //ゲッター（テキストエリアのインスタンス）
	 public JTextArea GetTextArea() {
		 return m_Logarea;
	 }

	 //抽象メソッドの定義
	 public void stateChanged(ChangeEvent e) {
//			JTabbedPane t = (JTabbedPane )e.getSource();
//			if (t.getSelectedComponent() == m_textarea1) {
//				System.out.println("m_textarea1");
//			} else if(t.getSelectedComponent() == m_textarea2){
//				System.out.println("Table");
//			}
		}

}
