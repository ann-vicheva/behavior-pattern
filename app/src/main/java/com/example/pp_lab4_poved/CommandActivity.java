package com.example.pp_lab4_poved;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CommandActivity extends AppCompatActivity {

    interface Command {
        void execute();
    }
    class Receiver {
        public void switchOn(){
            tv.setText(tv.getText()+"\n"+"Switch on from:"+this.getClass().getSimpleName());
            //System.out.println("Switch on from:"+this.getClass().getSimpleName());
        }
    }
    class OnCommand implements Command{
        private Receiver receiver;

        public OnCommand(Receiver receiver){
            this.receiver = receiver;
        }
        public void execute(){
            receiver.switchOn();
        }
    }
    class Invoker {
        private Command command;

        public Invoker(Command command){
            this.command = command;
        }
        public void execute(){
            this.command.execute();
        }
    }

    class TV extends Receiver{

        public String toString(){
            return this.getClass().getSimpleName();
        }
    }
    class DVDPlayer extends Receiver{

        public String toString(){
            return this.getClass().getSimpleName();
        }
    }

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        Receiver receiver = new TV();
        Command onCommand = new OnCommand(receiver);
        Invoker invoker = new Invoker(onCommand);
        invoker.execute();

        // On command for DVDPlayer with same invoker
        receiver = new DVDPlayer();
        onCommand = new OnCommand(receiver);
        invoker = new Invoker(onCommand);
        invoker.execute();
    }

    public void clear(View view){
        tv.setText("");
    }
}
