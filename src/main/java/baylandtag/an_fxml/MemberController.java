package baylandtag.an_fxml;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MemberController {
	@FXML private TextField titleTextField; 
	@FXML private TextField surnameTextField; 
	@FXML private TextField forenameTextField;
	
	public TextField getTitleTextField() {
		return titleTextField;
	}
	public TextField getSurnameTextField() {
		return surnameTextField;
	}
	public TextField getForenameTextField() {
		return forenameTextField;
	}
	
	
}
