package com.practice.hibernate.demo;

import com.practice.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

        try (sessionFactory) {
            Session currentSession = sessionFactory.getCurrentSession();

            currentSession.beginTransaction();
            List<Student> studentList = currentSession.createQuery("from Student").getResultList();
            displayStudent(studentList);
            System.out.println("\n\nStudents who have last name of Tyson");
            studentList =
                    currentSession.createQuery("from Student s where s.lastName='Tyson'").getResultList();
            displayStudent(studentList);

            System.out.println("\n\nStudents who have first name of John or last name of Tyson");
            studentList =
                    currentSession
                            .createQuery("from Student s where s.firstName = 'John' OR s.lastName = 'Tyson'")
                            .getResultList();
            displayStudent(studentList);

            System.out.println("\n\nStudents who have email address ends with email.com domain");
            studentList =
                    currentSession
                            .createQuery("from Student s where s.email like '%email.com'")
                            .getResultList();
            displayStudent(studentList);
            currentSession.getTransaction().commit();
            System.out.println("Done");
        }
    }

    private static void displayStudent(List<Student> studentList) {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
