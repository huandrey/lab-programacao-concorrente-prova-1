package SimuladorRequest.src.services;

public enum StatusCodeResponse {
    SUCCESS(200, "sucess"),
    ERROR(500, "error");

    private final int code;
    private final String key;

    StatusCodeResponse(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }
}
