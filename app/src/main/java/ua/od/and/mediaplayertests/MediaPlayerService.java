package ua.od.and.mediaplayertests;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;

import static ua.od.and.mediaplayertests.Constants.LOG_TAG;
import static ua.od.and.mediaplayertests.Constants.MODE_MP;
import static ua.od.and.mediaplayertests.Constants.NEXT;
import static ua.od.and.mediaplayertests.Constants.PAUSE;
import static ua.od.and.mediaplayertests.Constants.PLAY;
import static ua.od.and.mediaplayertests.Constants.PREV;
import static ua.od.and.mediaplayertests.Constants.STOP;

public class MediaPlayerService extends Service {
    private MediaPlayer mp;
    private int currentTrackNo = 0;
    private int currentStatus = Constants.STOP;

    private ArrayList<String> trackList;

    public MediaPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        trackList = intent.getStringArrayListExtra("data");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event.getTo() == MODE_MP) {
            switch (event.getCode()) {
                case PLAY:
                    Log.i(Constants.LOG_TAG, "PLAY");
                    if (mp == null) {
                        mp = new MediaPlayer();
                    } else {
                        mp.reset();
                    }
                    prepareAndPlay();
                    break;
                case PAUSE:
                    Log.i(Constants.LOG_TAG, "PAUSE");
                    if (currentStatus == Constants.PLAY) {
                        currentStatus = Constants.PAUSE;
                        mp.pause();
                    }
                    break;
                case NEXT:
                    if (currentTrackNo < trackList.size() - 1) {
                        currentTrackNo++;
                    } else {
                        currentTrackNo = 0;
                    }
                    Log.i(Constants.LOG_TAG, "NEXT " + currentTrackNo);
                    switch (currentStatus) {
                        case Constants.PAUSE:
                        case Constants.STOP:
                            break;
                        case Constants.PLAY:
                            mp.stop();
                            prepareAndPlay();
                            break;
                        case Constants.INIT:
                            mp.reset();
                            mp = null;
                            mp = new MediaPlayer();
                            prepareAndPlay();
                            break;
                    }
                    break;
                case PREV:
                    if (currentTrackNo > 0) {
                        currentTrackNo--;
                    } else {
                        currentTrackNo = trackList.size() - 1;
                    }
                    Log.i(Constants.LOG_TAG, "PREV " + currentTrackNo);
                    switch (currentStatus) {
                        case Constants.PAUSE:
                        case Constants.STOP:
                            break;
                        case Constants.PLAY:
                            mp.stop();
                            prepareAndPlay();
                            break;
                        case Constants.INIT:
                            mp.reset();
                            mp = null;
                            mp = new MediaPlayer();
                            prepareAndPlay();
                            break;
                    }
                    break;
                case STOP:
                    Log.i(Constants.LOG_TAG, "STOP");
                    if (mp != null) {
                        mp.reset();
                        mp = null;
                    }
                    break;
                default: {
                    Log.i(Constants.LOG_TAG, "Unknown command");
                }
            }
        }
    }

    private void prepareAndPlay() {
        if (mp == null) {
            mp = new MediaPlayer();
        }else{
            mp.reset();
        }
        try {
            mp.setDataSource(trackList.get(currentTrackNo));
            mp.setAudioAttributes(new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Log.i(LOG_TAG, "OnPreparedListener onPrepared");
                currentStatus = Constants.PLAY;
                mp.start();
                Log.i(LOG_TAG, "Playing");
            }
        });
        currentStatus = Constants.INIT;
        mp.prepareAsync();
        Log.i(LOG_TAG, "prepareAsync " + currentTrackNo);
        return;
    }


}
