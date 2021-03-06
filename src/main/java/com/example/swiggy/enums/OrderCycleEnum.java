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
public enum OrderCycleEnum {

	PLACED("placed", Byte.valueOf("1")),

	SUCCESS("success", Byte.valueOf("2")),
	
	RECEIVED("received", Byte.valueOf("3")),
	
	DELIVERED("delivered", Byte.valueOf("4")),
	
	CANCELLED("cancelled", Byte.valueOf("0"));
	
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
	OrderCycleEnum(String desc, byte value) {
		this.value = value;
		this.desc = desc;
	}

	/** The name 2 enum. */
	public static BiMap<String, OrderCycleEnum> name2Enum = new ImmutableBiMap.Builder<String, OrderCycleEnum>()
			.putAll(Arrays.stream(values()).collect(Collectors.toMap(OrderCycleEnum::getDesc, Function.identity())))
			.build();

	/** The id 2 enum. */
	public static BiMap<Byte, OrderCycleEnum> id2Enum = new ImmutableBiMap.Builder<Byte, OrderCycleEnum>()
			.putAll(Arrays.stream(values()).collect(Collectors.toMap(OrderCycleEnum::getValue, Function.identity())))
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
