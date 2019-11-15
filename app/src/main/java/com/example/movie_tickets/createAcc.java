package com.example.movie_tickets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class createAcc extends AppCompatActivity {
    Button signup, confirm,upload,select;
    EditText e1, e11, e12, e13, e14;
    ImageView addphoto;
    String s11, s12, s13, s14;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        e1 = findViewById(R.id.mobile);
        addphoto=findViewById(R.id.addphoto);
        upload=findViewById(R.id.upload);
        select=findViewById(R.id.select);


        signup = findViewById(R.id.signup);
         confirm = findViewById(R.id.forward);
         e11=findViewById(R.id.sname);
         e12=findViewById(R.id.semail);
         e14=findViewById(R.id.password);
         //to hide  button
        upload.setVisibility(View.GONE);
         confirm.setVisibility(View.GONE);
         upload.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 uploadImage();
             }
         });

         addphoto.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 chooseImage();
                 upload.setVisibility(View.VISIBLE);

             }
         });
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
                upload.setVisibility(View.VISIBLE);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                s11 = e11.getText().toString().replace(" ", "");
                s12 = e12.getText().toString().replace(" ", "");
                s13 = e1.getText().toString().replace(" ", "");
                s14 = e14.getText().toString().replace(" ", "");

                if(s11.length()==0)

                {
                    e11.requestFocus();
                    e11.setError("FIELD CANNOT BE EMPTY");
                }

                else if(!s11.matches("[a-zA-Z ]+"))
                {
                    e11.requestFocus();
                    e11.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }

                else if(s12.length()==0)
                {
                    e12.requestFocus();
                    e12.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!s12.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))
                {
                    e12.requestFocus();
                    e12.setError("ENTER VALID EMAIL ID");
                }

               else if(s13.length()==0)

                {
                    e1.requestFocus();
                    e1.setError("FIELD CANNOT BE EMPTY");
                }
                else if(s13.length()>=10)

                {
                    e1.requestFocus();
                    e1.setError("INVALID NUMBER");
                }
                else if(s14.length()==0)

                {
                    e14.requestFocus();
                    e14.setError("FIELD CANNOT BE EMPTY");
                }

              else
                {
                //   uploadImage();
                    reg1();
                    getEmail();
                   // .setVisibility(View.GONE);
                    confirm.setVisibility(View.VISIBLE);

                    signup.setVisibility(View.GONE);

                }


            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///email


        ///send


               /* String mobile = e1.getText().toString().trim();
                String name = e11.getText().toString().trim();
                String  email= e13.getText().toString().trim();
               */ Intent i = new Intent(createAcc.this, movie.class);
                i.putExtra("name",s11);
                i.putExtra("email",s12);
                i.putExtra("mobile",s13);
               // i.putExtra("mobile", mobile);
                startActivity(i);
                finish();
            }
        });
    }

    public void reg1() {
        RequestQueue rq = Volley.newRequestQueue(createAcc.this);
        String url = "https://studentmanagement924230.000webhostapp.com/movie/register.php?";
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(createAcc.this, "Register!!!login it.", Toast.LENGTH_SHORT).show();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(createAcc.this, "data not send " + error.toString() + "", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hm = new HashMap<String, String>();
                hm.put("n", s11);
                hm.put("e", s12);
                hm.put("m", s13);
                hm.put("p", s14);
                return hm;
            }
        };
        rq.add(sr);
    }
/////CHOOSE IMAGE
private void chooseImage() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                addphoto.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }




    private void uploadImage() {

        if (filePath != null) {
          /*  final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
*///send image
            StorageReference ref = storageReference.child(UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //    progressDialog.dismiss();
                            Toast.makeText(createAcc.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure( Exception e) {
                           // progressDialog.dismiss();
                            Toast.makeText(createAcc.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                         //   progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
    public void getEmail()
    {
        new Thread(new Runnable() {

            public void run() {

                try {

                    GMailSender sender = new GMailSender(

                            "mkchouhan2016@gmail.com",

                            "Lakshar@1");



                //    sender.addAttachment(Environment.getExternalStorageDirectory().getPath()+"/image.jpg");
                    s12 = e12.getText().toString().replace(" ", "");


                    sender.sendMail("VR cinema", "This mail has been sent from VR cinema about your register for VR cinema!!!"+"Welcome to our cinema",

                            "mkchouhan2016@gmail.com",

                            s12);

                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();



                }

            }

        }).start();
 }

}
