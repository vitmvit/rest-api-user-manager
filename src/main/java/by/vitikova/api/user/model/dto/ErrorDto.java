package by.vitikova.api.user.model.dto;

public record ErrorDto(
        String errorMessage,
        Integer errorCode) {
}