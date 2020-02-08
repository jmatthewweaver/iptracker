package com.jmw.iptracker.client;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Main {

    private NetworkPollRunnable networkPollRunnable;
    private Thread networkPollThread;

    public void run() {
        Toolkit.getDefaultToolkit();
        if(SystemTray.isSupported()) {
            try {
                SystemTray tray = SystemTray.getSystemTray();
                Image image = ImageIO.read(getClass().getResourceAsStream("icon.png"));
                TrayIcon trayIcon = new TrayIcon(image);
                MenuItem exitItem = new MenuItem("Exit");
                exitItem.addActionListener(event -> {
                    networkPollRunnable.terminate();
                    tray.remove(trayIcon);
                });

                PopupMenu popupMenu = new PopupMenu();
                popupMenu.add(exitItem);
                trayIcon.setPopupMenu(popupMenu);
                tray.add(trayIcon);
            } catch(IOException | AWTException e) {
                e.printStackTrace();
            }
        }

        networkPollRunnable = new NetworkPollRunnable(5000L);
        networkPollThread = new Thread(networkPollRunnable);
        networkPollThread.run();

        while(!networkPollRunnable.terminated()) {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
