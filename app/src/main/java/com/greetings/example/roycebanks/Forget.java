package com.greetings.example.roycebanks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Forget extends AppCompatActivity {

    DataBase db;
    String txt;
    String passwordxz,confirmxz;


    private EditText edt_account;
    private EditText edt_password;
    private EditText edt_confirm;
    private EditText edt_enter;

    private Button btn_back;
    private Button btn_send;
    private Button btn_submit;
    private TableLayout otp_l;
    private LinearLayout l_otp;
    private String id;
    private String rex;
    int tempxx=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forget);
        db = new DataBase(this);
        initcompo();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Forget.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ii=0;

                String from="mail-id-here";
                String sPAssword = "password-here";

                String account_i = edt_account.getText().toString();
                String password_i = edt_password.getText().toString();
                String confirm_i = edt_confirm.getText().toString();

                Cursor cur = db.get_data();

                while (cur.moveToNext()) {
                    String acc = cur.getString(3);
                    id = cur.getString(0);
                    String email = cur.getString(2);
                    String namexx = cur.getString(1);

                    if(account_i.equals("")||password_i.equals("")||confirm_i.equals(""))
                    {
                        Toast.makeText(Forget.this, "Enter all Fields", Toast.LENGTH_SHORT).show();
                    }

                    if (account_i.equals(acc)) {
                        tempxx++;

                            if (password_i.equals(confirm_i) == false) {
                                Toast.makeText(Forget.this, "Password not Matched", Toast.LENGTH_LONG).show();
                            }

                            if (password_i.equals(confirm_i) && account_i.equals(acc)) {

                                Randomotp();

                                Properties properties=new Properties();
                                properties.put("mail.smtp.auth","true");
                                properties.put("mail.smtp.starttls.enable","true");
                                properties.put("mail.smtp.host","smtp.gmail.com");
                                properties.put("mail.smtp.port","587");

                                Session session = Session.getInstance(properties, new Authenticator() {
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(from,sPAssword);
                                    }
                                });

                                try {

                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress(from));
                                    message.setRecipients(Message.RecipientType.TO,
                                            InternetAddress.parse(email));


                                    message.setSubject("Royce Banks - Forget Password !!!");
                                    message.setText("Dear "+ namexx+" as per your Forget Password request,\nYour OTP= "+ txt);


                                    new SendMail().execute(message);
                                    otp_l.setVisibility(View.VISIBLE);
                                    l_otp.setVisibility(View.VISIBLE);
                                    passwordxz=password_i;
                                    confirmxz=confirm_i;
                                    btn_send.setVisibility(View.INVISIBLE);
                                    break;


                                } catch (MessagingException e) {
                                    e.printStackTrace();
                                }

                                }

                            }
                }
                if(tempxx==0)
                {
                    Toast.makeText(Forget.this, "Account Number is Invalid.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cur = db.get_data();

                rex = edt_enter.getText().toString();
                if(rex.equals(txt))
                {
                    Boolean b = db.changePassword(passwordxz,confirmxz,id);
                    if(b==true)
                    {
                        Toast.makeText(Forget.this, "Password Changed Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Forget.this,MainActivity.class);
                        startActivity(intent);
                        edt_account.setText("");
                        edt_password.setText("");
                        edt_confirm.setText("");
                        edt_enter.setText("");
                    }
                    else{
                        Toast.makeText(Forget.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Forget.this, "wrong OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void initcompo () {

        otp_l = findViewById(R.id.f_t_otp);

        l_otp = findViewById(R.id.f_b_otp);


        edt_account = findViewById(R.id.f_e_account);
        edt_password = findViewById(R.id.f_e_password);
        edt_confirm = findViewById(R.id.f_e_confirm);
        edt_enter = findViewById(R.id.f_e_enter);

        btn_back = findViewById(R.id.f_b_back);
        btn_send = findViewById(R.id.f_b_send);
        btn_submit = findViewById(R.id.f_b_submit);

    }

    public void Randomotp(){
        Random ra=new Random();
        txt=String.valueOf(ra.nextInt(100000+1));

    }

    private class SendMail extends AsyncTask<Message,String,String> {

        private ProgressDialog progressDialog;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Forget.this,
                    "Please Wait", "Sending Mail...", true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Forget.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send Successfully \nPlease check your mail");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                builder.show();
            }else{
                Toast.makeText(Forget.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

}