package com.practice.hibernate.demo;

import com.practice.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("DuplicatedCode")
public class ReadStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

        try (sessionFactory) {
            Session currentSession = sessionFactory.getCurrentSession();

            System.out.println("Creating new student object");
            Student student = new Student("Paul", "Deep", "paul@email.com");

            currentSession.beginTransaction();
            System.out.println(student.toString());
            currentSession.save(student);
            currentSession.getTransaction().commit();

            currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            System.out.println("Saved Student. Generated Id : " + student.getId());
            Student getStudent = currentSession.get(Student.class, student.getId());
            System.out.println("getStudent " + getStudent);
            System.out.println("Done");
        }
    }
}
