package io.selendroid.server.model.impl;

import io.selendroid.server.model.EmulatorPortFinder;
import io.selendroid.server.model.impl.DefaultPortFinder;

import org.junit.Assert;
import org.junit.Test;

public class DefaultPortFinderTests {
  @Test
  public void assertTestIsAbleToGetNextIntOfRange() {
    EmulatorPortFinder finder = new DefaultPortFinder();

    for (int i = EmulatorPortFinder.MIN_PORT; i <= EmulatorPortFinder.MAX_PORT; i += 2) {
      Assert.assertEquals(i, finder.next().intValue());
    }

    Assert.assertNull(finder.next());
  }

  @Test
  public void assertTestIsAbleToReleasePorts() {
    EmulatorPortFinder finder = new DefaultPortFinder();

    Assert.assertEquals(5554, finder.next().intValue());
    Assert.assertEquals(5556, finder.next().intValue());
    Assert.assertEquals(5558, finder.next().intValue());
    finder.release(5554);
    Assert.assertEquals(5554, finder.next().intValue());
  }

  @Test
  public void assertTestIsNotAbleToAddPortNumberHigherThanMaxValue() {
    EmulatorPortFinder finder = anEmptyFinder();
    finder.release(EmulatorPortFinder.MAX_PORT + 2);
    Assert.assertNull(finder.next());
  }

  @Test
  public void assertTestIsNotAbleToAddPortNumberHigherThanMinValue() {
    EmulatorPortFinder finder = anEmptyFinder();
    finder.release(EmulatorPortFinder.MIN_PORT - 2);
    Assert.assertNull(finder.next());
  }

  @Test
  public void assertTestIsNotAbleToAddOddPortNumber() {
    EmulatorPortFinder finder = anEmptyFinder();
    finder.release(EmulatorPortFinder.MIN_PORT + 1);
    Assert.assertNull(finder.next());
  }

  private EmulatorPortFinder anEmptyFinder() {
    EmulatorPortFinder finder = new DefaultPortFinder();

    for (int i = EmulatorPortFinder.MIN_PORT; i <= EmulatorPortFinder.MAX_PORT; i += 2) {
      finder.next();
    }
    return finder;
  }
}
