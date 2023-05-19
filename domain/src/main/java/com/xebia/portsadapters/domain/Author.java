package com.xebia.portsadapters.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Value object
 */
@Data
@AllArgsConstructor(staticName = "of")
public class Author {
    private final String name;
}
