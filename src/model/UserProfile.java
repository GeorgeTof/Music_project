package model;

public class UserProfile {
    private int id;
    private String username;
    private String nickname;

    @Override
    public String toString(){
        return this.id +": "+this.username+" ( "+this.nickname+" ) ";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
