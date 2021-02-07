package net.intelie.impl;

import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import net.intelie.challenges.EventStore;
import net.intelie.mock.EventStoreMock;
import org.junit.Assert;
import org.junit.Test;


public class EventStoreTest {
  
  @Test
  public void insertNullEvent() {
    Event event = null;
    EventStore eventStore = new EventStoreImpl();
    try {
      eventStore.insert(event);
      Assert.assertTrue(false);
    } catch(NullPointerException e) {
      Assert.assertTrue(e.getMessage().equals("event cannot be null"));
    }
  }
  
  @Test
  public void insertNullEventType() {
    Event event = new Event(null, 0);
    EventStore eventStore = new EventStoreImpl();
    try {
      eventStore.insert(event);
      Assert.assertTrue(false);
    } catch(NullPointerException e) {
      Assert.assertTrue(e.getMessage().equals("event.type cannot be null"));
    }
  }
  
  @Test
  public void insertFirstEventType() {
    Event event = new Event("Test", 0);
    EventStore eventStore = new EventStoreImpl();
    
    try {
      eventStore.insert(event);
      Assert.assertTrue(true);
    } catch(NullPointerException e) {
      Assert.assertTrue(false);
    }
  }
  
  @Test
  public void insertSameEventType() {
    Event event1 = new Event("Test", 0);
    Event event2 = new Event("Test", 0);
    EventStore eventStore = new EventStoreImpl();
    
    try {
      eventStore.insert(event1);
      eventStore.insert(event2);
      Assert.assertTrue(true);
    } catch(NullPointerException e) {
      Assert.assertTrue(false);
    }
  }
  
  @Test
  public void removeNullEventType() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    
    try {
      eventStore.removeAll(null);
      
      Assert.assertTrue(false);
    } catch(NullPointerException e) {
      Assert.assertTrue(e.getMessage().equals("type cannot be null"));
    }
  }
  
  @Test
  public void removeEventType() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    
    try {
      eventStore.removeAll("Test");
      EventIterator eventIterator = eventStore.query("Test", 0, 10);
      Assert.assertFalse(eventIterator.moveNext());
    } catch(NullPointerException e) {
      Assert.assertTrue(false);
    }
  }
  
  @Test
  public void queryNullEventType() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    
    try {
      EventIterator eventIterator = eventStore.query(null, 0, 10);
      Assert.assertTrue(false);
    } catch(NullPointerException e) {
      Assert.assertTrue(e.getMessage().equals("type cannot be null"));
    }
  }
  
  @Test
  public void queryEventType() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    
    try {
      EventIterator eventIterator = eventStore.query("Test", 1, 2);
      //Has 1 event
      Assert.assertTrue(eventIterator.moveNext());
      //No more event in query
      Assert.assertFalse(eventIterator.moveNext());
    } catch(NullPointerException e) {
      Assert.assertTrue(false);
    }
  }
 
}
