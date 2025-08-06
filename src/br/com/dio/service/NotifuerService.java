package br.com.dio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifuerService {

	
	private Map<EventNum, List<EventListener>> listeners = new HashMap<>(){{
		
		put(EventNum.CLEAR_SPACE, new ArrayList<EventListener>());
	}
	};
	
	public void subscriber(final EventNum enevetType, EventListener listener) {
		List<EventListener> selectedListeners = listeners.get(enevetType);
		selectedListeners.add(listener);
	}
	
	public void notify(final EventNum eventType) {
		listeners.get(eventType).forEach(l -> l.update(eventType));
	}
}
