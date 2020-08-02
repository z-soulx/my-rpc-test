package soulx.transport.command;

/**
 * @program: demo
 * @description: 头对象定义
 * @author: soulx
 * @create: 2020-08-02 21:23
 **/
public class Header {
    /**
    * @Description: requestId 用于唯一标识一个请求命令
    * @Author: soulx
    */
    private int requestId;
    //  这个属性用于标识这条命令的版本号。type 用于标识这条命令的类型
    private int version;
    private int type;

    public Header() {}
    public Header(int type, int version, int requestId) {
        this.requestId = requestId;
        this.type = type;
        this.version = version;
    }
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getType() {
        return type;
    }

    public int length() {
        return Integer.BYTES + Integer.BYTES + Integer.BYTES;
    }

    public void setType(int type) {
        this.type = type;
    }
}
