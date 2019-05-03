package com.student.admin.easycalls;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.student.admin.easycalls.shared.sharedpreff;

import static android.Manifest.permission.CALL_PHONE;

public  class Dashboard  extends AppCompatActivity {

    LinearLayout ff,clientlocaion,employee,register,add;
    Button login1, singup;
    EditText ed1, ed2;
    static final Integer LOCATION = 0x1;
   int  REQUEST_PHONE_CALL=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.dashboard);
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);
        askForPermission(Manifest.permission.CALL_PHONE,REQUEST_PHONE_CALL);

//        if (ContextCompat.checkSelfPermission(Dashboard.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(Dashboard.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
//        }
//        else
//        {
////            startActivity(intent);
//        }

        LinearLayout button = findViewById(R.id.set);
        LinearLayout client = findViewById(R.id.client);
        employee=findViewById(R.id.clientlocation);
        clientlocaion=findViewById(R.id.emplocation);
        add=findViewById(R.id.add);
        register=findViewById(R.id.emplocation1);
        String userid= new sharedpreff(getApplicationContext()).getEmail();

        if(userid.equals("3")){

            clientlocaion.setVisibility(View.VISIBLE);
            employee.setVisibility(View.GONE);
            register.setVisibility(View.VISIBLE);
            add.setVisibility(View.GONE);

        }else if(userid.equals("2")){

            employee.setVisibility(View.VISIBLE);
            clientlocaion.setVisibility(View.GONE);
            register.setVisibility(View.GONE);
            add.setVisibility(View.VISIBLE);
        }
add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Toast.makeText(Dashboard.this, "under construction ", Toast.LENGTH_SHORT).show();
    }
});

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), employeelist.class);
                startActivity(intent);
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), executelist.class);
                startActivity(intent);

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getApplicationContext(),Register.class);
                startActivity(i);
                overridePendingTransition(R.anim.in,R.anim.out);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
   //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_user){

//           new sharedpreff(getApplicationContext()).logout1();
//           Intent i=new Intent(this,DashActivity.class);
//           startActivity(i);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Dashboard.this);
            builder1.setMessage("Sure Logout  ");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            new sharedpreff(getApplicationContext()).logout1();
                            Intent i = new Intent(Dashboard.this, MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(i);
                            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                        }
                    }
                    );

            builder1.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    } 
                           );

            AlertDialog alert11 = builder1.create();
            alert11.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void onBackPressed() {
//
        AlertDialog alertDialog = new AlertDialog.Builder(Dashboard.this).create();
        alertDialog.setTitle("Info");
        alertDialog.setMessage("Sure exit  app" );
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();

                    }
                });
        alertDialog.show();



    }

    private void askForPermission(String permission, Integer requestCode ) {

        if (ContextCompat.checkSelfPermission(Dashboard.this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(Dashboard.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(Dashboard.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(Dashboard.this, new String[]{permission}, requestCode);
            }

        } else {
//
//            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}