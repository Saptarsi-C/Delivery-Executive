/**
 * 
 */
package com.example.swiggy.enums;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;

/**
 * @author saptarsichaurashy
 *
 */
public enum StatusEnum {

	/** The inactive. */
	INACTIVE("active", Byte.valueOf("0")),
	/** The active. */
	ACTIVE("inactive", Byte.valueOf("1"));

	/** The value. */
	private byte value;

	/** The desc. */
	private String desc;

	/**
	 * Instantiates a new db status enum.
	 *
	 * @param desc
	 *            the desc
	 * @param value
	 *            the value
	 */
	StatusEnum(String desc, byte value) {
		this.value = value;
		this.desc = desc;
	}

	/** The name 2 enum. */
	public static BiMap<String, StatusEnum> name2Enum = new ImmutableBiMap.Builder<String, StatusEnum>()
			.putAll(Arrays.stream(values()).collect(Collectors.toMap(StatusEnum::getDesc, Function.identity())))
			.build();

	/** The id 2 enum. */
	public static BiMap<Byte, StatusEnum> id2Enum = new ImmutableBiMap.Builder<Byte, StatusEnum>()
			.putAll(Arrays.stream(values()).collect(Collectors.toMap(StatusEnum::getValue, Function.identity())))
			.build();

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public byte getValue() {
		return value;
	}

	/**
	 * Gets the desc.
	 *
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}
}
