package az.spring.rest.dmo.springrestdemo.enums;

public enum ErrorCodeEnum {
    NOT_FOUND_EMPLOYEE(1001, "Can not find employee with given id"),
    VALIDATION_ERROR(1002, " Is not valid"),
    UNKNOWN_ERROR(1003,"Unknown error");

    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
