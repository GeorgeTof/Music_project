package model;

public class Review {
    private int rating;
    private String text;
    private String username;
    private String nickname;
    private int revId;

    public int getRevId() {
        return revId;
    }

    public void setRevId(int revId) {
        this.revId = revId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
