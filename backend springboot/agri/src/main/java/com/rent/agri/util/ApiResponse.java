package com.rent.agri.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
	
    private boolean success;   
    private int status;     
    private String message;   
    private T data;        
}
