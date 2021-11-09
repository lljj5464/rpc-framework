package github.lljj.core.server;

import github.lljj.dto.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author lihan
 * @create 2021-11-09 1:42 下午
 */
@Slf4j
public class SocketServerRunnable implements Runnable{

    private Socket socket;
    //TODO add service center

    public SocketServerRunnable(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        log.info("Socketserver handle message from client by thread: [{}]", Thread.currentThread().getName());
        try(ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());){
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            //TODO invoke method
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
