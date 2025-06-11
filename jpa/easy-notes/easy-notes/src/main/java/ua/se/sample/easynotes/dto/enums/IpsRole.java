package ua.se.sample.easynotes.dto.enums;



import java.util.stream.Stream;

public enum IpsRole {
    unknown(0, "unknown"),// for debug and errors processing
    lawyer(1,"lawyer"),
    accounting(2,"accounting"),
    manager(3,"manager"),
    compliance(4, "compliance");

    private int code;
    private String value;

    IpsRole(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public static IpsRole of(int code) {
        return Stream.of(IpsRole.values())
                .filter(p -> p.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
