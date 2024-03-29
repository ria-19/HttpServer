package org.projects.httpserver.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket =  new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {
            Socket socket = null;

            while(serverSocket.isBound() && !serverSocket.isClosed()) {
                socket = serverSocket.accept(); // wait until it gets a connection

                LOGGER.info(" * Connection accepted: " + socket.getInetAddress());

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();
            }
        }
        catch (IOException e) {
            LOGGER.error("Problem with the setting socket", e);
        }
        finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {}
            }
        }
    }
}
