package com.wwx.tob.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppUtil {

	public static <T> List<T> nvlList(List<T> list) {
		return list == null ? new ArrayList<>() : list;
	}
    
    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.size() == 0;
    }
}
