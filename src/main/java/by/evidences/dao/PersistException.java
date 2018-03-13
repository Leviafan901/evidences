package by.evidences.dao;

public class PersistException extends Exception {

	private static final long serialVersionUID = 620023371716493158L;

	public PersistException() {
    }

    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }
}
