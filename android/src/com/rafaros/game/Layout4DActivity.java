package com.rafaros.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.rafaros.TroisDGame.LoadScene_and_objects_with_multiple_file;

public class Layout4DActivity extends AndroidApplication {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL30 = true;
        initialize(new LoadScene_and_objects_with_multiple_file(), cfg);
    }
}
