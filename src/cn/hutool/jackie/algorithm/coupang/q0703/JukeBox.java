package cn.hutool.jackie.algorithm.coupang.q0703;

import java.util.Set;

public class JukeBox {
    private CDPlayer cdPlayer;
    private User user;
    private Set<CD> cdCollection;
    private SongSelector ts;

    public JukeBox(CDPlayer cdPlayer, User user, Set<CD> cdCollection,
                   SongSelector ts) {
        super();
        this.cdPlayer = cdPlayer;
        this.user = user;
        this.cdCollection = cdCollection;
        this.ts = ts;
    }

    public Song getCurrentSong() {
        return ts.getCurrentSong();
    }

    public void setUser(User u) {
        this.user = u;
    }
}
