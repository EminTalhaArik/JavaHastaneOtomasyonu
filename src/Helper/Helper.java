package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "Ýptal");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hayýr");
	}

	public static void showMessage(String str) {
		String msg;
		optionPaneChangeButtonText();

		switch (str) {
		case "fill":
			msg = "Lütfen tüm alanlarý doldurunuz ! ";
			break;
		case "succes":
			msg = "Ýþlem Baþarýlý";
			break;
		case "error":
			msg = "Bir Hata Gerçekleþti";
			break;
		default:
			msg = str;
		}

		JOptionPane.showMessageDialog(null, msg, "mesaj", JOptionPane.INFORMATION_MESSAGE);
	}

	public static boolean confirmMessage(String str) {

		String msg;
		optionPaneChangeButtonText();

		switch (str) {
		case "sure":
			msg = "Bu iþlemi onaylamak istediðinizden emin misiniz ?";
			break;
		default:
			msg = str;
			break;
		}

		int res = JOptionPane.showConfirmDialog(null, msg, "Dikkat !", JOptionPane.YES_NO_OPTION);

		if (res == 0) {
			return true;
		} else {
			return false;
		}
	}

}
