package lafourchette.com.lafourchette;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Ali on 10/02/2016.
 */

public class Application extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }
}
