package com.management.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.management.exception.CustomException;

@Component
public class ManagementMapper {
	public <T> T convertToEntity(Object srcObj, Class<T> targetClass) {
		T entity = null;

		try {
			entity = new ModelMapper().map(srcObj, targetClass);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return entity;
	}

	public <T> T convertToDto(Object srcObj, Class<T> targetClass) {
		T dto = null;

		try {
			dto = new ModelMapper().map(srcObj, targetClass);
		} catch (Exception e) {
			throw new CustomException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return dto;
	}
	
	public <T1, T> List<T> convertToDtos(List<T1> srcObjs, Class<T> targetClass) {
		List<T> dtos = new ArrayList<>();
		srcObjs.forEach(srcObj -> {
			T t = convert(srcObj, targetClass);
			dtos.add(t);
		});
		return dtos;
	}
	
	private <T> T convert(Object srcObj, Class<T> targetClass) {
		T targetObj = null;

		try {
			targetObj = new ModelMapper().map(srcObj, targetClass);
		} catch (Exception e) {
			throw new CustomException("Exception in convertion.message:- " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
		return targetObj;
	}
}
