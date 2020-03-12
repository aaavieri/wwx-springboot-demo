package com.wwx.tob.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;

@Data
@Accessors(chain = true)
@EqualsAndHashCode
public class ResponseDto {
	private int errCode = 0;
	private String errMsg = "";
	private boolean success = true;
	private boolean applicationError = false;
	private Object data = new HashMap<>();
}
