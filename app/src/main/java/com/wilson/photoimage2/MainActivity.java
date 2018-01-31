package com.wilson.photoimage2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String imgpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView)this.findViewById(R.id.textView);

        Button a = (Button)this.findViewById(R.id.button1Obj);
        Button b = (Button)this.findViewById(R.id.button2Obj);


        a.setOnClickListener( new View.OnClickListener(){
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                // 建立 "選擇檔案 Action" 的 Intent
                Intent intent = new Intent( Intent.ACTION_PICK );

                // 過濾檔案格式
                intent.setType( "image/*" );

                // 建立 "檔案選擇器" 的 Intent (第二個參數: 選擇器的標題)
                Intent destIntent = Intent.createChooser( intent, "選擇檔案" );

                // 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
                startActivityForResult( destIntent, 0 );
            }
        });

        b.setOnClickListener( new View.OnClickListener(){
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                // 建立 "選擇檔案 Action" 的 Intent
                Intent intent = new Intent( Intent.ACTION_PICK );

                // 過濾檔案格式
                intent.setType( "image/*" );

                // 建立 "檔案選擇器" 的 Intent (第二個參數: 選擇器的標題)
                Intent destIntent = Intent.createChooser( intent, "選擇檔案" );

                // 切換到檔案選擇器 (它的處理結果, 會觸發 onActivityResult 事件)
                startActivityForResult( destIntent, 0 );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);



        // 有選擇檔案
        if ( resultCode == RESULT_OK )
        {
            // 取得檔案的 Uri
            Uri uri = data.getData();
            if( uri != null )
            {
                // 利用 Uri 顯示 ImageView 圖片
                ImageView iv = (ImageView)this.findViewById(R.id.imageView);
                iv.setImageURI( uri );

                setTitle( uri.toString() );

               //
                String path = getRealPathFromURI(this,uri);
                String filename = path.substring(path.lastIndexOf("/")+1);
                String file;
                if (filename.indexOf(".") > 0) {
                    file = filename.substring(0, filename.lastIndexOf("."));
                } else {
                    file =  filename;
                }
                Log.d("Real Path: ",path);

                this.imgpath=path;
                Log.d("IMG Real Path: ",this.imgpath);

                Log.d("Filename With Extension: ", filename);
                Log.d("File Without Extension: ", file);
               //

                Log.d("PATH",Environment.getExternalStorageDirectory().getPath());
                //Log.d("File",data.getData().
                Log.d("Path",data.getData().getPath());
                Log.d("Name",data.getData().getQueryParameterNames().toString());

            }
            else
            {
                setTitle("無效的檔案路徑 !!");
            }
        }
        else
        {
            setTitle("取消選擇檔案 !!");
        }
    }
//
    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
//

    public void clickCamera(View v)
    {

        Log.d("DDDDDDDDDDD","clickCamera");
        Log.d("DDDDDDDDDDDDDDDDDDD","clickCamera:"+this.imgpath);
    }
    public void clickImage(View v)
    {
        Log.d("DD","456");
    }


}
