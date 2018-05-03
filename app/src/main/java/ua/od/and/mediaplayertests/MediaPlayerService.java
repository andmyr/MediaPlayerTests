package ua.od.and.mediaplayertests;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
                    if (mp == null) {
                        mp = new MediaPlayer();
                        mp.se
                    }
                    break;
                case PAUSE:

                    break;
                case NEXT:

                    break;
                case PREV:

                    break;
                case STOP:

                    break;
                default: {
                }
            }
        }
    }


}
