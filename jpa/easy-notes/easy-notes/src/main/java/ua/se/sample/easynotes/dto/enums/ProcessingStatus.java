package ua.se.sample.easynotes.dto.enums;

import java.util.stream.Stream;

public enum ProcessingStatus {
    success(1),
    fail(2);

    private final int status;

    ProcessingStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public static ProcessingStatus of(int code) {
        return Stream.of(ProcessingStatus.values())
                .filter(p -> p.getStatus() == code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
