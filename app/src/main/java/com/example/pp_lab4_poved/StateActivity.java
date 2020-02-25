package com.example.pp_lab4_poved;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity {

    //------------------
    public abstract class State {
        Player player;

        /**
         * Контекст передаёт себя в конструктор состояния, чтобы состояние могло
         * обращаться к его данным и методам в будущем, если потребуется.
         */
        State(Player player) {
            this.player = player;
        }

        public abstract String onLock();
        public abstract String onPlay();
        public abstract String onNext();
        public abstract String onPrevious();
    }
    //------------------
    public class LockedState extends State {

        LockedState(Player player) {
            super(player);
            player.setPlaying(false);
        }

        @Override
        public String onLock() {
            if (player.isPlaying()) {
                player.changeState(new ReadyState(player));
                return "Stop playing";
            } else {
                return "Locked...";
            }
        }

        @Override
        public String onPlay() {
            player.changeState(new ReadyState(player));
            return "Ready";
        }

        @Override
        public String onNext() {
            return "Locked...";
        }

        @Override
        public String onPrevious() {
            return "Locked...";
        }
    }
    //------------------
    public class ReadyState extends State {

        public ReadyState(Player player) {
            super(player);
        }

        @Override
        public String onLock() {
            player.changeState(new LockedState(player));
            return "Stop playing";
            //return player.;
        }

        @Override
        public String onPlay() {
            String action = player.startPlayback();
            player.changeState(new PlayingState(player));
            return action;
        }

        @Override
        public String onNext() {
            //return "Locked...";
            return player.nextTrack();
        }

        @Override
        public String onPrevious() {
            //return "Locked...";
            return player.previousTrack();
        }
    }
    //------------------
    public class PlayingState extends State {

        PlayingState(Player player) {
            super(player);
        }

        @Override
        public String onLock() {
            player.changeState(new LockedState(player));
            player.setCurrentTrackAfterStop();
            return "Stop playing";
        }

        @Override
        public String onPlay() {
            player.changeState(new ReadyState(player));
            return "Paused...";
        }

        @Override
        public String onNext() {
            return player.nextTrack();
        }

        @Override
        public String onPrevious() {
            return player.previousTrack();
        }
    }
    //------------------
    public class Player {
        private State state;
        private boolean playing = false;
        private List<String> playlist = new ArrayList<>();
        private int currentTrack = 0;

        public Player() {
            this.state = new ReadyState(this);
            setPlaying(true);
            for (int i = 1; i <= 12; i++) {
                playlist.add("Track " + i);
            }
        }

        public void changeState(State state) {
            this.state = state;
        }

        public State getState() {
            return state;
        }

        public void setPlaying(boolean playing) {
            this.playing = playing;
        }

        public boolean isPlaying() {
            return playing;
        }

        public String startPlayback() {
            return "Playing " + playlist.get(currentTrack);
        }

        public String nextTrack() {
            currentTrack++;
            if (currentTrack > playlist.size() - 1) {
                currentTrack = 0;
            }
            return "Playing " + playlist.get(currentTrack);
        }

        public String previousTrack() {
            currentTrack--;
            if (currentTrack < 0) {
                currentTrack = playlist.size() - 1;
            }
            return "Playing " + playlist.get(currentTrack);
        }

        public void setCurrentTrackAfterStop() {
            this.currentTrack = 0;
        }
    }
    //------------------
    //------------------
    //------------------
    //------------------
    //------------------

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void play(View view){
        Player player = new Player();
        tv.setText(tv.getText()+"\n"+player.getState().onPlay());
    }
    public void stop(View view){
        Player player = new Player();
        tv.setText(tv.getText()+"\n"+player.getState().onLock());
    }
    public void next(View view){
        Player player = new Player();
        tv.setText(tv.getText()+"\n"+player.getState().onNext());
    }
    public void prev(View view){
        Player player = new Player();
        tv.setText(tv.getText()+"\n"+player.getState().onPrevious());
    }


}
