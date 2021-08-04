package com.jpr.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,imageURL,url;
    private TextView titleTV,subDescTV,contentTV;
    private ImageView newsIV;
    private Button readNewsBtn,button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        content=getIntent().getStringExtra("content");
        imageURL=getIntent().getStringExtra("image");
        url=getIntent().getStringExtra("url");

        titleTV=findViewById(R.id.idTVTitle);
        subDescTV=findViewById(R.id.idTVSubDesc);
        contentTV=findViewById(R.id.idTVContent);
        newsIV=findViewById(R.id.idIVNews);
        readNewsBtn=findViewById(R.id.idBtnReadNews);
        titleTV.setText(title);
        subDescTV.setText(desc);
        contentTV.setText(content);
        Picasso.get().load(imageURL).into(newsIV);



        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomTabsIntent.Builder customTabIntent = new CustomTabsIntent.Builder();
                customTabIntent.setToolbarColor(Color.parseColor("#292D36"));
                openCustomTabs(NewsDetailActivity.this,customTabIntent.build(),Uri.parse(url));


            }
        });

    }

    public static void openCustomTabs(Activity activity,CustomTabsIntent customTabsIntent,Uri uri){

        String packageName="com.android.chrome";
        if(packageName!=null)
        {
            customTabsIntent.intent.setPackage(packageName);
            customTabsIntent.launchUrl(activity,uri);
        }
        else {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }
    }

}

