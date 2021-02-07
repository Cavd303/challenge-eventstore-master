package net.intelie.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import net.intelie.challenges.EventStore;


public class EventStoreImpl implements EventStore {

  //Why map? Type is necessary in removeAll and query.
  //Each type contans list of event
  private Map<String, List<Event>>  eventTypeMap;
  
  public EventStoreImpl() {
    //ConcurrentHashMap is thread safe
    eventTypeMap = new ConcurrentHashMap<>();
  }
  
  @Override
  public synchronized void insert(Event event) {
    if(Objects.isNull(event)) {
      throw new NullPointerException("event cannot be null");
    } 
    if(Objects.isNull(event.type())) {
      throw new NullPointerException("event.type cannot be null");
    }
    
    if(eventTypeMap.containsKey(event.type())) {
      eventTypeMap.get(event.type()).add(event);
    } else {
      List<Event> eventList = new Vector<>();
      eventList.add(event);
      
      eventTypeMap.put(event.type(), eventList);
    }
  }

  @Override
  public synchronized void removeAll(String type) {
    if(Objects.isNull(type)) {
      throw new NullPointerException("type cannot be null");
    }
    eventTypeMap.remove(type);
  }

  @Override
  public synchronized EventIterator query(String type, long startTime, long endTime) {
    if(Objects.isNull(type)) {
      throw new NullPointerException("type cannot be null");
    }
    
    List<Event> eventList = eventTypeMap.get(type);
    List<Event> indexList = new ArrayList<>();
    
    if(eventList == null) {
      return new EventIteratorImpl(eventTypeMap, type, indexList);
    }
    
    for(int i = 0; i < eventList.size(); i++) {
      Event event = eventList.get(i);
      
      if(event.timestamp() >= startTime && event.timestamp() < endTime) {
        indexList.add(event);
      }
    }
    
    return new EventIteratorImpl(eventTypeMap, type, indexList);
  }
  
  
}
