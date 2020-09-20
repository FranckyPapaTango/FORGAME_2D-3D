package com.rafaros.game;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MasterMainActivity extends Activity implements View.OnClickListener {
    private AndroidApplicationConfiguration cfg;
    private Button config_btn;
    private Button demarrer_btn;
    ImageView img;
    private LinearLayout layout;
    private Button notices_btn;
    Button owner_btn;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.layout = (LinearLayout) findViewById(R.id.layout);
        setContentView(R.layout.activity_master_main);
        this.demarrer_btn = (Button) findViewById(R.id.demarrer_btn);
        this.config_btn = (Button) findViewById(R.id.config_btn);
        this.notices_btn = (Button) findViewById(R.id.notices_btn);
        addButtonListener();
        this.demarrer_btn.setOnClickListener(this);
        this.config_btn.setOnClickListener(this);
        this.notices_btn.setOnClickListener(this);
        this.cfg = new AndroidApplicationConfiguration();
        this.cfg.useGL30 = true;
    }

    public void addButtonListener() {
        this.img = (ImageView) findViewById(R.id.img);
        this.owner_btn = (Button) findViewById(R.id.owner_btn);
        this.owner_btn.setOnClickListener(new View.OnClickListener() {
            /* class com.smallwave.game.MasterMainActivity.AnonymousClass1 */

            public void onClick(View paramAnonymousView) {
                MasterMainActivity.this.img.setImageResource(R.drawable.ic_launcher);
            }
        });
    }

    public void onClick(View view) {
        if (view == this.demarrer_btn) {
            showConsignes("Touchez ou caressez l'écran !");
            startActivity(new Intent(this, SurfaceviewExample.class));
        }
        if (view == this.config_btn) {
            showConsignes("Patientez...puis carressez l'écran !");
            startActivity(new Intent(this, Layout3DActivity.class));
        }
        if (view == this.notices_btn) {
            showConsignes("Patientez svp...puis caressez l'écran !");
            showConsignes("Wait a while please...then drag up the screen !");
            startActivity(new Intent(this, Layout4DActivity.class));
        }
    }

    @SuppressLint("WrongConstant")
    private void showConsignes(String consignes) {
        Toast.makeText(getApplicationContext(), consignes, 1).show();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
