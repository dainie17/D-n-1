package com.example.test_du_an_mau.Sevice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.example.test_du_an_mau.Activity.MainActivity;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MyService extends Service {

    public static String NOTIFICATION_CHANNEL = "thông báo của app";
    FirebaseDatabase database;
    DatabaseReference myRef;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String id = intent.getStringExtra("id");

        if (id != null){

            database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
            myRef = database.getReference("ThongBao");
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    Thongbao thongbao = snapshot.getValue(Thongbao.class);

                    if (thongbao != null){

                        String listID = thongbao.getIDNguoiNhan();

                        if (id.equals(listID)){

                            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MyService.this);

                            // Đăng ký
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                CharSequence name = "Kênh thông báo";
                                String description = "Mô tả thông báo";
                                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                                NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL, name, importance);
                                channel.setDescription(description);
                                notificationManager.createNotificationChannel(channel);
                            }

                            // Nhấn vào tb
                            Intent intent = new Intent(MyService.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, 0);

                            //nội dung tb
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this, NOTIFICATION_CHANNEL)
                                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                                    .setContentTitle(thongbao.getLoaiThongBao())
                                    .setContentText(thongbao.getNoiDung())
                                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                    .setContentIntent(pendingIntent)
                                    .setAutoCancel(true);

                            notificationManager.notify(33, builder.build());

                        }

                    }

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        return START_NOT_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return null;
    }
}