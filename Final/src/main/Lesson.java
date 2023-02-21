package main;

import java.util.*;
import student.Student;
import teacher.Teacher;
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Lesson implements Serializable {
	Teacher teacher;
	public String name;
	LocalDate date;
	TypeOfLesson TOL;
	LocalTime startTime;
	LocalTime endTime;
	LocalDateTime time;
	public int classroom;
	public HashMap<Student, Mark> mark = new HashMap<>();

	public Lesson() {
	}

	public Lesson(int date, int hour, int min) {
		LocalDateTime time = LocalDateTime.of(2022, Month.APRIL, date, hour, min);
		this.time = time;
	}

	@Override
	public String toString() {
		return "Lesson [ time=" + time + ", classroom=" + classroom + "]";
	}

}
