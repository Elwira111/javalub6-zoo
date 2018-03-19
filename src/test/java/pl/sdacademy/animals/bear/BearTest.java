package pl.sdacademy.animals.bear;

import org.joda.time.DateTime;
import org.junit.Test;
import pl.sdacademy.animals.time.Clock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BearTest {

    @Test
    public void bearShouldNotBeAliveIfItHasNotEatenWithinLast10Days() {
        ClockFake clock = new ClockFake();
        Bear bear = new BlackBear(4, clock);
        bear.eat();
        clock.pushForwardByDays(11);

        assertTrue(!bear.isAlive());
    }

    //Not recommended - hard-wired to the number of executions of clock.getCurrentTime()
    @Test
    public void bearShouldNotBeAliveIfItHasNotEatenWithinLast10DaysTestedWithMock() {
        Clock clock = mock(Clock.class);
        Bear bear = new BlackBear(4, clock);
        when(clock.getCurrentTime()).thenReturn(DateTime.now());
        bear.eat();
        when(clock.getCurrentTime()).thenReturn(DateTime.now().plusDays(11));

        assertTrue(!bear.isAlive());
    }

    class ClockFake implements Clock {

        private DateTime currentTime = DateTime.now();

        @Override
        public DateTime getCurrentTime() {
            return currentTime;
        }

        public void pushForwardByDays(int days) {
            this.currentTime = currentTime.plusDays(days);
        }
    }
}
