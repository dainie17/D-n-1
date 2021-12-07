package com.example.test_du_an_mau.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_du_an_mau.Activity.DangNhapActivity;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

public class UserAcivity extends AppCompatActivity {
    private TextView tv_name, txt_ChinhSuaTrang;
    EditText edt_SoDienThoai, edt_DiaChiCN;
    String id;
    ImageView back, img_AnhNguoiDung1;
    private static final int REQUEST_IMAGE_OPEN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_acivity);

        tv_name = findViewById(R.id.tv_name);
        txt_ChinhSuaTrang = findViewById(R.id.txt_ChinhSuaTrang);
        back = findViewById(R.id.img_back_tcn);
        edt_SoDienThoai = findViewById(R.id.edt_SoDienThoaiCN);
        edt_DiaChiCN = findViewById(R.id.edt_DiaChiCN);
        img_AnhNguoiDung1 = findViewById(R.id.img_AnhNguoiDung1);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        id = mAuth.getUid();

        if(id == null){
            startActivity(new Intent(this, DangNhapActivity.class));
        } else {

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference fer = firebaseDatabase.getReference("Users");

            fer.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    if (user == null){
                        return;
                    }
                    tv_name.setText(user.getUsername());
                    edt_SoDienThoai.setText(user.getPhone());
                    edt_DiaChiCN.setText(user.getDiaChi());
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDung1);

                    txt_ChinhSuaTrang.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            FirebaseStorage storage = FirebaseStorage.getInstance("gs://asigment-a306b.appspot.com");
                            Calendar calendar = Calendar.getInstance();
                            final StorageReference storageRef = storage.getReference();
                            StorageReference mountainsRef = storageRef.child("image"+ calendar.getTimeInMillis() +".png");

                            img_AnhNguoiDung1.setDrawingCacheEnabled(true);
                            img_AnhNguoiDung1.buildDrawingCache();
                            Bitmap bitmap = ((BitmapDrawable) img_AnhNguoiDung1.getDrawable()).getBitmap();
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                            byte[] data = baos.toByteArray();

                            UploadTask uploadTask = mountainsRef.putBytes(data);
                            uploadTask.addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UserAcivity.this, "Lỗi tải ảnh lên !!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String sdt = edt_SoDienThoai.getText().toString().trim();
                                            String diachi = edt_DiaChiCN.getText().toString().trim();

                                            user.setPhone(sdt);
                                            user.setDiaChi(diachi);
                                            user.setImageURL(uri.toString().trim());

                                            fer.child(id).updateChildren(user.toMap(), new DatabaseReference.CompletionListener() {
                                                @Override
                                                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                                                }
                                            });

                                        }
                                    });
                                }
                            });

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        img_AnhNguoiDung1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_IMAGE_OPEN);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri full = data.getData();
            img_AnhNguoiDung1.setImageURI(full);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}