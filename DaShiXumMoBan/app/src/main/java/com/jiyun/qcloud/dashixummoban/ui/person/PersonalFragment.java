package com.jiyun.qcloud.dashixummoban.ui.person;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.main.RegistActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalFragment extends BaseFragment {
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;
    private TextView username;
    private TextView pass;
    private LinearLayout ll_userinfo;
    ImageView imagess;
    private String ufo;
    private boolean islogin = false;
    private TextView change_tx, look_big, qux;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String substring;
    private PopupWindow popupWindow;

    @Override
    protected void initView(View view) {
        ll_userinfo = (LinearLayout) view.findViewById(R.id.lissa);
        username = (TextView) view.findViewById(R.id.use_name);
        pass = (TextView) view.findViewById(R.id.use_gender);
        imagess = (ImageView) view.findViewById(R.id.touxiamg);
    }

    @Override
    public void setBundle(Bundle bundle) {

    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {
        SharedPreferences sharedP = getActivity().getSharedPreferences("zc", Context.MODE_PRIVATE);
        String picture = sharedP.getString("picture", "");
        Glide.with(getActivity().getApplicationContext()).load(picture).asBitmap().centerCrop().into(new BitmapImageViewTarget(imagess) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                ciDrawable.setCircular(true);
                imagess.setImageDrawable(ciDrawable);
            }
        });

        ll_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(App.mBaseActivity,RegistActivity.class);

//                startActivity(intent);

                startActivity(intent);

            }
        });
        imagess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainpop2();
            }
        });

    }

    private void mainpop2() {
        popupWindow = new PopupWindow();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.pop_head, null);
        change_tx = (TextView) view.findViewById(R.id.change_tx);
        look_big = (TextView) view.findViewById(R.id.look_big);
        qux = (TextView) view.findViewById(R.id.qux);

        popupWindow.setContentView(view);
        popupWindow.setHeight(600);
        popupWindow.setWidth(800);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.showAtLocation(getActivity().findViewById(R.id.touxiamg), Gravity.CENTER | Gravity.CENTER, 0, 0);
        change_tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
                startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
            }
        });
        look_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, PHOTO_REQUEST_CAREMA);
            }
        });
        qux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                String s = uri.toString();
                substring = s.substring(s.length() - 2, s.length());
                crop(uri);
                Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imagess) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                        ciDrawable.setCircular(true);
                        imagess.setImageDrawable(ciDrawable);
                    }
                });
            }
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            if (data != null) {
                // 得到图片的全路径
                Bitmap bitmap = data.getParcelableExtra("data");
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                //myfragment_touxiang.setImageBitmap(bitmap);
                Glide.with(getActivity().getApplicationContext()).load(uri).asBitmap().centerCrop().into(new BitmapImageViewTarget(imagess) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                        ciDrawable.setCircular(true);
                        //imageHead.setImageDrawable(ciDrawable);
                    }
                });
                HashMap<String, String> map = new HashMap<>();
                final File file = saveBitmapFile(bitmap);
                map.put("file", file + "");
                OkHttpClient.Builder builder = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .readTimeout(20, TimeUnit.SECONDS);
                OkHttpClient mOkHttpClient = builder.build();

                MultipartBody.Builder mbody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                mbody.addFormDataPart("image", file.getName(), RequestBody.create(null, file));
                RequestBody requestBody = mbody.build();
                Request request = new Request.Builder()
                        .url("http://123.206.14.104:8080/FileUploadDemo/FileUploadServlet")
                        .post(requestBody)
                        .build();
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Gson gson = new Gson();
                        Head shangchuang = gson.fromJson(string, Head.class);
                        final HeadResult shangchuang2 = gson.fromJson(shangchuang.getData(), HeadResult.class);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), shangchuang2.getResult(), Toast.LENGTH_SHORT).show();
                                Log.e("TAg", shangchuang2.getUrl());

                                String localurl = shangchuang2.getUrl().replace("localhost", "123.206.14.104");
                                Log.e("localurl", localurl + "...fvfgfgfgfg");

                                sharedPreferences = getActivity().getSharedPreferences("zc", Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                editor.putString("picture", localurl);
                                editor.commit();
                                Glide.with(getActivity().getApplicationContext()).load(localurl).asBitmap().centerCrop().into(new BitmapImageViewTarget(imagess) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable ciDrawable = RoundedBitmapDrawableFactory.create(getActivity().getApplicationContext().getResources(), resource);
                                        ciDrawable.setCircular(true);
                                        imagess.setImageDrawable(ciDrawable);
                                    }
                                });
                            }
                        });
                    }
                });

            } else if (requestCode == PHOTO_REQUEST_CAREMA) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                //Bitmap bitmap = data.getParcelableExtra("data");
                //拿到bitmap，做喜欢做的事情把  ---> 显示 or上传？
                Uri uria = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
                Log.e("BBBBBBBBBBBBBBB", uria + "");
                imagess.setImageBitmap(bitmap);
                Glide.with(imagess.getContext()).load(uria).into(imagess);
            }


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public File saveBitmapFile(Bitmap bitmap) {
        File file = new File("/sdcard/" + substring + "head.jpg");//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
