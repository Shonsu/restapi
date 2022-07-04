package pl.shonsu.restapi.controller.exceptionshandling.exceptions;

import java.time.LocalDateTime;

public record ExceptionMessage(LocalDateTime localDateTime, String httpStatus, String message) {
}
