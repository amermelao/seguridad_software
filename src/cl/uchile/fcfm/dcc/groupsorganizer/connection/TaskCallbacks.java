package cl.uchile.fcfm.dcc.groupsorganizer.connection;



public interface TaskCallbacks {
	
	  abstract void onPreExecute(TareaAsincrona tarea);
	  abstract void onProgressUpdate(TareaAsincrona tarea, Object... vars);
	  abstract void onCancelled(TareaAsincrona tarea);
	  abstract void onPostExecute(TareaAsincrona tarea, Object... vars);
}
