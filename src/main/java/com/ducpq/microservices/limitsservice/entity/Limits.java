package com.ducpq.microservices.limitsservice.entity;

import lombok.*;

/**
 * Limits
 *
 * @author Admin
 * @version 1.0
 * @since 2025-02-20
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Limits {
	private int minimum;
	private int maximum;
}
