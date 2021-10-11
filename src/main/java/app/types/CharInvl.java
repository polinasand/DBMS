package app.Types;

public class CharInvl {
    private final char DEFAULT_BEGIN = 'a';
    private final char DEFAULT_END = 'z';
    private char begin;
    private char end;

    public CharInvl() {
        this.begin = DEFAULT_BEGIN;
        this.end = DEFAULT_END;
    }
    public CharInvl(char begin, char end) {
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
    public char end() {
        return this.end;
    }
    public boolean contains(char other) {
        return this.begin <= other && this.end >= other;
    }
    public boolean equals(CharInvl other) {
        return this.begin == other.begin() && this.end == other.end();
    }
    public boolean contains(CharInvl other ) {
        return this.begin <= other.begin() && this.end >= other.end();
    }

    static public CharInvl parseString(String value) {
        if (value.length() !=5 || !(value.charAt(0) == '[' && value.charAt(value.length()-1) == ']'))
            return null;
        String[] nums = value.substring(1, value.length()-1).split(";");
        if (nums[0].charAt(0) > nums[1].charAt(0))
            return null;
        return new CharInvl(nums[0].charAt(0), nums[1].charAt(0));
    }

}
