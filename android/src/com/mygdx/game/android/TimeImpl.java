package com.mygdx.game.android;

import com.mygdx.game.Game.TimeInterface;

import static android.os.SystemClock.currentThreadTimeMillis;
import static android.os.SystemClock.uptimeMillis;

/**
 * Created by sebastien on 02/01/15.
 */
public class TimeImpl implements TimeInterface {

    private long time=0;
    private long currentTimeSystem;
    Thread d;
    public TimeImpl(){
        time = 0;
        currentTimeSystem = currentThreadTimeMillis();
    }

    @Override
    public void startTime() {
    time=currentThreadTimeMillis()-currentTimeSystem;
    d =new Thread(new Runnable() {
        @Override
        public void run() {
            time+=currentThreadTimeMillis();
            System.out.println(time);

        }
    });
        d.start();
    }

    @Override
    public long getTime() {
        return time;
    }
}
