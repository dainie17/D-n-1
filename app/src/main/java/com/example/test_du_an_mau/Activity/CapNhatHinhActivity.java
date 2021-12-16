package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test_du_an_mau.Adapter.HinhAnhAdapter;
import com.example.test_du_an_mau.Domian.ChonHinh;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class CapNhatHinhActivity extends AppCompatActivity {

    ImageView btn_ChonHinhChon, button_back_SuaHinh;
    RecyclerView rscv_HienAnhChon;
    Button btn_SuaHinhAnh;

    private static final int IMAGE_CODE = 1;
    private static final int MY_REQUEST_CODE = 10;

    private List<ChonHinh> modalClassList;
    private ArrayList<String> linkAnhList;
    HinhAnhAdapter hinhAnhAdapter;
    private ProgressDialog progressDialog;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_nhat_hinh);

        if (getIntent().getExtras() != null){

            SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("DuLieuSanPham");

            button_back_SuaHinh = findViewById(R.id.button_back_SuaHinh);
            btn_SuaHinhAnh = this.findViewById(R.id.btn_SuaHinhAnh);
            btn_ChonHinhChon = this.findViewById(R.id.btn_ChonHinhChon);
            rscv_HienAnhChon = this.findViewById(R.id.rscv_HienAnhChon);

            rscv_HienAnhChon.setHasFixedSize(true);
            rscv_HienAnhChon.setLayoutManager(new LinearLayoutManager(this));

            mStorageRef = FirebaseStorage.getInstance().getReference();

            modalClassList = new ArrayList<>();
            linkAnhList = new ArrayList<>();

            btn_ChonHinhChon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(intent, IMAGE_CODE);

                }
            });

            btn_SuaHinhAnh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent inten = new Intent(CapNhatHinhActivity.this, QuanLySanPhamActivity.class);

                        if (linkAnhList.size() != 0){

                            sanPhamDomian.setAlbumAnh(linkAnhList);

                            FirebaseDatabase database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                            DatabaseReference ref = database.getReference("SanPham");
                            ref.child(sanPhamDomian.getMaSP()).updateChildren(sanPhamDomian.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    startActivity(inten);
                                    finish();

                                }
                            });

                        } else {
                            Toast.makeText(CapNhatHinhActivity.this, "Bạn chưa chọn hình", Toast.LENGTH_SHORT).show();
                        }

                }
            });

            //        Quay Lại
            button_back_SuaHinh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });


        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK) {

            if (data.getClipData() != null) {

                int totalitem = data.getClipData().getItemCount();

                for (int i = 0; i < totalitem; i++) {

                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    String imagename = getFileName(imageUri);

                    ChonHinh modalClass = new ChonHinh(imagename,imageUri);
                    modalClassList.add(modalClass);


                    hinhAnhAdapter = new HinhAnhAdapter(CapNhatHinhActivity.this, modalClassList);
                    rscv_HienAnhChon.setAdapter(hinhAnhAdapter);

                    StorageReference mRef = mStorageRef.child(imagename);

                    mRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    linkAnhList.add(uri.toString().trim());

                                    btn_SuaHinhAnh.setVisibility(View.VISIBLE);

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CapNhatHinhActivity.this, "lỗi : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            } else if (data.getData() != null) {
                Toast.makeText(this, "Phải chọn nhiều hơn một hình !", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;

    }

}