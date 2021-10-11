package app.Types;

public class IntInvl {
    private final int DEFAULT_BEGIN = 0;
    private final int DEFAULT_END = 10;
    private int begin;
    private int end;

    public IntInvl() {
        this.begin = DEFAULT_BEGIN;
        this.end = DEFAULT_END;
    }
    public IntInvl(int begin, int end) {
        if (begin > end) {
            this.begin = DEFAULT_BEGIN;
            this.end = DEFAULT_END;
            return;
        }
        this.begin = begin;
        this.end = end;
    }
    public String toString() {
        return "[" + this.begin+";"+this.end+"]";
    }
    public int begin() {
        return this.begin;

    }
    public int end() {
        return this.end;
    }
    public boolean contains(int other) {
        return this.begin <= other && this.end >= other;
    }
    public boolean equals(IntInvl other) {
        return this.begin == other.begin() && this.end == other.end();
    }
    public boolean contains(IntInvl other ) {
        return this.begin <= other.begin() && this.end >= other.end();
    }

    static public IntInvl parseString(String value) {
        if (value.length() < 3 || !(value.charAt(0) == '[' && value.charAt(value.length()-1) == ']'))
            return null;
        String[] nums = value.substring(1, value.length()-1).split(";");
        int begin, end;
        try {
            begin = Integer.parseInt(nums[0]);
            end = Integer.parseInt(nums[1]);
            if (begin > end) return null;

        } catch (Exception e) {
            return null;
        }
        return new IntInvl(begin, end);
    }

}
