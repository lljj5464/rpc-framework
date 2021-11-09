package github.lljj.core.server;

import github.lljj.utils.ThreadPoolFactoryUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @author lihan
 * @create 2021-10-20 11:47 上午
 */
@Slf4j
public class SocketServer {

    public static final int PORT = 9998;
    private final ExecutorService threadPool;

    public SocketServer() {
        this.threadPool = ThreadPoolFactoryUtil.createCustomThreadPoolIfAbsent("socket-server-thread-pool");

    }

    public void start(){
        try(ServerSocket serverSocket = new ServerSocket()){
            String host = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(host, PORT));
//            CustomShutdownHook.getCustomShutdownHook().clearAll();
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                threadPool.execute(new SocketServerRunnable(socket));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
