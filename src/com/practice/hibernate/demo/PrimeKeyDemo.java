package com.practice.hibernate.demo;

import com.practice.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimeKeyDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

        try (sessionFactory) {
            Session currentSession = sessionFactory.getCurrentSession();

            System.out.println("Creating 3 student object");
            Student student1 = new Student("Paul", "Tyson", "paul@email.com");
            Student student2 = new Student("John", "Doe", "john@email.com");
            Student student3 = new Student("Mike", "Deep", "mike@email.com");

            currentSession.beginTransaction();
            System.out.println("Saving the students");
            currentSession.save(student1);
            currentSession.save(student2);
            currentSession.save(student3);
            currentSession.getTransaction().commit();
        }
    }
}
