package app.types;

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

}
