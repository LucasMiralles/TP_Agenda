package agenda;

import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {

    protected ChronoUnit frequency;
    protected HashSet<LocalDate> lesRepEvents;

    /**
     * Constructs a repetitive event
     *
     * @param title     the title of this event
     * @param start     the start of this event
     * @param duration  myDuration in seconds
     * @param frequency one of :
     *                  <UL>
     *                  <LI>ChronoUnit.DAYS for daily repetitions</LI>
     *                  <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     *                  <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     *                  </UL>
     */
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
        this.lesRepEvents = new HashSet<LocalDate>();
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        lesRepEvents.add(date);
    }

    public HashSet<LocalDate> getException() {
        return this.lesRepEvents;
    }
        /**
         *
         * @return the type of repetition
         */
        public ChronoUnit getFrequency () {
            return frequency;
        }
    public boolean isInDay(LocalDate aDay) {
        if(this.lesRepEvents.contains(aDay)){
            return false;
        }
        if(this.frequency == ChronoUnit.DAYS){
            return true;
        }
        if((aDay.getDayOfYear() == myStart.getDayOfYear() &&  aDay.getYear() == myStart.getYear()) || (aDay.getDayOfYear() == myStart.plus(myDuration).getDayOfYear() && aDay.getYear() == myStart.plus(myDuration).getYear()) || (aDay.isAfter(ChronoLocalDate.from(myStart)) && aDay.isBefore(ChronoLocalDate.from(myStart.plus(myDuration))))
        ){
            return true;
        }
        return false;
    }

    }

