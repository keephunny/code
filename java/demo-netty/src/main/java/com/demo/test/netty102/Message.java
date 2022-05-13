package com.demo.test.netty102;

import java.io.Serializable;

/**
 * 说明:
 * <p>
 * <br>
 * 创建时间：2022-05-01 18:36
 *
 * @author
 */
public class Message implements Serializable {

    private String id;

    private String name;

    public Message() {
    }

    public Message(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
