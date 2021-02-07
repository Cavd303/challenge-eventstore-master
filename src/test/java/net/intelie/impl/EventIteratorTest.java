package net.intelie.impl;

import net.intelie.challenges.Event;
import net.intelie.challenges.EventIterator;
import net.intelie.challenges.EventStore;
import net.intelie.mock.EventStoreMock;
import org.junit.Assert;
import org.junit.Test;


public class EventIteratorTest {
  
  @Test
  public void moveNextFalse() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    EventIterator eventIterator = eventStore.query("Test10", 1, 2);
    
    Assert.assertFalse(eventIterator.moveNext());
  }
  
  @Test
  public void moveNextTrue() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    EventIterator eventIterator = eventStore.query("Test", 1, 2);
    
    Assert.assertTrue(eventIterator.moveNext());
  }
  
  @Test
  public void currentIllegalStateException() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    EventIterator eventIterator = eventStore.query("Test4", 1, 2);
    
    Assert.assertTrue(eventIterator.moveNext());
    try {
      eventIterator.moveNext();
      eventIterator.current();
      Assert.assertTrue(false);
    } catch(IllegalStateException e) {
      Assert.assertTrue(true);
    }
  }
  
  @Test
  public void currentEvent() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    EventIterator eventIterator = eventStore.query("Test4", 1, 2);
    eventIterator.moveNext();
    Event event = eventIterator.current();
    Assert.assertTrue(event.timestamp() == 1);
  }
  
  @Test
  public void removeEventError() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    EventIterator eventIterator = eventStore.query("Test4", 1, 2);
    
    try {
      eventIterator.remove();
      Assert.assertTrue(false);
    } catch(IllegalStateException e) {
      Assert.assertTrue(true);
    } 
  }
  
  @Test
  public void removeEvent() {
    EventStore eventStore = EventStoreMock.generateEventStore();
    EventIterator eventIterator = eventStore.query("Test4", 1, 2);
    eventIterator.moveNext();
    
    try {
      eventIterator.remove();
      eventIterator = eventStore.query("Test4", 1, 2);
      
      Assert.assertFalse(eventIterator.moveNext());
    } catch(IllegalStateException e) {
      Assert.assertTrue(false);
    } 
  }
}
