package android.fmproject.ru.dipolsizes2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.text.MessageFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    public static final double MIN_FREQ = 136.0;
    public static final double MAX_FREQ = 527.0;
    public static final double MIN_COEFFICIENT = 0.5;
    public static final double MAX_COEFFICIENT = 1.0;

    public static final int METERS = 1;
    public static final int CENTIMETERS = 100;
    public static final int MILLIMETERS = 1000;

    private AdView mAdView;

    Button btnCalc;

    EditText etFreq1;
    EditText etCoefficient;

    TextView tvSizeA1;
    TextView tvSizeA2;
    TextView tvSizeA3;
    TextView tvSizeB;
    TextView tvFormulaB;
    TextView tvAutor;

    ImageView mImageView;
    LinearLayout mLayout;

    private static final String KEY_FREQ1 = "FREQ";
    private static final String KEY_COEFFICIENT = "COEFFICIENT";
    private static final String KEY_SIZE_A1 = "SIZE_A1";
    private static final String KEY_SIZE_A2 = "SIZE_A2";
    private static final String KEY_SIZE_A3 = "SIZE_A3";
    private static final String KEY_SIZE_B = "SIZE_B";

    public static final String APP_PREFERENCES = "mysettings";
    private SharedPreferences mSettings;

    int intUnits = CENTIMETERS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() запущен.");
        try {
            setContentView(R.layout.activity_main);
            MobileAds.initialize(this, "ca-app-pub-9802856203007191~2354956671");
            setTitle (getString(R.string.toolbar));
            btnCalc = findViewById(R.id.btnCalc1);
            btnCalc.setOnClickListener(this);
            etFreq1 = findViewById(R.id.etFreq1);
            etCoefficient = findViewById(R.id.etCoefficient1);
            tvSizeA1 = findViewById(R.id.tvSizeA11);
            tvSizeA2 = findViewById(R.id.tvSizeA12);
            tvSizeA3 = findViewById(R.id.tvSizeA13);
            tvFormulaB = findViewById(R.id.tvFormulaB1);
            tvSizeB = findViewById(R.id.tvSizeB1);
            tvAutor = findViewById(R.id.tvAutor);
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
            tvAutor.startAnimation(animation);

//            mImageView = findViewById(R.id.imageView);
//            mImageView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
//                public void onSwipeTop() {
//                    Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
//                }
//                public void onSwipeRight() {
//                    Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
//                }
//                public void onSwipeLeft() {
//                    Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
//                }
//                public void onSwipeBottom() {
//                    Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//                }
//
//            });

            mLayout = findViewById(R.id.mainLayout);
//            mLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
//                public void onSwipeTop() {
//                    Toast.makeText(MainActivity.this, "top", Toast.LENGTH_SHORT).show();
//                }
//                public void onSwipeRight() {
//                    Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
//                }
//                public void onSwipeLeft() {
//                    Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
//                }
//                public void onSwipeBottom() {
//                    Toast.makeText(MainActivity.this, "bottom", Toast.LENGTH_SHORT).show();
//                }
//
//            });

            mAdView = findViewById(R.id.adView);

            mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

            if (savedInstanceState != null) {
                Log.d(TAG, "Существуют значения, восстанавливаем их.");
                //etFreq1.setText(savedInstanceState.getString(KEY_FREQ1));
                tvSizeA1.setText(savedInstanceState.getString(KEY_SIZE_A1));
                tvSizeA2.setText(savedInstanceState.getString(KEY_SIZE_A2));
                tvSizeA3.setText(savedInstanceState.getString(KEY_SIZE_A3));
                tvSizeB.setText(savedInstanceState.getString(KEY_SIZE_B));
            }

//            AdRequest adRequest = new AdRequest.Builder().addTestDevice("C656855C66D6AB6FF2E42A97286F3B59").build(); //Genymotion Custom Phone - 8.0 API26
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("7AC37A42A3DEC77A354D9A0C0B1C4325").build();// Xiaomi Redmi 5A

            mAdView.loadAd(adRequest);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    Log.d(TAG, "onAdLoaded() запущен.");
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    Log.d(TAG, "onAdFailedToLoad() запущен. errorCode: " + errorCode);


                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                    Log.d(TAG, "onAdOpened() запущен.");
                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                    Log.d(TAG, "onAdLeftApplication() запущен.");
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                    Log.d(TAG, "onAdClosed() запущен.");
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when a click is recorded for an ad.
                    Log.d(TAG, "onAdClicked() запущен.");
                }

                @Override
                public void onAdImpression() {
                    // Code to be executed when an impression is recorded for an ad.
                    Log.d(TAG, "onAdImpression() запущен.");
                }
            });

        } catch (Exception e) {
            Log.d(TAG, "При старте приложения произошла ошибка: " + e);
            StackTraceElement[] stackTraceElements = e.getStackTrace();

            for (int i = 0; i < stackTraceElements.length; i++) {
                Log.d(TAG, i + ": " + stackTraceElements[i].toString());
            }
            this.onDestroy();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState(Bundle outState) запущен.");
        //outState.putString(KEY_FREQ1, etFreq1.getText().toString());
        outState.putString(KEY_SIZE_A1, tvSizeA1.getText().toString());
        outState.putString(KEY_SIZE_A2, tvSizeA2.getText().toString());
        outState.putString(KEY_SIZE_A3, tvSizeA3.getText().toString());
        outState.putString(KEY_SIZE_B, tvSizeB.getText().toString());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.d(TAG, "onPostCreate() запущен.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() запущен.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() запущен.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() запущен.");
        if (mSettings.contains(KEY_FREQ1)) {
            Log.d(TAG, "Существует файл настроек, читаем частоту из него.");
            // Получаем частоту из настроек
            etFreq1.setText(mSettings.getString(KEY_FREQ1, "134"));
        }if (mSettings.contains(KEY_COEFFICIENT)) {
            Log.d(TAG, "Существует файл настроек, читаем коэффициент из него.");
            // Получаем частоту из настроек
            etCoefficient.setText(mSettings.getString(KEY_COEFFICIENT, "0.9"));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() запущен.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() запущен.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() запущен.");
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick(View v) запущен.");
        try {
            double [] sizeA;
            Double sizeB;
            String strFreq;
            String strCoefficient;
            Double dblFreq;
            Double dblCoefficient;

            strFreq = etFreq1.getText().toString();
            if (strFreq.equals("")) {
                Toast.makeText(this, getString(R.string.emptyFreqFieldMsg), Toast.LENGTH_SHORT).show();
                return;
            }
            dblFreq = Double.parseDouble(strFreq);
            if (dblFreq < MIN_FREQ || dblFreq > MAX_FREQ) {
                Toast.makeText(this, (String.format(getString(R.string.freqLimits), MIN_FREQ, MAX_FREQ)), Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(KEY_FREQ1, strFreq);//Введена подходящая частота, сохраняем ее в файле настроек
            editor.apply();
            sizeA = getSizeA(dblFreq);
            tvSizeA3.setText(String.format("%s%s", new DecimalFormat("##.#").format(sizeA[2] * intUnits), getString(R.string.units)));
            tvSizeA2.setText(String.format("%s%s", new DecimalFormat("##.#").format(sizeA[1] * intUnits), getString(R.string.units)));
            tvSizeA1.setText(MessageFormat.format("{0}{1}", new DecimalFormat("##.#").format(sizeA[0] * intUnits), getString(R.string.units)));

            strCoefficient = etCoefficient.getText().toString();
            if (strCoefficient.equals("")) {
                Toast.makeText(this, getString(R.string.emptyCoeffFieldMsg), Toast.LENGTH_SHORT).show();
                tvFormulaB.setText("");
                return;
            }
            dblCoefficient = Double.parseDouble(strCoefficient);
            if (dblCoefficient < MIN_COEFFICIENT || dblCoefficient > MAX_COEFFICIENT) {
                Toast.makeText(this, (String.format(getString(R.string.coeffLimits), MIN_COEFFICIENT, MAX_COEFFICIENT)), Toast.LENGTH_SHORT).show();
                return;
            }
            editor.putString(KEY_COEFFICIENT, strCoefficient);//Введен подходящий коэффициент, сохраняем его в файле настроек
            editor.apply();

            sizeB = getSizeB(dblFreq, dblCoefficient);
            tvFormulaB.setText(String.format("Между антеннами: B=%s*λ", strCoefficient));
            tvSizeB.setText(String.format("%s%s", new DecimalFormat("##.#").format(sizeB * intUnits), getString(R.string.units)));
        } catch (Exception e) {
            Log.d(TAG, "При вычислениях приложения произошла ошибка: " + e);
            StackTraceElement[] stackTraceElements = e.getStackTrace();

            for (int i = 0; i < stackTraceElements.length; i++) {
                Log.d(TAG, i + ": " + stackTraceElements[i].toString());
            }
//            this.onDestroy();
        }
    }

    double[] getSizeA(double freq) {
        double [] sizeA = new double [3];
        sizeA[0] = (300 / freq) / 2;
        sizeA[1] = (300 / freq) / 4;
        sizeA[2] = (300 / freq) / 8;
        return sizeA;
    }

    double getSizeB(double freq, double coefficient) {
        return coefficient * 300 / freq;
    }
}
