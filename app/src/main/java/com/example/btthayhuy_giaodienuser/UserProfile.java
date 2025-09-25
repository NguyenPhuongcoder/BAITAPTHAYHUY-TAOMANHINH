package com.example.btthayhuy_giaodienuser;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class UserProfile extends AppCompatActivity {

    private TextView tvUserName, tvUserYear, tvUserMajor;
    private ImageView ivAvatar;
    private MaterialButton btnEditProfile, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Xử lý system bars cho edge-to-edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        setupUserData();
        setupClickListeners();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showLogoutDialog();
            }
        });
    }

    private void initViews() {
        tvUserName = findViewById(R.id.textView2);
        tvUserYear = findViewById(R.id.textView5);
        tvUserMajor = findViewById(R.id.textView6);
        ivAvatar = findViewById(R.id.avatar);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void setupUserData() {
        // Lấy thông tin từ Intent nếu có
        String userEmail = getIntent().getStringExtra("user_email");

        // Set thông tin mặc định
        tvUserName.setText("Nguyễn Phương");
        tvUserYear.setText("Sinh viên năm 3");
        tvUserMajor.setText("Công nghệ thông tin");

        // Có thể load avatar từ URL hoặc storage
        // Glide.with(this).load(avatarUrl).into(ivAvatar);
    }

    private void setupClickListeners() {
        // Xử lý nút chỉnh sửa thông tin
        btnEditProfile.setOnClickListener(v -> showEditProfileDialog());

        // Xử lý nút đăng xuất
        btnLogout.setOnClickListener(v -> showLogoutDialog());

        // Click vào avatar để thay đổi ảnh
        ivAvatar.setOnClickListener(v -> {
            Toast.makeText(this, "Tính năng thay đổi avatar đang được phát triển",
                    Toast.LENGTH_SHORT).show();
        });
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chỉnh sửa thông tin")
                .setMessage("Bạn có muốn chỉnh sửa thông tin cá nhân?")
                .setPositiveButton("Chỉnh sửa", (dialog, which) -> {
                    // TODO: Mở màn hình edit profile
                    Toast.makeText(this, "Tính năng đang được phát triển",
                            Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Đăng xuất")
                .setMessage("Bạn có chắc chắn muốn đăng xuất?")
                .setPositiveButton("Đăng xuất", (dialog, which) -> {
                    performLogout();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void performLogout() {
        // Show loading
        btnLogout.setEnabled(false);
        btnLogout.setText("Đang đăng xuất...");

        // Simulate logout delay
        btnLogout.postDelayed(() -> {
            // Clear user session (nếu có)
            // SharedPreferences, Database, etc.

            Toast.makeText(this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

            // Quay lại màn hình login
            Intent intent = new Intent(UserProfile.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }, 1000);
    }
}