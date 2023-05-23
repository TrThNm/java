package com.example.appandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {

    public static final String DATABASE_NAME="AppPhim.db";
    SQLiteDatabase db;
    EditText edtName,edtPass;
    Button btnsignin,btnsignup;
    private CheckBox chkHienThiPass;
//    private boolean isTableExists(SQLiteDatabase database,String tableName){
//        Cursor cursor=database.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name "+"= '"+tableName+"'",null);
//        if(cursor!=null){
//            if(cursor.getCount()>0){
//                cursor.close();
//                return true;
//            }
//            cursor.close();
//        }
//        return false;
//    }
    private boolean isUser(String username,String password){
        try{
            db=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
            Cursor c=db.rawQuery("select * from User where Email=? and Password=?",new String[]{username,password});
            c.moveToFirst();
            if(c.getCount()>0){
                return true;
            }
        }
        catch (Exception ex){
            Toast.makeText(this, "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        edtName=(EditText) findViewById(R.id.edtName);
        edtPass=(EditText) findViewById(R.id.edtPass);
        btnsignin=(Button) findViewById(R.id.btnSignin);
        btnsignup=(Button) findViewById(R.id.btnSignup);
        chkHienThiPass=(CheckBox) findViewById(R.id.chkHienThiPass);
        chkHienThiPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edtPass.setTransformationMethod(null);
                }
                else{
                    edtPass.setTransformationMethod( new PasswordTransformationMethod());
                }
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtName.getText().toString();
                String password=edtPass.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(Signin.this, "Xin vui lòng nhập tài khoản", Toast.LENGTH_SHORT).show();
                    edtName.requestFocus();
                } else if (password.isEmpty()) {
                    Toast.makeText(Signin.this, "Xin vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    edtPass.requestFocus();
                } else if (isUser(edtName.getText().toString(),edtPass.getText().toString())) {
                    Intent intent= new Intent(Signin.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Signin.this, "Tài khoản hoặc mật khẩu không chính xác !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Signin.this,Signup.class);
                startActivity(intent);
            }
        });
    }
}