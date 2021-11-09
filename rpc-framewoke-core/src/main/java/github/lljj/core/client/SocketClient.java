package github.lljj.core.client;

import github.lljj.dto.RpcRequest;
import github.lljj.exception.RpcException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author lihan
 * @create 2021-10-20 11:21 上午
 */
public class SocketClient {

    public Object sendRpcRequest(RpcRequest rpcRequest){
        //TODO 通过zookeeper获取服务地址
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8888);
        try(Socket socket = new Socket()){
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RpcException("调用Rpc失败：", e);
        }

    }

}
