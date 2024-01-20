package model;

public enum DBcredentials {
    URL("jdbc:postgresql://localhost:5432/projectmusic1"),
    USER("postgres"),
    PASSWORD("postgres");

    final public String value;

    DBcredentials (String val){
        this.value = val;
    }

}
