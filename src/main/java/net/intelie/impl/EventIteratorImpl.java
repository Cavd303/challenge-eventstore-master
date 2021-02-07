package net.intelie.impl;

import java.util.List;
import java.util.Map;
import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;


public class EventIteratorImpl implements EventIterator {
  
  //List of event finded in query
  private final List<Event> eventList;
  //Type of event
  private final String type;
  //Original data structure contains all events
  private final Map<String, List<Event>>  eventTypeMap;
  //current index off iterator
  private int currentIndex;
  

  public EventIteratorImpl(Map<String, List<Event>> eventTypeMap, String type, List<Event> eventList) {
    this.eventTypeMap = eventTypeMap;
    this.type = type;
    this.eventList = eventList;
    currentIndex = -1;
  }
  
  @Override
  public boolean moveNext() {
    currentIndex++;
    return eventList.size() > currentIndex;
  }

  @Override
  public Event current() {
    verifyMoveNextCalled();
    
    if(eventList.size() <= currentIndex) {
      throw new IllegalStateException();
    }
    
    return eventList.get(currentIndex);
  }

  @Override
  public synchronized void remove() {

    verifyMoveNextCalled();


    Event event = eventList.get(currentIndex);

    if(eventTypeMap.containsKey(type) && eventTypeMap.get(type).contains(event)) {
      eventTypeMap.get(type).remove(event);
    }
  }

  @Override
  public void close() throws Exception {
  }
  
  private void verifyMoveNextCalled() throws IllegalStateException {
    if(currentIndex < 0) {
      throw new IllegalStateException();
    }
  }
  
}
