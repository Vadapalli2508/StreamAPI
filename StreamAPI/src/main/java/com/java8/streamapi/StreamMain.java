package com.java8.streamapi;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamMain {

	public static void main(String[] args) {

		List<Employee> employeeList = new ArrayList<>();

		employeeList.add(Employee.builder().empId(101).empName("siva").gender("Male").depId(101).status("active")
				.salary(2000).build());
		employeeList.add(Employee.builder().empId(102).empName("reddy").gender("Female").depId(101).status("active")
				.salary(5000).build());
		employeeList.add(Employee.builder().empId(103).empName("raju").gender("Female").depId(102).status("inactive")
				.salary(6000).build());
		employeeList.add(Employee.builder().empId(104).empName("shivam").gender("Male").depId(102).status("inactive")
				.salary(4000).build());
		employeeList.add(Employee.builder().empId(105).empName("bob").gender("Male").depId(103).status("active")
				.salary(3500).build());
		employeeList.add(Employee.builder().empId(106).empName("alice").gender("Female").depId(103).status("inactive")
				.salary(3500).build());
		employeeList.add(Employee.builder().empId(107).empName("srinu").gender("Male").depId(104).status("active")
				.salary(3500).build());
		
		// increasing salary for employees whose salary is < 4000
		employeeList.stream()
		.filter(e->e.getSalary()<4000)
		.sorted((e,r)->e.getEmpName().compareTo(r.getEmpName()))
				.map(e->{int sal= (int) (e.getSalary()+(e.getSalary()*0.1));
								e.setSalary(sal);
								return e;
								}
								).forEach(System.out::println);

		// by depId
		Map<Integer, List<Employee>> empList = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepId, Collectors.toList()));
		empList.entrySet().forEach(System.out::println);

		// count by depId
		Map<Integer, Long> empCount = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepId, Collectors.counting()));
		empCount.entrySet().stream().forEach(c -> System.out.println(c.getKey() + "-----" + c.getValue()));
		
		Map<Integer,Long> empCount2= employeeList.stream().collect(Collectors.groupingBy(Employee::getDepId, Collectors.counting()));
		System.out.println(empCount2);

		// print active and inactive employees
		Map<String, List<Employee>> empStatus = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getStatus, Collectors.toList()));
		empStatus.entrySet().stream()
				.forEach(s -> System.out.println(s.getKey() + "--->" + s.getValue().stream().count()));

		System.out.println("inactive =" + employeeList.stream().filter(e -> e.getStatus().equals("inactive")).count());
		System.out.println("active =" + employeeList.stream().filter(e -> e.getStatus().equals("active")).count());

		// max salary
		System.out.println("Max Salary of employees is: "
				+ employeeList.stream().max((e1, e2) -> e1.getSalary() - e2.getSalary()));
		System.out.println(employeeList.stream().max(Comparator.comparing(Employee::getSalary)));

		///y min System.out.println("Max Salary on dept basis: ");

		// max salary on dept basis
		employeeList.stream().collect(Collectors.groupingBy(Employee::getDepId)).entrySet().stream()
				.forEach(s -> System.out.println(s.getValue().stream().max(Comparator.comparing(Employee::getSalary))));

		// count by gender
		Map<String, Long> empMap = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
		System.out.println(empMap);
		

	}
}
