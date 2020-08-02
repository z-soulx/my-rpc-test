package soulx.transport.command;

/**
 * @program: demo
 * @description: 请求和响应数据都抽象成了一个 Command 类
 * @author: soulx
 * @create: 2020-08-02 21:22
 **/
public class Command {
    protected Header header;
    private byte [] payload;

    public Command(Header header, byte [] payload) {
        this.header = header;
        this.payload = payload;
    }
    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public byte [] getPayload() {
        return payload;
    }

    public void setPayload(byte [] payload) {
        this.payload = payload;
    }
}
