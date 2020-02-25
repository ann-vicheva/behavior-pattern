package com.example.pp_lab4_poved;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MementoActivity extends AppCompatActivity {

    //-----------------
    public class Memento {
        private String state;
        public Memento(String newState){
            state=newState;
        }
        public String getState(){
            return state;
        }
    }
    //-----------------
    public class Caretaker {
        private Memento memento;
        public Memento getMemento() {
            return memento;
        }
        public void setMemento(Memento newMemento) {
            memento = newMemento;
        }
    }
    //-----------------
    public class Originator {
        private String state;
        public String getState() {
            return state;
        }
        public void setState(String newState) {
            state = newState;
        }
        public Memento createMemento(){
            return new Memento(state);
        }
        public void setMemento(Memento memento){
            state=memento.getState();
        }
    }
    //-----------------

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memento);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        Originator originator = new Originator();
        originator.setState("Ok!");
        tv.setText(tv.getText()+"\n"+originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("Error!");
        tv.setText(tv.getText()+"\n"+originator.getState());
        originator.setMemento(caretaker.getMemento());
        tv.setText(tv.getText()+"\n"+originator.getState());
    }

    public void clear(View view){
        tv.setText("");
    }

}
