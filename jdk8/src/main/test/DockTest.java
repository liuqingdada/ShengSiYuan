import java.io.File;
import java.util.Arrays;

public class DockTest {

    private String str = "/home/suhen/.cache/mintinstall/screenshots/cairo-dock_1.png\n" +
            "/usr/share/app-install/desktop/cairo-dock-core:cairo-dock.desktop\n" +
            "/usr/share/app-install/icons/cairo-dock.svg\n" +
            "/usr/share/icons/Mint-X/apps/16/cairo-dock.png\n" +
            "/usr/share/icons/Mint-X/apps/22/cairo-dock.png\n" +
            "/usr/share/icons/Mint-X/apps/24/cairo-dock.png\n" +
            "/usr/share/icons/Mint-X/apps/32/cairo-dock.png\n" +
            "/usr/share/icons/Mint-X/apps/48/cairo-dock.png\n" +
            "/usr/share/icons/Mint-X/apps/96/cairo-dock-c.svg\n" +
            "/usr/share/icons/Mint-X/apps/96/cairo-dock-o.svg\n" +
            "/usr/share/icons/Mint-X/apps/96/cairo-dock.svg\n" +
            "/usr/share/icons/Mint-Y/apps/16/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/22/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/24/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/256/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/32/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/48/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/64/cairo-dock.png\n" +
            "/usr/share/icons/Mint-Y/apps/96/cairo-dock.png\n" +
            "/usr/share/slick-greeter/badges/cairo-dock-fallback.png\n" +
            "/usr/share/slick-greeter/badges/cairo-dock-unity.png\n" +
            "/usr/share/slick-greeter/badges/cairo-dock.png\n" +
            "/var/cache/apt/archives/cairo-dock-core_3.4.1-0ubuntu1_amd64.deb\n" +
            "/var/cache/apt/archives/cairo-dock-data_3.4.1-0ubuntu1_all.deb\n" +
            "/var/cache/apt/archives/cairo-dock-plug-ins-data_3.4.1-0ubuntu7_all.deb\n" +
            "/var/cache/apt/archives/cairo-dock-plug-ins-dbus-interface-python_3.4.1-0ubuntu7_all" +
            ".deb\n" +
            "/var/cache/apt/archives/cairo-dock-plug-ins-integration_3.4.1-0ubuntu7_amd64.deb\n" +
            "/var/cache/apt/archives/cairo-dock-plug-ins_3.4.1-0ubuntu7_amd64.deb\n" +
            "/var/cache/apt/archives/cairo-dock_3.4.1-0ubuntu1_all.deb";

    private void deleteFiles() {
        System.out.println("start ... ...");

        String[] split = str.split("\n");
        Arrays.stream(split)
              .forEach(item -> {
                  System.out.println(item);

                  File file = new File(item);
                  System.out.println(file.canWrite());
                  System.out.println(file.delete());
              });
    }

    public static void main(String[] args) {
        new DockTest().deleteFiles();
    }
}
