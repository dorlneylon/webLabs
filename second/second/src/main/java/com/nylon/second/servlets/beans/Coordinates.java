package com.nylon.second.servlets.beans;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Coordinates implements Serializable {
    private double x;
    private double y;
    private double r;
}