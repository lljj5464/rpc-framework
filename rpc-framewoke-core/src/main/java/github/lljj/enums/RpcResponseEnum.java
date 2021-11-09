package github.lljj.enums;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lihan
 * @create 2021-11-09 7:50 下午
 */
@Getter
@ToString
public enum RpcResponseEnum {
    SUCCESS(1, "Rpc call remote method success"),
    FAIL(2, "Rpc call remote method fail");
    private final int code;
    private final String message;


    RpcResponseEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
