package com.example.ivy.encryptdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content)
    EditText etContent;
    @BindView(R.id.result)
    TextView tvResult;
    @BindView(R.id.base)
    Button base;
    @BindView(R.id.md)
    Button md;
    @BindView(R.id.sha)
    Button sha;
    @BindView(R.id.des)
    Button des;
    @BindView(R.id.des3)
    Button des3;
    @BindView(R.id.aes)
    Button aes;
    @BindView(R.id.pbe)
    Button pbe;
    @BindView(R.id.rsa)
    Button rsa;
    @BindView(R.id.dh)
    Button dh;
    @BindView(R.id.switch1)
    Switch switch1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.base, R.id.md, R.id.sha, R.id.des, R.id.des3, R.id.aes, R.id.pbe, R.id.rsa, R.id.dh})
    public void onClick(View view) {
        String content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "加密内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        switch (view.getId()) {
            case R.id.base:
                base64(content);
                break;
            case R.id.md:
                md5(content);
                break;
            case R.id.sha:
                sha(content);
                break;
            case R.id.des:
                break;
            case R.id.des3:
                break;
            case R.id.aes:
                break;
            case R.id.pbe:
                break;
            case R.id.rsa:
                break;
            case R.id.dh:
                break;
        }
    }

    private void sha(String content) {
        if(switch1.isChecked()){//加密
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] bytes = digest.digest(content.getBytes());
            String result = byteArrayToHexString(bytes);
            setText(result);
        }else{//解密
            setText("此方式无解密算法");
        }
    }

    private void md5(String content) {
        if(switch1.isChecked()){//加密
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] bytes = digest.digest(content.getBytes());
            String result = byteArrayToHexString(bytes);
            setText(result);
        }else{//解密
            setText("此方式无解密算法");
        }
    }

    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private void base64(String content) {

        if(switch1.isChecked()){//加密
            byte[] bytes = Base64.encode(content.getBytes(),0);
            String result = new String(bytes);
            setText(result);
        }else{//解密
            byte[] decode = Base64.decode(content,0);
            String result = new String(decode);
            setText(result);
        }
    }

    public void setText(String result) {
        Log.d("main",result);
        tvResult.setText(result);
    }
}
