package dubiner.org.onehacksapp;

public class TimeAndValue {
    private long time;
    private float value;

    public TimeAndValue(long time, float value){
        this.time = time;
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public float getValue() {
        return value;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
