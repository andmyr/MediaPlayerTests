package ua.od.and.mediaplayertests;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ExoService extends Service {
    public ExoService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
