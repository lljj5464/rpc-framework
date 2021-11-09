package github.lljj.dto;

import github.lljj.enums.RpcResponseEnum;
import lombok.*;

import java.io.Serializable;

/**
 * @author lihan
 * @create 2021-11-09 2:07 下午
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RpcResponse<T> implements Serializable {

    private static final long serialVersionUID = -7608431642081259425L;
    private String requestId;
    private Integer code;
    private String message;
    private T data;

    public static<T> RpcResponse<T> success(T data, String requestId){
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(RpcResponseEnum.SUCCESS.getCode());
        response.setMessage(RpcResponseEnum.SUCCESS.getMessage());
        response.setData(data);
        response.setRequestId(requestId);
        return response;
    }

    public static<T> RpcResponse<T> fail(){
        RpcResponse<T> response = new RpcResponse<>();
        response.setCode(RpcResponseEnum.FAIL.getCode());
        response.setMessage(RpcResponseEnum.FAIL.getMessage());
        return response;
    }
}
