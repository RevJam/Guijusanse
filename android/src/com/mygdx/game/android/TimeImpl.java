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
    public long startTime() {
        time+=currentThreadTimeMillis()-currentTimeSystem;
        return time;
    }

    @Override
    public long getTime() {
        return time;
    }
}
