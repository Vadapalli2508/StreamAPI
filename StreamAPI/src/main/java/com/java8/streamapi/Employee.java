package com.java8.streamapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

	private int empId;
	private String empName;
	private String gender;
	private int depId;
	private String status;
	private int salary;
}
