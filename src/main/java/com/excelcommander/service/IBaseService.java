package com.excelcommander.service;

import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface IBaseService<T> {

	 Service<T> save(T obj, EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart)
			throws Exception;

	 Service<Void> delete(T obj, EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart)
			throws Exception;

	 Service<List<T>> findAll(EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart);

	 Service<T> findById(long id, EventHandler<WorkerStateEvent> onSuccess, EventHandler<WorkerStateEvent> beforeStart)
			throws Exception;

	 void onClose();
}
