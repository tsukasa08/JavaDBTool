package dbUtil;

//EMPLOYEEテーブルのDTOクラス
public class EMPLOYEE_DTO {

	//従業員番号（PK）
	private String EMP_NO;

	//従業員番号の名前
	private String EMP_NAME;

	//従業員番号の性別（男：０女：１)
	private boolean GENDER_F;

	//従業員番号の部署名
	private String EMP_BUSHO;



	//従業員番号（PK）のゲッター
	public String GetEMP_NO() {
		return EMP_NO;
	}

	//従業員番号（PK）のセッター
	public void SetEMP_NO(String emp_no) {
		EMP_NO = emp_no;
	}

	//従業員番号の名前のゲッター
	public String GetEMP_NAME() {
		return EMP_NAME;
	}

	//従業員番号の名前のセッター
	public void SetEMP_NAME(String emp_name) {
		EMP_NAME = emp_name;
	}

	//従業員番号の性別（男：０女：１)のゲッター
	public boolean GetGENDER_F() {
		return GENDER_F;
	}

	//従業員番号の性別（男：０女：１)のセッター
	public void SetGENDER_F(boolean gender_f) {
		GENDER_F = gender_f;
	}

	//従業員番号の部署名のゲッター
	public String GetEMP_BUSHO() {
		return EMP_BUSHO;
	}

	//従業員番号の部署名のセッター
	public void SetEMP_BUSHO(String emp_busho) {
		EMP_BUSHO = emp_busho;
	}

}
