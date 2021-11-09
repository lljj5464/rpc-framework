package github.lljj.exception;

/**
 * @author lihan
 * @create 2021-10-20 11:45 上午
 */
public class RpcException extends RuntimeException{
    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
}
