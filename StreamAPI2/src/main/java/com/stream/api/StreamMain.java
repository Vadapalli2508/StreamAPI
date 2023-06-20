package com.stream.api;

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
				.salary(3700).build());
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
				
		// divide by dep
		Map<Integer, List<Employee>> empMapDep = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepId, Collectors.toList()));
		System.out.println(empMapDep);
		// count by dep
		Map<Integer, Long> empMapCountDep = employeeList.stream()
				.collect(Collectors.groupingBy(Employee::getDepId, Collectors.counting()));
		System.out.println(empMapCountDep);
		// print active and inactive
		Map<String, List<Employee>> empMapActiv= employeeList.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.toList()));
		System.out.println(empMapActiv);
		// max salary
		System.out.println(employeeList.stream().max((o1,o2)->o1.getSalary()-o2.getSalary()));
		
		System.out.println(employeeList.stream().max(Comparator.comparing(Employee::getSalary)));
		// min salary

		// max salary on dep basis
		employeeList.stream().collect(Collectors.groupingBy(Employee::getDepId)).entrySet().stream().forEach(s->System.out.println(s.getValue().stream().max(Comparator.comparing(Employee::getSalary))));
		// count by gender
		System.out.println(employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting())));
	}
}
