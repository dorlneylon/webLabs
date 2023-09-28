package com.nylon.second.servlets.beans;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class HttpError implements Serializable {
    private int statusCode;
    private String errorMessage;
}