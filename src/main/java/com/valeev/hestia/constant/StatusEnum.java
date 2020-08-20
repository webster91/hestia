package com.valeev.hestia.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    ERROR("Ошибка"),
    SUCCESS("Выполнена"),
    WORK("В работе"),
    CREATE("Создана"),
    ;

    private final String name;

    public static StatusEnum fromName(String text) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.name.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return name;
    }
}
