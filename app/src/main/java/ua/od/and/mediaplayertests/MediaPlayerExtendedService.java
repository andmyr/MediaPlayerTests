package ua.od.and.mediaplayertests;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MediaPlayerExtendedService extends Service {
    public MediaPlayerExtendedService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
