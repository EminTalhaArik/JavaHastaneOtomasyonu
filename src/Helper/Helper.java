package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {

	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "�ptal");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		UIManager.put("OptionPane.noButtonText", "Hay�r");
	}

	public static void showMessage(String str) {
		String msg;
		optionPaneChangeButtonText();

		switch (str) {
		case "fill":
			msg = "L�tfen t�m alanlar� doldurunuz ! ";
			break;
		case "succes":
			msg = "��lem Ba�ar�l�";
			break;
		case "error":
			msg = "Bir Hata Ger�ekle�ti";
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
			msg = "Bu i�lemi onaylamak istedi�inizden emin misiniz ?";
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
