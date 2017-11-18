/**
 * Represents a Metric in the sort tester
 */
public class Metric {

    /**
     * The name of the test
     */
    private String name;

    /**
     * The duration of the test in millis
     */
    private Long timeInMillis;

    /**
     * The size of the test
     */
    private int size;

    /**
     * Error that occurs during execution
     */
    private String error;

    public Metric(String name, int size) {
        this.name = name;
        this.size = size;
        this.error = "";
    }

    /**
     * Starts the timer
     */
    public void start() {
        this.timeInMillis = System.currentTimeMillis();
    }

    /**
     * Stops the timer
     */
    public void stop() {
        this.timeInMillis = System.currentTimeMillis() - this.timeInMillis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTimeInMillis() {
        return timeInMillis;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return String.format("%1$-15s %2$,15d ms %3$s", this.name, this.timeInMillis, this.error);
    }
}
