package com.info.presentation.shared.reponse;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BadRequestResponse {

    private String field;
    private String message;
}
