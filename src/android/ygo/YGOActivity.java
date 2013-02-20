package android.ygo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.ygo.utils.Utils;
import android.ygo.views.DuelDiskView;
import android.ygo.views.PlayOnKeyProcessor;

public class YGOActivity extends Activity {
    private PlayOnKeyProcessor keyProcessor;
    private DuelDiskView duelDiskView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.initInstance(this);

        setContentView(R.layout.main);
        duelDiskView = (DuelDiskView)findViewById(R.id.duelDiskView);

        keyProcessor = new PlayOnKeyProcessor(duelDiskView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyProcessor.onKey(keyCode, event);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (duelDiskView != null) {
            duelDiskView.pause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (duelDiskView != null) {
            duelDiskView.resume();
        }
    }
}

