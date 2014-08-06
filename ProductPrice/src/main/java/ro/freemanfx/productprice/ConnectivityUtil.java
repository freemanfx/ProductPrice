package ro.freemanfx.productprice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityUtil {
    private final Context context;

    public ConnectivityUtil(Context context) {
        this.context = context;
    }

    public boolean isConnected() {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();

        if (isConnectedState(wifi) || isConnectedState(mobile)) {
            return true;
        }
        return false;
    }

    private boolean isConnectedState(NetworkInfo.State connectionState) {
        return connectionState == NetworkInfo.State.CONNECTED || connectionState == NetworkInfo.State.CONNECTING;
    }
}
