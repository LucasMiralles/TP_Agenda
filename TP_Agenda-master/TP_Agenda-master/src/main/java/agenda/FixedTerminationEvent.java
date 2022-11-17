package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;
/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {

    private LocalDate terminationInclusive;
    private long numberOfOccurences;
    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
         super(title, start, duration, frequency);
        this.terminationInclusive = terminationInclusive;

    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
       this.numberOfOccurences = numberOfOccurrences;
    }

    /**
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        if(Objects.isNull(terminationInclusive)){
            LocalDateTime compteur = this.getStart();
            for(int i = 1; i < this.getNumberOfOccurrences(); i++){
                compteur = compteur.plus(this.getFrequency().getDuration());
            }
            return compteur.toLocalDate();
        }
        return terminationInclusive;
    }



    public long getNumberOfOccurrences() {
        long compteur = 0;
        if (terminationInclusive == null) {
            return numberOfOccurences;
        } else {
         compteur = frequency.between(myStart, terminationInclusive);
         return compteur;
            }
        }

    public boolean isInDay(LocalDate day){
        LocalDate date = this.getStart().toLocalDate();
        while(date.isBefore(ChronoLocalDate.from(this.getTerminationDate()))){
            if(this.getException().contains(day)){
                return false;
            }
            if(date.equals(day)){
                return true;
            }
            if(this.getFrequency() == ChronoUnit.DAYS){
                date = date.plusDays(1);
            }
        }
        return false;
    }
}
        

