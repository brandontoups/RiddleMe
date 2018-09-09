package edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * Created by Brandon on 5/2/18.
 *
 * names:       Brandon Toups, Evan McCarthy
 * email:       bmt0015        esm0012
 * date:        2 May 2018
 * class:       COMP3710
 * team:        ArrayIndexOutOfBoundsException
 * package:     edu.auburn.eng.csse.comp3710.spring2018.ArrayIndexOutOfBoundsException;
 * file:        BackgroundSoundService.java
 *
 */

// handles the creation of a background service for playing persistent music.
// https://stackoverflow.com/a/8209975
public class BackgroundSoundService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.star);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);

    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        return START_NOT_STICKY;
    }

    public void onStart(Intent intent, int startId) {
        // TO DO
    }
    public IBinder onUnBind(Intent arg0) {
        // TO DO Auto-generated method
        return null;
    }

    public void onStop() {
        player.stop();
        player.release();

    }
    public void onPause() {
        player.stop();
        player.release();
    }
    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}