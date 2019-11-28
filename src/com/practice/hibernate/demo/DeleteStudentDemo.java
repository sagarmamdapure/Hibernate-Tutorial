package com.practice.hibernate.demo;

import com.practice.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

        try (sessionFactory) {
            int studentId = 9;
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Student student = currentSession.get(Student.class, studentId);
            currentSession.delete(student);
            currentSession.getTransaction().commit();
        }
    }
}
