package com.ganapathi.youtuby;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.moos.navigation.BottomBarLayout;
import com.moos.navigation.BottomTabView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class HomeActivity extends AppCompatActivity implements  ItemClickListener {

    private static final int PERMISSIONS_REQUEST = 123;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshCollection;
    public static ArrayList<productversion> productversions;
    public ArrayList<productversion> product_version = new ArrayList<>();
    DataAdapter adapter;
    BottomBarLayout bottomBarLayout;
    BottomTabView tab_all, tab_music, tab_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        refreshCollection = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        bottomBarLayout = findViewById(R.id.bottom_bar);

        BottomTabView tab_all = new BottomTabView(this);
        tab_all.setTabIcon(R.drawable.ic_video_library_black_24dp);
        tab_all.setTabTitle("All");

        tab_all = new BottomTabView(this);
        tab_all.setTabIcon(R.drawable.ic_videocam_black_24dp);
        tab_all.setTabTitle("Video");

        tab_music = new BottomTabView(this);
        tab_all.setTabIcon(R.drawable.ic_music_video_black_24dp);
        tab_all.setTabTitle("Music");

        tab_video = new BottomTabView(this);
        tab_all.setTabIcon(R.drawable.ic_subscriptions_black_24dp);
        tab_all.setTabTitle("Subs");

        bottomBarLayout
                .addTab(tab_all)
                .addTab(tab_music)
                .addTab(tab_video)
                .create(new BottomBarLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(BottomTabView tab) {

                    }

                    @Override
                    public void onTabUnselected(BottomTabView tab) {

                    }

                    @Override
                    public void onTabReselected(BottomTabView tab) {

                    }
                });

        refreshCollection.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshPlease();
            }
        });

        initViews();

        getPermission();
    }

    public void initViews() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        productversions = prepareData();
        adapter = new DataAdapter(getApplicationContext(), productversions, getLifecycle());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(HomeActivity.this);
    }

    private ArrayList<productversion> prepareData() {
        String[] youTubeURL = getResources().getStringArray(R.array.my_youtubeURL_array);
        String[] youTubeVideo = getResources().getStringArray(R.array.my_youtubeName_array);

        product_version = new ArrayList<>();
        productversions = new ArrayList<>();
        for (int i = 0; i < youTubeURL.length; i++) {
            productversion productversion = new productversion();
            productversion.setAndroid_version_name(youTubeVideo[i]);
            productversion.setAndroid_imagePath(youTubeURL[i]);
            product_version.add(productversion);
        }
        return product_version;
    }

    public void refreshPlease() {
        refreshCollection.setRefreshing(true);
        initViews();
        refreshCollection.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshCollection.setRefreshing(false);
            }
        }, 500);
    }

    @Override
    public void onClick(final View view, int posi) {
        Intent tender = new Intent(this, MainActivity.class);
        tender.putExtra("videoId", product_version.get(posi).getAndroid_imagePath());
        tender.putExtra("videoName", product_version.get(posi).getAndroid_version_name());
        startActivity(tender);
    }

    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_NETWORK_STATE)
                == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE,
                            android.Manifest.permission.INTERNET,
                            android.Manifest.permission.ACCESS_WIFI_STATE},
                    PERMISSIONS_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            default: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.app_name) + " was not allowed to access your location",
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
