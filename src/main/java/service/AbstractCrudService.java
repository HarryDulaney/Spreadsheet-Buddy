package service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public abstract class AbstractCrudService<T, R extends JpaRepository<T, Long>> extends ParentService {

	private R repository;

	public AbstractCrudService(R repository) {
		this.repository = repository;

	}
	public Service<T> save(T obj, EventHandler<WorkerStateEvent> onSuccess,
							  EventHandler<WorkerStateEvent> beforeStart) throws Exception {

		if (obj == null) {
			throw new Exception();
		}

		return createService(new Task<T>() {
			protected T call() throws Exception {
				
				return repository.save(obj);
			}
		}, onSuccess, beforeStart);

	}

	public Service<Void> delete(T obj, EventHandler<WorkerStateEvent> onSuccess,
			EventHandler<WorkerStateEvent> beforeStart) throws Exception {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				repository.delete(obj);
				return null;
			}
		}, onSuccess, beforeStart);

	}
	public Service<List<T>> findAll(EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<List<T>>() {
			protected List<T> call() throws Exception {
				return repository.findAll();
			};
		}, onSuccess, beforeStart);
	}
	
	

}
