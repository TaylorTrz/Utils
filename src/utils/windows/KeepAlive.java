package utils.windows;


import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.junit.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 保持windows的活动状态而不息屏
 *
 * @author taoruizhe
 * @date   2020/05/26
 */
public class KeepAlive {

    public interface CLibrary extends Library {
        CLibrary INSTANCE = (CLibrary) Native.load(Platform.isWindows() ? "msvcrt" : "c", CLibrary.class);

        void printf(String format, Object... args);
    }

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Robot robot = new Robot();
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 1, 1, TimeUnit.MINUTES);

        CLibrary.INSTANCE.printf("any");
        for (int i = 0; i < args.length; i++) {
            CLibrary.INSTANCE.printf("Arguments %d is %s", i, args[i]);
        }



    }



}
