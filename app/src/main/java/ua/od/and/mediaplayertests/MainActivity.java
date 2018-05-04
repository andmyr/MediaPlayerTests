package ua.od.and.mediaplayertests;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> trackList;
    private int currentMode = Constants.MODE_MP;

    @BindView(R.id.btnPrev)
    Button btnPrev;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnPause)
    Button btnPause;
    @BindView(R.id.btnNext)
    Button btnNext;
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.rbMediaPlayer)
    RadioButton rbMediaPlayer;
    @BindView(R.id.rbFFmpeg)
    RadioButton rbFFmpeg;
    @BindView(R.id.rbMediaPlayerExtended)
    RadioButton rbMediaPlayerExtended;
    @BindView(R.id.rbExoPlayer)
    RadioButton rbExoPlayer;
    @BindView(R.id.tvCurrentTrack)
    TextView tvCurrentTrack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rbMediaPlayer.setOnClickListener(this);
        rbFFmpeg.setOnClickListener(this);
        rbMediaPlayerExtended.setOnClickListener(this);
        rbExoPlayer.setOnClickListener(this);

        btnPrev.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        trackList = new ArrayList<>();
        trackList.add("https://tracks.louder.me/5ae969bbb707d5002de2074b/stream/sample");
        trackList.add("https://tracks.louder.me/5ae967b0b707d5002de2070e/stream/sample");
        trackList.add("https://tracks.louder.me/5aea0550b707d5002de29938/stream/sample");
        trackList.add("https://tracks.louder.me/5ae9674e8746e7002d649136/stream/sample");
        trackList.add("https://tracks.louder.me/5ae9699bb707d5002de20747/stream/sample");
        trackList.add("https://tracks.louder.me/5ae968728746e7002d649149/stream/sample");
        trackList.add("https://tracks.louder.me/5ae969e08746e7002d64918a/stream/sample");
        trackList.add("https://tracks.louder.me/5ae966c9b707d5002de20703/stream/sample");
        trackList.add("https://tracks.louder.me/5ae968a38746e7002d64914d/stream/sample");
        trackList.add("https://tracks.louder.me/5ae968308746e7002d649145/stream/sample");
        trackList.add("https://tracks.louder.me/5ae966a7b707d5002de206fe/stream/sample");
        trackList.add("https://tracks.louder.me/5ae969278746e7002d649155/stream/sample");
        trackList.add("https://tracks.louder.me/5ae969fb8746e7002d64918e/stream/sample");
        trackList.add("https://tracks.louder.me/5ae9694d8746e7002d64916d/stream/sample");
        trackList.add("https://tracks.louder.me/5aeacbb68746e7002d654447/stream/sample");
        trackList.add("https://tracks.louder.me/5ae968e28746e7002d649151/stream/sample");
        trackList.add("https://tracks.louder.me/5ae96968b707d5002de2073d/stream/sample");
        trackList.add("https://tracks.louder.me/5aea4a1fb707d5002de29b88/stream/sample");
        trackList.add("https://tracks.louder.me/5ae967d7b707d5002de20712/stream/sample");
        trackList.add("https://tracks.louder.me/5ae966f68746e7002d64912d/stream/sample");

        trackList.add("https://tracks.louder.me/5ae969bbb707d5002de2074b/stream");
        trackList.add("https://tracks.louder.me/5ae967b0b707d5002de2070e/stream");
        trackList.add("https://tracks.louder.me/5aea0550b707d5002de29938/stream");
        trackList.add("https://tracks.louder.me/5ae9674e8746e7002d649136/stream");
        trackList.add("https://tracks.louder.me/5ae9699bb707d5002de20747/stream");
        trackList.add("https://tracks.louder.me/5ae968728746e7002d649149/stream");
        trackList.add("https://tracks.louder.me/5ae969e08746e7002d64918a/stream");
        trackList.add("https://tracks.louder.me/5ae966c9b707d5002de20703/stream");
        trackList.add("https://tracks.louder.me/5ae968a38746e7002d64914d/stream");
        trackList.add("https://tracks.louder.me/5ae968308746e7002d649145/stream");
        trackList.add("https://tracks.louder.me/5ae966a7b707d5002de206fe/stream");
        trackList.add("https://tracks.louder.me/5ae969278746e7002d649155/stream");
        trackList.add("https://tracks.louder.me/5ae969fb8746e7002d64918e/stream");
        trackList.add("https://tracks.louder.me/5ae9694d8746e7002d64916d/stream");
        trackList.add("https://tracks.louder.me/5aeacbb68746e7002d654447/stream");
        trackList.add("https://tracks.louder.me/5ae968e28746e7002d649151/stream");
        trackList.add("https://tracks.louder.me/5ae96968b707d5002de2073d/stream");
        trackList.add("https://tracks.louder.me/5aea4a1fb707d5002de29b88/stream");
        trackList.add("https://tracks.louder.me/5ae967d7b707d5002de20712/stream");
        trackList.add("https://tracks.louder.me/5ae966f68746e7002d64912d/stream");

        Intent intent = new Intent(this, MediaPlayerService.class);
        intent.putExtra("data", trackList);
        startService(intent);

        intent = new Intent(this, MediaPlayerExtendedService.class);
        intent.putExtra("data", trackList);
        startService(intent);

        intent = new Intent(this, FFmpegService.class);
        intent.putExtra("data", trackList);
        startService(intent);

        intent = new Intent(this, ExoService.class);
        intent.putExtra("data", trackList);
        startService(intent);
    }

    @Override
    public void onClick(View view) {
        //RadioButton rb = (RadioButton) view;
        switch (view.getId()) {
            case R.id.rbMediaPlayer:
                tvCurrentTrack.setText("rbMediaPlayer");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.STOP));
                currentMode = Constants.MODE_MP;
                break;
            case R.id.rbFFmpeg:
                tvCurrentTrack.setText("rbFFmpeg");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.STOP));
                currentMode = Constants.MODE_FF_MPEG;
                break;
            case R.id.rbMediaPlayerExtended:
                tvCurrentTrack.setText("rbMediaPlayerExtended");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.STOP));
                currentMode = Constants.MODE_MP_E;
                break;
            case R.id.rbExoPlayer:
                tvCurrentTrack.setText("rbExoPlayer");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.STOP));
                currentMode = Constants.MODE_EXO;
                break;
            case R.id.btnPrev:
                tvCurrentTrack.setText("btnPrev");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.PREV));
                break;
            case R.id.btnNext:
                tvCurrentTrack.setText("btnNext");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.NEXT));
                break;
            case R.id.btnPlay:
                tvCurrentTrack.setText("btnPlay");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.PLAY));
                break;
            case R.id.btnPause:
                tvCurrentTrack.setText("btnPause");
                EventBus.getDefault().post(new MessageEvent(currentMode, Constants.PAUSE));
                break;

            default:
                break;
        }
    }
}
