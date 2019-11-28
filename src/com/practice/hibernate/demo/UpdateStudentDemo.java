package com.practice.hibernate.demo;

import com.practice.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        SessionFactory sessionFactory =
                new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(Student.class)
                        .buildSessionFactory();

        try (sessionFactory) {
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            int studentId = 8;

            Student student = currentSession.get(Student.class, studentId);
            student.setLastName("Mike");
            //currentSession.save(student);
            System.out.println("Student with studentId=1" + student);
            currentSession.getTransaction().commit();
        }
    }
}
