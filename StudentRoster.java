package com.assignment8;

//vishvajit Gadakari
//Shiv Patil

import java.util.*;
class Student {
    String name;
    String id;
    ArrayList<Course> courses;

    public Student(String name, String id) {
	this.name = name;
	this.id = id;
	this.courses = new ArrayList<>();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public ArrayList<Course> getCourses() {
	return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
	this.courses = courses;
    }
}

class Course {
    String id;
    String name;
    int sem;

    public Course(String id, String name, int sem) {
	this.id = id;
	this.name = name;
	this.sem = sem;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getSem() {
	return sem;
    }

    public void setSem(int sem) {
	this.sem = sem;
    }
}



public class StudentRoster {

    public static List<Student> studentRoster = new ArrayList<>();
    public static List<Student> enRolledStudent = new ArrayList<>();
    public static List<Course> courseList = new ArrayList<>();

    public static void main(String[] args) {
	courseList.add(new Course("210", "Mechanical", 1));
	courseList.add(new Course("211", "Civil", 3));
	courseList.add(new Course("213", "Electronics", 1));
	courseList.add(new Course("312", "Electrical", 3));
	courseList.add(new Course("215", "Computer Science", 3));

	Scanner sc = new Scanner(System.in);
	int digit;

	do {
	    displayMenu();
	    digit = sc.nextInt(); 
	    switch (digit) {
	    case 1:
		addStudent();
		break;
	    case 2:
		enrollInCourse();
		break;
	    case 3:
		displayEnrolledCourses();
		break;
	    case 4:
		displayStudents();
		break;
	    case 5:
		removeStudent();
		break;
	    case 6:
		System.out.println("Exiting the program.");
		break;
	    default:
		System.out.println("Invalid input. Please try again.");
	    }
	} while (digit != 6);
    }

    private static void displayMenu() {
	System.out.println("Student Roster Management Program");
	System.out.println("1. Add a new student");
	System.out.println("2. Enroll a student in a course");
	System.out.println("3. Display the list of courses a student is enrolled in");
	System.out.println("4. Display the list of all students along with their enrolled courses");
	System.out.println("5. Remove a student from the roster");
	System.out.println("6. Exit the program");
	System.out.print("Enter a number from above");
    }

    private static void addStudent() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter student name: ");
	String name = sc.nextLine();

	String studentId;
	Student existingStudent;
	do {
	    System.out.print("Enter student ID: ");
	    studentId = sc.next();
	    existingStudent = findStudentById(studentId);
	    if (existingStudent != null) {
		System.out.println("Student with this ID already exists. Please enter a unique ID.");
	    }
	} while (existingStudent != null);

	studentRoster.add(new Student(name, studentId));
	System.out.println("Student added to the roster.");
    }

    private static void enrollInCourse() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter student ID: ");
	String studentId = sc.next();
	Student student = findStudentById(studentId);

	if (student == null) {
	    System.out.println("There is no student in the roster with this ID.");
	    return;
	}
	System.out.println(" Course Id: 210 Course: Mechanical and Course Sem: 1");
	System.out.println(" Course Id: 211 Course: Civil and Course Sem: 3");
	System.out.println(" Course Id: 213 Course: Electronics and Course Sem: 1");
	System.out.println(" Course Id: 312 Course: Electrical and Course Sem: 3");
	System.out.println(" Course Id: 215 Course: Computer Science and Course Sem: 3");
	System.out.println();

	String courseId;
	Course existingCourse;
	System.out.print("Choose and enter Course ID from");
	do {
	    courseId = sc.next();
	    existingCourse = findCourseById(courseId);
	    if (existingCourse == null) {
		System.out.println("No course exists with this ID, please eneter a valid ID");
	    } else {
		student.getCourses().add(existingCourse);
		enRolledStudent.add(findStudentById(studentId));
		System.out.println("Student enrolled in the course.");
	    }
	} while (existingCourse == null);
	Course course = findCourseById(courseId);

    }

    private static void displayEnrolledCourses() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter student ID: ");
	String studentId = sc.next();
	Student student = findStudentById(studentId);

	if (student == null) {
	    System.out.println("Student not found in the roster.");
	    return;
	}

	System.out.println("Courses enrolled by the student:");
	for (Course course : student.getCourses()) {
	    System.out.println("Course ID: " + course.getId() + ", Course Name: " + course.getName() + ", Semester: "
		    + course.getSem());
	}
    }

    private static void displayStudents() {
	System.out.println("List of all students along with their enrolled courses:");
	for (Student student : enRolledStudent) {
	    System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName());

	    for (Course course : student.getCourses()) {
		System.out.println("    Course ID: " + course.getId() + ", Course Name: " + course.getName()
			+ ", Semester: " + course.getSem());
	    }
	}
    }

    private static void removeStudent() {
	Scanner sc = new Scanner(System.in);
	System.out.print("Enter student ID: ");
	String studentId = sc.next();

	for (int i = 0; i < studentRoster.size(); i++) {
	    Student student = studentRoster.get(i);
	    if (student.getId().equals(studentId)) {
		studentRoster.remove(i);
		System.out.println("Student removed from the roster.");
		return;
	    }
	}

	System.out.println("Student not found in the roster.");
    }

    private static Student findStudentById(String studentId) {
	for (Student student : studentRoster) {
	    if (student.getId().equals(studentId)) {
		return student;
	    }
	}
	return null;
    }

    private static Course findCourseById(String courseId) {
	for (Course course : courseList) {
	    if (course.getId().equals(courseId)) {
		return course;
	    }
	}
	return null;
    }
}