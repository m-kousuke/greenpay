package com.greenpay.web;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SelesVolumeForm {

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate startDate;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate endDate;
}
