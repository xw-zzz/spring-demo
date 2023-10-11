package io.github.xw.model;

/**
 * @author xw
 * @date 2023/10/9
 */
public class MyBean {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }
}
