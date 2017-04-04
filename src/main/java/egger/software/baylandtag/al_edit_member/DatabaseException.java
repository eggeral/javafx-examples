package egger.software.baylandtag.al_edit_member;

public class DatabaseException extends RuntimeException {

	private static final long serialVersionUID = 7974621277508998411L;

    public DatabaseException(String message) {
        super(message);
    }

	public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
