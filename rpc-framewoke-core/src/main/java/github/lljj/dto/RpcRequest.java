package github.lljj.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author lihan
 * @create 2021-10-17 12:03 下午
 */
@Setter
@Getter
@Builder
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 567233335565261862L;
    private String methodName;
    private String interfaceName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
    private String requestId;
    private String version;
    private String group;

    private <T> void from(T a, Collection<T> b){
        
    }
}
