package cl.uchile.fcfm.dcc.groupsorganizer.connection;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerFragment extends Fragment {
	ExecutorService mExecutorService;
	 List<TareaAsincrona> tareas;
	private boolean mConfigurationChanged = false;

	public WorkerFragment() {
		setRetainInstance(true);
		mExecutorService = Executors.newCachedThreadPool();
		tareas = new ArrayList<TareaAsincrona>();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		mConfigurationChanged = true;
		super.onSaveInstanceState(outState);
	}

	public void runTask(final TareaAsincrona tarea) {
		tarea.setTaskCallbacks((TaskCallbacks) getActivity());
		synchronized (tareas) {
			tareas.add(tarea);
			tarea.executeOnExecutor(mExecutorService, (Void[])null);
		}
	}

	public List<TareaAsincrona> getRunningTasks() {
		synchronized (tareas) {
			return new ArrayList<TareaAsincrona>(tareas);
		}
	}

	public void removeTask(TareaAsincrona tarea) {
		synchronized (tareas) {
			tareas.remove(tarea);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		synchronized (tareas) {
			for (TareaAsincrona t : tareas) {
				t.setTaskCallbacks((TaskCallbacks) activity);
			}
		}
		mConfigurationChanged = false;
	}

	@Override
	public void onStop() {
		super.onStop();
		if (mConfigurationChanged)
			return;
		List<TareaAsincrona> tareasPorRemover = new ArrayList<TareaAsincrona>();
		synchronized (tareas) {
			for (TareaAsincrona t : tareas) {
				t.cancel(true);
				// Encolamos la remoción, porque puede que el
				// CustomFragmentActivity no las remueva.
				tareasPorRemover.add(t);
			}
			for (TareaAsincrona t : tareasPorRemover) {
				tareas.remove(t);
			}
		}
	}
}
