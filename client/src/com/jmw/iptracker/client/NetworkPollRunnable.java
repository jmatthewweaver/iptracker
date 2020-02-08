package com.jmw.iptracker.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

public class NetworkPollRunnable
implements Runnable {

    private long sleepInterval;

    private boolean stop;

    public NetworkPollRunnable(long sleepInterval) {
        this.sleepInterval = sleepInterval;
        this.stop = false;
    }

    public void terminate() {
        this.stop = true;
    }

    public boolean terminated() {
        return stop;
    }

    @Override
    public void run() {
        while(!stop) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface itf = networkInterfaces.nextElement();
                    if (itf.isUp()) {
                        System.out.println(itf.getName());
                        System.out.println(itf.getDisplayName());
                        System.out.println(itf.isLoopback());
                        for (InterfaceAddress address : itf.getInterfaceAddresses()) {
                            System.out.println(address.getAddress().getClass().getName());
                            System.out.println("  Canonical host name : " + address.getAddress().getCanonicalHostName());
                            System.out.println("  Host name           : " + address.getAddress().getHostName());
                            System.out.println("  Host address        : " + address.getAddress().getHostAddress());
                            System.out.println();
                        }
                        System.out.println();
                        System.out.println();
                    }
                }

                Socket socket = new Socket();
                socket.connect(new InetSocketAddress("google.com", 80));
                System.out.println(socket.getLocalAddress().getHostAddress());

                Thread.sleep(sleepInterval);
            } catch(IOException | InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
