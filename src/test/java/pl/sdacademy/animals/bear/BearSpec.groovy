package pl.sdacademy.animals.bear

import org.joda.time.DateTime
import pl.sdacademy.animals.time.Clock
import pl.sdacademy.animals.time.JodaClock
import spock.lang.Specification

class BearSpec extends Specification {

    def "Bear should be alive immediately after creation"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)

        when:
        boolean  result = bear.isAlive()

        then:
        result
    }

    def "Bear should be alive if it has eaten within 10 days"() {
        given:
        int weight = 3
        Bear bear = new BlackBear(weight)
        bear.eat()

        when:
        boolean result = bear.isAlive()

        then:
        result
    }

    def "Bear should not be alive if it has eaten within more than 10 days"() {
        given:
        def clock = Mock(Clock)
        def bear = new BlackBear(3, clock)
        bear.eat()
        clock.currentTime >> DateTime.now().plusDays(11)


        when:
        def result = bear.isAlive()

        then:
        !result
    }

}
