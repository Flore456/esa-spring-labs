package ru.ssau.esa.response;

public class GoodResponse extends ServerResponse {

    public GoodResponse(Object message) {
        super(true, message);
    }

    public GoodResponse() {
        super(true, "");
    }
}
