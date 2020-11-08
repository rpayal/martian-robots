package com.co4gsl.martianrobots.enums;

import com.co4gsl.martianrobots.direction.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Direction {

    N(new NorthDirection()),
    E(new EastDirection()),
    S(new SouthDirection()),
    W(new WestDirection());

    private final IDirection direction;
}
