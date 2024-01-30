package com.example.psds.personal_account.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomErrorMessage {
    USER_NOT_FOUND(404, "Пользователь не найден"),
    GROUP_NOT_FOUND(404, "Группа не найдена"),
    USER_NOT_BELONG_TO_GROUP(403, "Пользователь не принадлежит группе"),
    ACCESS_DENIED(403, "В доступе отказано"),
    ;

    private final Integer code;
    private final String message;
}
