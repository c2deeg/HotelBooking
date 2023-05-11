package com.app.LukandaH.Activities.WriteReviewActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.LukandaH.Activities.FragmentBackPress.IOnBackPressed;
import com.app.LukandaH.Activities.WriteReviewActivity.Presenter.WriteReviewPresenter;
import com.app.LukandaH.Activities.WriteReviewActivity.View.WriteReviewView;
import com.app.LukandaH.R;
import com.app.LukandaH.Utils.Utils;

public class WriteReviewActivity extends AppCompatActivity implements View.OnClickListener, WriteReviewView {
    public static IOnBackPressed backPressed;
    private Activity activity;
    private EditText et_review;
    private ImageView img_star1;
    private ImageView img_star2;
    private ImageView img_star3;
    private ImageView img_star4;
    private ImageView img_star5;
    private Button btn_confirmReview;
    private final String skyBlue = "#02A6B8";
    private final String gray = "#40000000";
    private int rating = 0;
    private ImageView img_back;
    private String hotelId;
    private WriteReviewPresenter writeReviewPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        activity = this;
        writeReviewPresenter = new WriteReviewPresenter(activity, this);
        hotelId = getIntent().getStringExtra("hotelId");
        init();
        listeners();
        etScrolling();
    }

    private void init() {
        et_review = findViewById(R.id.et_review);
        img_star1 = findViewById(R.id.img_star1);
        img_star2 = findViewById(R.id.img_star2);
        img_star3 = findViewById(R.id.img_star3);
        img_star4 = findViewById(R.id.img_star4);
        img_star5 = findViewById(R.id.img_star5);
        btn_confirmReview = findViewById(R.id.btn_confirmReview);
        img_back = findViewById(R.id.img_back);
    }

    private void listeners() {
        img_star1.setOnClickListener(this);
        img_star2.setOnClickListener(this);
        img_star3.setOnClickListener(this);
        img_star4.setOnClickListener(this);
        img_star5.setOnClickListener(this);
        btn_confirmReview.setOnClickListener(this);
        img_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == img_star1) {
            img_star1.setImageResource(R.drawable.rating_star_skyblue);
            img_star2.setImageResource(R.drawable.rating_star);
            img_star3.setImageResource(R.drawable.rating_star);
            img_star4.setImageResource(R.drawable.rating_star);
            img_star5.setImageResource(R.drawable.rating_star);
            rating = 1;
        } else if (v == img_star2) {
            img_star1.setImageResource(R.drawable.rating_star_skyblue);
            img_star2.setImageResource(R.drawable.rating_star_skyblue);
            img_star3.setImageResource(R.drawable.rating_star);
            img_star4.setImageResource(R.drawable.rating_star);
            img_star5.setImageResource(R.drawable.rating_star);
            rating = 2;
        } else if (v == img_star3) {
            img_star1.setImageResource(R.drawable.rating_star_skyblue);
            img_star2.setImageResource(R.drawable.rating_star_skyblue);
            img_star3.setImageResource(R.drawable.rating_star_skyblue);
            img_star4.setImageResource(R.drawable.rating_star);
            img_star5.setImageResource(R.drawable.rating_star);
            rating = 3;
        } else if (v == img_star4) {
            img_star1.setImageResource(R.drawable.rating_star_skyblue);
            img_star2.setImageResource(R.drawable.rating_star_skyblue);
            img_star3.setImageResource(R.drawable.rating_star_skyblue);
            img_star4.setImageResource(R.drawable.rating_star_skyblue);
            img_star5.setImageResource(R.drawable.rating_star);
            rating = 4;
        } else if (v == img_star5) {
            img_star1.setImageResource(R.drawable.rating_star_skyblue);
            img_star2.setImageResource(R.drawable.rating_star_skyblue);
            img_star3.setImageResource(R.drawable.rating_star_skyblue);
            img_star4.setImageResource(R.drawable.rating_star_skyblue);
            img_star5.setImageResource(R.drawable.rating_star_skyblue);
            rating = 5;
        } else if (v == btn_confirmReview) {
            writeReviewPresenter.writeReviewMethod(hotelId,rating,et_review);
        } else if (v == img_back) {
            finish();
        }
    }

    private void etScrolling() {
        et_review.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.et_review) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void showMessage(Activity activity, String msg) {
        Utils.showMessage(activity, msg);
    }

    @Override
    public void showDialog(Activity activity) {
        Utils.showDialogMethod(activity, getSupportFragmentManager());
    }

    @Override
    public void hideDialog() {
        Utils.hideDialog();
    }
}