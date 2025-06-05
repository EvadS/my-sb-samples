package ua.lz.ep.models.enums;

import lombok.Getter;

import java.util.stream.Stream;

@Getter
public enum IpsRole {

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

    public static IpsRole of(int code) {
        return Stream.of(IpsRole.values())
                .filter(p -> p.getCode() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
