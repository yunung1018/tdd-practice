package tw.teddysoft.clean.usecase.workitem;

public class CycleTime {

    private long seconds;
    private long minutes;
    private long hours;
    private long days;

    public CycleTime(long diffDays, long diffHours, long diffMinutes, long diffMinutes1, long diffSeconds) {
        this.days = diffDays;
        this.hours = diffHours;
        this.minutes = diffMinutes;
        this.seconds = diffSeconds;
    }


    public long getDays() {
        return days;
    }

    public long getHours() {
        return hours;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getSeconds() {
        return seconds;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cycle time = ").append(getDays()).append(" days, ").append(hours).append(" hours, ").append(minutes).append(" minutes, ").append(seconds).append(" seconds.");
        return sb.toString();
    }
}
