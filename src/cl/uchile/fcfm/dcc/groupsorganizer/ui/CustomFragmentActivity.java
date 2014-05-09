package cl.uchile.fcfm.dcc.groupsorganizer.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.TareaAsincrona;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.TaskCallbacks;
import cl.uchile.fcfm.dcc.groupsorganizer.connection.WorkerFragment;

import java.util.List;

public abstract class CustomFragmentActivity extends FragmentActivity implements TaskCallbacks {
	
	public void runTask(TareaAsincrona tarea) {
		FragmentManager fm = getSupportFragmentManager();
		WorkerFragment worker = getWorkerFragment();
		worker.runTask(tarea);
	}

    public List<TareaAsincrona> getRunningTasks() {
        return getWorkerFragment().getRunningTasks();
    }

    private WorkerFragment getWorkerFragment() {
        WorkerFragment worker = (WorkerFragment)getSupportFragmentManager().findFragmentByTag("WorkerFragment");
        if(worker == null) {
            worker = new WorkerFragment();
            getSupportFragmentManager().beginTransaction().add(worker, "WorkerFragment").commit();
        }
        return worker;
    }
	
	@Override
	public void onPreExecute(TareaAsincrona tarea) {
		
	}
	
	@Override
	public void onCancelled(TareaAsincrona tarea) {
		if(isFinishing()) return;
        getWorkerFragment().removeTask(tarea);
	}
	
	@Override
	public void onPostExecute(TareaAsincrona tarea, Object... vars) {
		if(isFinishing()) return;
		WorkerFragment workerFragment = getWorkerFragment();
		if(workerFragment != null) {
			workerFragment.removeTask(tarea);
		}
	}
	
	@Override
	public void onProgressUpdate(TareaAsincrona tarea, Object... vars) {
		// TODO Auto-generated method stub
		
	}
	

}
