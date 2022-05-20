package com.golan.amit.boolpegia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class BoolPegiaActivity extends AppCompatActivity implements View.OnClickListener {

    private BoolPegiaHelper bph;
    TextView[][] tvDisplay;
    TextView[][] tvReflect;
    Button[] btnUserGuess;
    Button btnClear;
    ToggleButton tbMode;
    WebView wvVideo;
    Button btnDropVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bool_pegia);

        init();

        setButtonsListeners();

        play();
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void setButtonsListeners() {
        for(int i = 0; i < btnUserGuess.length; i++) {
            btnUserGuess[i].setOnClickListener(this);
        }
        btnClear.setOnClickListener(this);
        tbMode.setOnClickListener(this);

        btnDropVideo.setOnClickListener(this);
    }

    private void play() {
        if(MainActivity.DEBUG) {
            Log.d(MainActivity.DEBUGTAG, "computer picked: " + bph.computer_representation());
        }
        btnClear.setVisibility(View.INVISIBLE);

        String url = "https://www.youtube.com/watch?v=01TYrVJ5em4";
        wvVideo.getSettings().setLoadsImagesAutomatically(true);
        wvVideo.getSettings().setJavaScriptEnabled(true);
        wvVideo.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        try {
            wvVideo.loadUrl(url);
        } catch (Exception e) {

        }
    }

    private void init() {
        bph = new BoolPegiaHelper();
        tvDisplay = new TextView[][]{
                {findViewById(R.id.tvDis00), findViewById(R.id.tvDis01),
                        findViewById(R.id.tvDis02), findViewById(R.id.tvDis03)},
                {findViewById(R.id.tvDis10), findViewById(R.id.tvDis11),
                        findViewById(R.id.tvDis12), findViewById(R.id.tvDis13)},
                {findViewById(R.id.tvDis20), findViewById(R.id.tvDis21),
                        findViewById(R.id.tvDis22), findViewById(R.id.tvDis23)},
                {findViewById(R.id.tvDis30), findViewById(R.id.tvDis31),
                        findViewById(R.id.tvDis32), findViewById(R.id.tvDis33)},
                {findViewById(R.id.tvDis40), findViewById(R.id.tvDis41),
                        findViewById(R.id.tvDis42), findViewById(R.id.tvDis43)},
                {findViewById(R.id.tvDis50), findViewById(R.id.tvDis51),
                        findViewById(R.id.tvDis52), findViewById(R.id.tvDis53)},
                {findViewById(R.id.tvDis60), findViewById(R.id.tvDis61),
                        findViewById(R.id.tvDis62), findViewById(R.id.tvDis63)},
                {findViewById(R.id.tvDis70), findViewById(R.id.tvDis71),
                        findViewById(R.id.tvDis72), findViewById(R.id.tvDis73)},
                {findViewById(R.id.tvDis80), findViewById(R.id.tvDis81),
                        findViewById(R.id.tvDis82), findViewById(R.id.tvDis83)},
                {findViewById(R.id.tvDis90), findViewById(R.id.tvDis91),
                        findViewById(R.id.tvDis92), findViewById(R.id.tvDis93)}
        };

        tvReflect = new TextView[][]{
                {findViewById(R.id.tvRef00), findViewById(R.id.tvRef01),
                        findViewById(R.id.tvRef02), findViewById(R.id.tvRef03)},
                {findViewById(R.id.tvRef10), findViewById(R.id.tvRef11),
                        findViewById(R.id.tvRef12), findViewById(R.id.tvRef13)},
                {findViewById(R.id.tvRef20), findViewById(R.id.tvRef21),
                        findViewById(R.id.tvRef22), findViewById(R.id.tvRef23)},
                {findViewById(R.id.tvRef30), findViewById(R.id.tvRef31),
                        findViewById(R.id.tvRef32), findViewById(R.id.tvRef33)},
                {findViewById(R.id.tvRef40), findViewById(R.id.tvRef41),
                        findViewById(R.id.tvRef42), findViewById(R.id.tvRef43)},
                {findViewById(R.id.tvRef50), findViewById(R.id.tvRef51),
                        findViewById(R.id.tvRef52), findViewById(R.id.tvRef53)},
                {findViewById(R.id.tvRef60), findViewById(R.id.tvRef61),
                        findViewById(R.id.tvRef62), findViewById(R.id.tvRef63)},
                {findViewById(R.id.tvRef70), findViewById(R.id.tvRef71),
                        findViewById(R.id.tvRef72), findViewById(R.id.tvRef73)},
                {findViewById(R.id.tvRef80), findViewById(R.id.tvRef81),
                        findViewById(R.id.tvRef82), findViewById(R.id.tvRef83)},
                {findViewById(R.id.tvRef90), findViewById(R.id.tvRef91),
                        findViewById(R.id.tvRef92), findViewById(R.id.tvRef93)}
        };

        for (int i = 0; i < tvDisplay.length; i++) {
            for (int j = 0; j < tvDisplay[i].length; j++) {
                tvDisplay[i][j].setBackgroundResource(R.color.colorGrey);
                tvReflect[i][j].setBackgroundResource(R.color.colorGrey);
            }
        }

        /**
         * Buttons init
         */

        btnUserGuess = new Button[]{
                findViewById(R.id.btnUserGuess0), findViewById(R.id.btnUserGuess1), findViewById(R.id.btnUserGuess2),
                findViewById(R.id.btnUserGuess3), findViewById(R.id.btnUserGuess4)
        };

        btnClear = findViewById(R.id.btnClearId);
        tbMode = findViewById(R.id.tbModeId);
        btnDropVideo = findViewById(R.id.btnDropVideoId);

        wvVideo = findViewById(R.id.wvVideo);
        wvVideo.setWebViewClient(new MyBrowser());
    }

    @Override
    public void onClick(View v) {

        if(v == btnDropVideo) {
            wvVideo.destroy();
            wvVideo.setVisibility(View.INVISIBLE);
            wvVideo = null;
            btnDropVideo.setClickable(false);
            btnDropVideo.setVisibility(View.INVISIBLE);
        }
        else if(v == tbMode) {
            if(tbMode.getText().toString().equals("מתחילים")) {
                Toast.makeText(this, "בוצע מעבר למתקדמים", Toast.LENGTH_SHORT).show();
                bph.setMode(BoolPegiaHelper.MODE_ADVANCED);
            } else if(tbMode.getText().toString().equals("מתקדמים")) {
                bph.setMode(BoolPegiaHelper.MODE_BEGINNER);
                Toast.makeText(this, "בוצע מעבר למתחילים", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(MainActivity.DEBUGTAG, "illegal choice of mode. should never reach here");
            }
            if(MainActivity.DEBUG) {
                Log.d(MainActivity.DEBUGTAG, "mode is now: " + bph.getMode());
            }
        }
        else if (v == btnClear) {
            if(MainActivity.DEBUG) {
                Log.d(MainActivity.DEBUGTAG, "user clicked clear");
            }
            bph.decreaseIn_index();
            if (bph.getIn_index() < 0) {
                bph.resetIn_index();
                return;
            }
            btnUserGuess[bph.getUserGuessByIndex(bph.getIn_index())].setClickable(true);
            bph.setUserGuessByIndex(-1, bph.getIn_index());
            tvDisplay[bph.getOut_index()][bph.getIn_index()].setBackgroundColor(Color.parseColor("#ABA000"));
            if (bph.getIn_index() == 0)
                btnClear.setVisibility(View.INVISIBLE);
        } else {
            btnClear.setVisibility(View.VISIBLE);
            int tmpUserGuess = -1;
            for (int i = 0; i < btnUserGuess.length; i++) {
                if (v == btnUserGuess[i]) {
                    tmpUserGuess = i;
                    break;
                }
            }
            if (tmpUserGuess != -1) {
                btnUserGuess[tmpUserGuess].setClickable(false);
                bph.setUserGuessByIndex(tmpUserGuess, bph.getIn_index());
                tvDisplay[bph.getOut_index()][bph.getIn_index()].setBackgroundColor(Color.parseColor(BoolPegiaHelper.COLOR_STR[tmpUserGuess]));
                bph.increaseIn_index();
                if (bph.getIn_index() == BoolPegiaHelper.NUM_LENGTH) {
                    Toast.makeText(this, "finished user guess cycle: " + bph.user_guesses_representation(), Toast.LENGTH_SHORT).show();
                    if(MainActivity.DEBUG) {
                        Log.d(MainActivity.DEBUGTAG, "user guess: " + bph.user_guesses_representation());
                    }
                    //  test if WIN
                    if (bph.user_win()) {
                        Intent i = new Intent(this, WonActivity.class);
                        startActivity(i);
                        return;
                    }

                    //  Evaluating for reflection display
                    bph.evaluate();
                    if(MainActivity.DEBUG) {
                        Log.d(MainActivity.DEBUGTAG, "evaluation: " + bph.evaluation_representation());
                    }


                    if(bph.getMode() == BoolPegiaHelper.MODE_ADVANCED) {
                        displayEvaluation_Novice();
                    } else if(bph.getMode() == BoolPegiaHelper.MODE_BEGINNER) {
                        displayEvaluation();
                    } else {
                        Log.e(MainActivity.DEBUGTAG, "illegal mode operation. should never reach here");
                    }

                    bph.resetIn_index();
                    bph.increaseOut_index();
                    if (bph.getOut_index() == tvDisplay.length) {
                        Toast.makeText(this, "Finished all cycles", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(this, LostActivity.class);
                        startActivity(i);
                        return;
                    }
                    for (int i = 0; i < btnUserGuess.length; i++) {
                        btnUserGuess[i].setClickable(true);
                    }
                    btnClear.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    private void displayEvaluation() {
        for (int i = 0; i < bph.getEvalRes().length; i++) {
            switch (bph.getEvalByIndex(i)) {
                case 0:
                    tvReflect[bph.getOut_index()][i].setBackgroundColor(Color.parseColor(BoolPegiaHelper.REFLECTION_COLOR_STR[0]));
                    break;
                case 1:
                    tvReflect[bph.getOut_index()][i].setBackgroundColor(Color.parseColor(BoolPegiaHelper.REFLECTION_COLOR_STR[1]));
                    break;
                default:
                    break;
            }
        }
    }

    private void displayEvaluation_Novice() {
        int bool = 0, pegia = 0;
        for (int i = 0; i < bph.getEvalRes().length; i++) {
            switch (bph.getEvalByIndex(i)) {
                case 0:
                    bool++;
//                    tvReflect[bph.getOut_index()][i].setBackgroundColor(Color.parseColor(BoolPegiaHelper.REFLECTION_COLOR_STR[0]));
                    break;
                case 1:
                    pegia++;
//                    tvReflect[bph.getOut_index()][i].setBackgroundColor(Color.parseColor(BoolPegiaHelper.REFLECTION_COLOR_STR[1]));
                    break;
                default:
                    break;
            }
        }
        int cnt = 0;
        for(int i = 0; i < bool; i++) {
            tvReflect[bph.getOut_index()][cnt].setBackgroundColor(Color.parseColor(BoolPegiaHelper.REFLECTION_COLOR_STR[0]));
            cnt++;
        }
        for(int i = 0; i < pegia; i++) {
            tvReflect[bph.getOut_index()][cnt].setBackgroundColor(Color.parseColor(BoolPegiaHelper.REFLECTION_COLOR_STR[1]));
            cnt++;
        }
    }
}
