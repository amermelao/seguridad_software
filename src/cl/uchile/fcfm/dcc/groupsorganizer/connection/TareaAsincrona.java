package cl.uchile.fcfm.dcc.groupsorganizer.connection;

import android.content.Context;
import android.os.AsyncTask;

public abstract class TareaAsincrona extends AsyncTask<Void, Object, Object> {

	private Exception thrownException;
	private TaskCallbacks taskCallbacks;
	private Context context;
	
	public TareaAsincrona(Context context, TaskCallbacks taskCallbacks) {
		this.taskCallbacks = taskCallbacks;
		this.context = context;
	}
	
	public void setTaskCallbacks(TaskCallbacks taskCallbacks) {
		this.taskCallbacks = taskCallbacks;
	}
	
	/**
	 * En caso de un error de ejecución en la tarea, se puede usar este método para notificar
	 * un error de ejecución.
	 * @param e
	 */
	protected void setThrownException(Exception e){
		this.thrownException = e;
	}
	/**
	 * Retorna la excepción asignada durante la ejecución de la tarea.
	 * Permite saber por qué no se pudo completar de forma correcta una tarea.
	 * @return
	 * 		La excepción entregada a {@link cl.uchile.fcfm.dcc.groupsorganizer.connection.TareaAsincrona#setThrownException(Exception)}.
	 */
	public Exception getThrownException() {
		return this.thrownException;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		if(taskCallbacks != null)
			taskCallbacks.onPostExecute(this, result);
		super.onPostExecute(result);
	}
	
	@Override
	protected void onPreExecute() {
		if(taskCallbacks != null)
			taskCallbacks.onPreExecute(this);
		super.onPreExecute();
	}
	
	@Override
	protected void onCancelled() {
		if(taskCallbacks != null)
			taskCallbacks.onCancelled(this);
		super.onCancelled();
	}
	
	protected Context getContext() {
		return context;
	}
	
}