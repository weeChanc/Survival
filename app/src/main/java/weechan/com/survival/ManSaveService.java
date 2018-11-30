package weechan.com.survival;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/13 21:19
 */

public class ManSaveService extends AccessibilityService {

    private SharedPreferences preference;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    public void onCreate() {
        super.onCreate();
        preference = getApplicationContext().getSharedPreferences("gfs",MODE_PRIVATE);
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("http://xmyd.sc.chinaz.com/files/download/sound1/201311/3761.wav");
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        final List<String> contracts = new Gson().fromJson(preference.getString("gfs","[]"),
                new TypeToken<List<String>>(){}.getType());
        String message = join(event.getText());

        for (int i = 0; i < contracts.size(); i++) {
            String name = contracts.get(i);
            if(message.startsWith(name)){
                Log.e("ManSaveService",message+ " " + name);
                mediaPlayer.start();
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            synchronized (this){
                AccessibilityNodeInfo window = getRootInActiveWindow();
                if(window!= null ){
                    List<AccessibilityNodeInfo> nodes = window.findAccessibilityNodeInfosByText("登录");
                    if(nodes.size() == 3 ){
                        for (int i = 0; i < nodes.size(); i++) {
                            nodes.get(1).performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        }
                    }

                }
            }
        }

    }

    @Override
    public void onInterrupt() {

    }

    private String join(List<CharSequence> characters){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < characters.size(); i++) {
            sb.append(characters.get(i));
        }
        return sb.toString().trim();
    }

}
