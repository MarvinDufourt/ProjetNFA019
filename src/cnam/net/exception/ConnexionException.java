package cnam.net.exception;

public class ConnexionException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnexionException(String message ) {
        super( message );
    }

    public ConnexionException(String message, Throwable cause ) {
        super( message, cause );
    }

    public ConnexionException(Throwable cause ) {
        super( cause );
    }
}