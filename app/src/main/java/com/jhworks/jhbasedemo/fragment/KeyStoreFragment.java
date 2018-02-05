package com.jhworks.jhbasedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jhworks.jhbase.base.BaseFragment;
import com.jhworks.jhbasedemo.R;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import cn.jhworks.utilscore.utils.EncryptUtils;

/**
 * @apiNote
 * @since 2017/7/18
 * <p>
 * author: jacksonliao
 */
public class KeyStoreFragment extends BaseFragment implements View.OnClickListener {


    private static final String KEY_ALIAS = "hello";
    private static final String SHARED_PREFENCE_NAME = "test";
    private static final String ENCRYPTED_KEY = "ENCRYPTED_KEY";

    private Button mEncrypting, mDecrypting;

    @Override
    protected int getLayout() {
        return R.layout.fragment_key_store;
    }

    @Override
    protected void initView(View rootView, @Nullable Bundle savedInstanceState) {
        mEncrypting = (Button) rootView.findViewById(R.id.encrypting);
        mDecrypting = (Button) rootView.findViewById(R.id.decrypting);

        mEncrypting.setOnClickListener(this);
        mDecrypting.setOnClickListener(this);

        try {


            String jsonData = "12345678";
            Log.e("MainActivity", "AES加密前json数据 ---->" + jsonData);
            Log.e("MainActivity", "AES加密前json数据长度 ---->" + jsonData.length());


            KeyGenerator keyGen = KeyGenerator.getInstance("DES"); //密钥生成器
            keyGen.init(56);//初始化密钥生成器
            SecretKey secretKey = keyGen.generateKey();//生成密钥
            byte[] key = secretKey.getEncoded();//密钥字节数组


            //生成一个动态key
//        String secretKey = EncryUtils.generateKey();
            Log.e("MainActivity", "AES动态secretKey ---->" + secretKey);

            //AES加密
            long start = System.currentTimeMillis();
//        String encryStr = EncryUtils.encrypt(secretKey, jsonData);
            String encryStr = new String(EncryptUtils.encryptDES(jsonData.getBytes(), key));
            long end = System.currentTimeMillis();
            Log.e("MainActivity", "AES加密耗时 cost time---->" + (end - start));
            Log.e("MainActivity", "AES加密后json数据 ---->" + encryStr);
            Log.e("MainActivity", "AES加密后json数据长度 ---->" + encryStr.length());

            //AES解密
            start = System.currentTimeMillis();
            String decryStr = new String(EncryptUtils.decryptDES(encryStr.getBytes(), key));
//        String decryStr = EncryUtils.decrypt(secretKey, encryStr);

            end = System.currentTimeMillis();
            Log.e("MainActivity", "AES解密耗时 cost time---->" + (end - start));
            Log.e("MainActivity", "AES解密后json数据 ---->" + decryStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    private String encryptString;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.encrypting:

                break;
            case R.id.decrypting:

                break;
        }
    }
}
