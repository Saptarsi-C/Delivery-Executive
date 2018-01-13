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
public enum ActivityEnum {

	/** The inactive. */
	BUSY("busy", Byte.valueOf("1")),
	/** The active. */
	FREE("free", Byte.valueOf("0"));

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
	ActivityEnum(String desc, byte value) {
		this.value = value;
		this.desc = desc;
	}

	/** The name 2 enum. */
	public static BiMap<String, ActivityEnum> name2Enum = new ImmutableBiMap.Builder<String, ActivityEnum>()
			.putAll(Arrays.stream(values()).collect(Collectors.toMap(ActivityEnum::getDesc, Function.identity())))
			.build();

	/** The id 2 enum. */
	public static BiMap<Byte, ActivityEnum> id2Enum = new ImmutableBiMap.Builder<Byte, ActivityEnum>()
			.putAll(Arrays.stream(values()).collect(Collectors.toMap(ActivityEnum::getValue, Function.identity())))
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
