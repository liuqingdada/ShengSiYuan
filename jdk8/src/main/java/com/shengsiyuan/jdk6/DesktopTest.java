package com.shengsiyuan.jdk6;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URI;

/**
 * Created by cooper
 * 2020/11/11.
 * Email: 1239604859@qq.com
 */
class DesktopTest {
    public static void main(String[] args) throws Exception {
        Desktop.getDesktop().browse(URI.create("https://www.baidu.com/"));

        if (SystemTray.isSupported()) {
            BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
            SystemTray.getSystemTray().add(new TrayIcon(image));
        }
    }
}
