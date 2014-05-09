package cl.uchile.fcfm.dcc.groupsorganizer.connection.exception;


public class ServerErrorException extends Exception {
	private static final long serialVersionUID = 1L;
	private Exception e;
	
	public ServerErrorException(Exception e) {
		this.e = e;
	}
	
	public ServerErrorException() {
	}

	@Override
	public String getLocalizedMessage() {
		if(hasWrappedException())
			return e.getLocalizedMessage();
		else
			return "Error en el servidor";
	}

	public boolean hasWrappedException() {
		return e != null;
	}
	
}
