package com.example.pp_lab4_poved;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TemplateActivity extends AppCompatActivity {


    public abstract class Network {
        String userName;
        String password;

        Network() {}

        /**
         * Публикация данных в любой сети.
         */
        public boolean post(String message) {
            // Проверка данных пользователя перед постом в соцсеть. Каждая сеть для
            // проверки использует разные методы.
            if (logIn(this.userName, this.password)) {
                // Отправка данных.
                boolean result =  sendData(message.getBytes());
                logOut();
                return result;
            }
            return false;
        }

        abstract boolean logIn(String userName, String password);
        abstract boolean sendData(byte[] data);
        abstract void logOut();
    }
    //--------------------
    public class Facebook extends Network {
        public Facebook(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public boolean logIn(String userName, String password) {
            tv.setText(tv.getText()+"\n"+"Checking user's parameters"+
                    "\n"+"Name: " + this.userName+"\n"+"Password: ");
            //System.out.println("\nChecking user's parameters");
            //System.out.println("Name: " + this.userName);
            //System.out.print("Password: ");
            for (int i = 0; i < this.password.length(); i++) {
                tv.setText(tv.getText()+"*");
                //System.out.print("*");
            }
            simulateNetworkLatency();
            tv.setText(tv.getText()+"\n"+"LogIn success on Facebook");
            //System.out.println("\nLogIn success on Facebook");
            return true;
        }

        public boolean sendData(byte[] data) {
            boolean messagePosted = true;
            if (messagePosted) {
                tv.setText(tv.getText()+"\n"+"Message: '" + new String(data) + "' was posted on Facebook");
                //System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
                return true;
            } else {
                return false;
            }
        }

        public void logOut() {
            tv.setText(tv.getText()+"\n"+"User: '" + userName + "' was logged out from Facebook");
            //System.out.println("User: '" + userName + "' was logged out from Facebook");
        }

        private void simulateNetworkLatency() {
            try {
                int i = 0;
                System.out.println();
                while (i < 10) {
                    //System.out.print(".");
                    Thread.sleep(50);
                    i++;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    //------------------
    public class Twitter extends Network {

        public Twitter(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        public boolean logIn(String userName, String password) {
            tv.setText(tv.getText()+"\n"+"Checking user's parameters"+
                    "\n"+"Name: " + this.userName+"\n"+"Password: ");
            //System.out.println("\nChecking user's parameters");
            //System.out.println("Name: " + this.userName);
            //System.out.print("Password: ");
            for (int i = 0; i < this.password.length(); i++) {
                tv.setText(tv.getText()+"*");
                //System.out.print("*");
            }
            simulateNetworkLatency();
            tv.setText(tv.getText()+"\n"+"LogIn success on Twitter");
            //System.out.println("\n\nLogIn success on Twitter");
            return true;
        }

        public boolean sendData(byte[] data) {
            boolean messagePosted = true;
            if (messagePosted) {
                tv.setText(tv.getText()+"\n"+"Message: '" + new String(data) + "' was posted on Twitter");
                //System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
                return true;
            } else {
                return false;
            }
        }

        public void logOut() {
            tv.setText(tv.getText()+"\n"+"User: '" + userName + "' was logged out from Twitter");
            //System.out.println("User: '" + userName + "' was logged out from Twitter");
        }

        private void simulateNetworkLatency() {
            try {
                int i = 0;
                System.out.println();
                while (i < 10) {
                    System.out.print(".");
                    Thread.sleep(500);
                    i++;
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    //----------------



    CheckBox cb1;
    CheckBox cb2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template);
        cb1=(CheckBox)findViewById(R.id.first_obj);
        cb2=(CheckBox)findViewById(R.id.second_obj);
        tv=(TextView)findViewById(R.id.tv);
    }

    public void create(View view){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        String userName = "Jane";
        String password = "1234";
        String message = "Hello, world!";
        if (cb1.isChecked()) {
            network = new Facebook(userName, password);
        } else if (cb2.isChecked()) {
            network = new Twitter(userName, password);
        }
        network.post(message);
    }

    public  void clear(View view){
        tv.setText("");
    }

}
