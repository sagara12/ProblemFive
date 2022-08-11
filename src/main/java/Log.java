public class Log {

    private String log;
    private int count;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Log(String log, int count) {
        this.log = log;
        this.count = count;
    }
}
