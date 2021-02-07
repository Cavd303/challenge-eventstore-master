package net.intelie.mock;

import net.intelie.challenges.Event;
import net.intelie.challenges.EventStore;
import net.intelie.impl.EventStoreImpl;


public class EventStoreMock {
  
   public static EventStore generateEventStore() {
    EventStore eventStore = new EventStoreImpl();
    
    Event event1 = new Event("Test", 2);
    Event event2 = new Event("Test", 1);
    Event event3 = new Event("Test", 8);
    Event event4 = new Event("Test2", 8);
    Event event5 = new Event("Test4", 1);
    Event event6 = new Event("Test5", 2);
    Event event7 = new Event("Test6", 4);
    Event event8 = new Event("Test2", 6);
    Event event9 = new Event("Test3", 18);
    Event event10 = new Event("Test3", 15);
    Event event11 = new Event("Test3", 7);
    
    eventStore.insert(event1);
    eventStore.insert(event2);
    eventStore.insert(event3);
    eventStore.insert(event4);
    eventStore.insert(event5);
    eventStore.insert(event6);
    eventStore.insert(event7);
    eventStore.insert(event8);
    eventStore.insert(event9);
    eventStore.insert(event10);
    eventStore.insert(event11);
    
    return eventStore;
  }
}
