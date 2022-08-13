package com.greetings.example.roycebanks;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBase db;

    private Button btn_register;
    private Button btn_forget;
    private Button btn_login;
    private EditText edt_username;
    private EditText edt_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        db = new DataBase(this);


        initViews();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

        btn_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Forget.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_s = null;
                username_s = edt_username.getText().toString();
                String password_s = null;
                password_s = edt_password.getText().toString();

                if(username_s.equals("")||password_s.equals(("")))
                {
                    Toast.makeText(MainActivity.this, "Enter all fields", Toast.LENGTH_LONG).show();
                }
                else{
                    boolean chk = db.login_check(username_s,password_s);

                    if(chk==true){
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,Menu.class);
                        intent.putExtra("acc_p",username_s);
                        intent.putExtra("pass_p",password_s);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }

    private void initViews() {

        edt_username = findViewById(R.id.l_e_username);
        edt_password = findViewById(R.id.l_e_password);

        btn_forget = findViewById(R.id.l_b_forget);
        btn_login = findViewById(R.id.l_b_login);
        btn_register = findViewById(R.id.l_b_register);


    }


}