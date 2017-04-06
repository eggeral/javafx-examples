package baylandtag.av_combobox_edit_in_table;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 7974621277508998411L;

    public DatabaseException(String message) {
        super(message);
    }

	public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
	
}