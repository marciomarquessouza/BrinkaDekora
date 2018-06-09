package br.com.brinkaedekora.domain;

public class Response {

    private String status;
    private String msg;

    public static Response ok (String message) {
        Response response = new Response();
        response.setStatus("Ok");
        response.setMsg(message);
        return response;
    }

    public static Response error (String message) {
        Response response = new Response();
        response.setStatus("Error");
        response.setMsg(message);
        return response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
