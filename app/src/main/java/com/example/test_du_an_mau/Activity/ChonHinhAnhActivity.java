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
import com.example.test_du_an_mau.Domian.LinkAnh;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChonHinhAnhActivity extends AppCompatActivity {

    Button btn_TiepTucThemHA;
    ImageView back,btn_ChonHinhAnh;
    RecyclerView rscv_HienHinhAnh;

    private static final int IMAGE_CODE = 1;
    private static final int MY_REQUEST_CODE = 10;

    private List<ChonHinh> modalClassList;
    private ArrayList<String> linkAnhList;
    HinhAnhAdapter hinhAnhAdapter;
    ProgressDialog dialog;

    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_hinh_anh);


        back = findViewById(R.id.button_back_ChonHinh);
        btn_TiepTucThemHA = this.findViewById(R.id.btn_TiepTucThemHA);
        btn_ChonHinhAnh = this.findViewById(R.id.btn_ChonHinh);
        rscv_HienHinhAnh = this.findViewById(R.id.rscv_HienAnh);

        rscv_HienHinhAnh.setHasFixedSize(true);
        rscv_HienHinhAnh.setLayoutManager(new LinearLayoutManager(this));

        mStorageRef = FirebaseStorage.getInstance().getReference();

        modalClassList = new ArrayList<>();
        linkAnhList = new ArrayList<>();

        btn_ChonHinhAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, IMAGE_CODE);

            }
        });

        btn_TiepTucThemHA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiChiTiet");

                    Intent inten = new Intent(ChonHinhAnhActivity.this, NhapDuLieuSanPhamActivity.class);

                    if (linkAnhList.size() != 0){

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("ChonHinhAnh", sanPhamDomian);

                        inten.putExtras(bundle);
                        inten.putStringArrayListExtra("LinkHinhAnh", linkAnhList);

                        startActivityForResult(inten, MY_REQUEST_CODE);
                    } else {
                        Toast.makeText(ChonHinhAnhActivity.this, "Bạn chưa chọn hình", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        //        Quay Lại
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE && resultCode == RESULT_OK) {

            if (data.getClipData() != null) {

                dialog = Utils.showLoader(ChonHinhAnhActivity.this);

                int totalitem = data.getClipData().getItemCount();

                for (int i = 0; i < totalitem; i++) {

                    Uri imageUri = data.getClipData().getItemAt(i).getUri();
                    String imagename = getFileName(imageUri);

                    ChonHinh modalClass = new ChonHinh(imagename,imageUri);
                    modalClassList.add(modalClass);


                    hinhAnhAdapter = new HinhAnhAdapter(ChonHinhAnhActivity.this, modalClassList);
                    rscv_HienHinhAnh.setAdapter(hinhAnhAdapter);

                    StorageReference mRef = mStorageRef.child(imagename);

                    mRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    linkAnhList.add(uri.toString().trim());

                                    btn_TiepTucThemHA.setVisibility(View.VISIBLE);

                                    if(dialog!=null){
                                        dialog.dismiss();
                                    }

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(dialog!=null){
                                dialog.dismiss();
                            }
                            Toast.makeText(ChonHinhAnhActivity.this, "lỗi : " + e.getMessage(), Toast.LENGTH_SHORT).show();
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