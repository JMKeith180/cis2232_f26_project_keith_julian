package ca.hccis.a2.bo;

import ca.hccis.a2.entity.DayPlanner;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;

public class DayPlannerBO {

    private static final double MINUTES_PER_DAY = 24 * 60;

    private final Clock clock;

    public DayPlannerBO(){
        this(Clock.systemDefaultZone());
    }

    public DayPlannerBO(Clock clock){
        this.clock = clock;
    }

    public double calculate(DayPlanner event){
        if (event == null) {
            throw new IllegalArgumentException("Event must not be null");
        }
        LocalDateTime eventTime = LocalDateTime.of(event.getYear(), event.getMonth(), event.getDay(), event.getHour(), event.getMinute());
        long minutesAway = Duration.between(LocalDateTime.now(clock),eventTime).toMinutes();
        return minutesAway / MINUTES_PER_DAY;
    }



}