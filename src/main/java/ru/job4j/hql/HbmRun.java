package ru.job4j.hql;

import org.hibernate.Session;
import  org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hiber.model.MarkAvto;
import ru.job4j.hiber.model.ModelAvto;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

/* Candidate candidateOne = Candidate.of("Lena", 2, 1000);
Candidate candidateTwo = Candidate.of("Kolya", 5, 2000);
Candidate candidateThree = Candidate.of("Gena", 3, 1500);

session.save(candidateOne);
session.save(candidateTwo);
session.save(candidateThree); */

            Query query = session.createQuery("from Candidate ");
            for (Object st : query.getResultList()) {
                System.out.println(st);
            }

            query = session.createQuery("from Candidate s where s.id = :fId");
            query.setParameter("fId", 1);
            System.out.println(query.uniqueResult());

            query = session.createQuery("from Candidate s where s.name = :fName");
            query.setParameter("fName", "Lena");
            System.out.println(query.uniqueResult());

            session.createQuery("insert into Candidate (name, experience, salary) "
            + "select concat(c.name, '!'), c.experience + 1, c.salary +  200 "
                    + "from Candidate c where c.id = :fId").setParameter("fId", 2).executeUpdate();

              session.createQuery("delete from Candidate where salary > :fSal")
             .setParameter("fSal", 1900).executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
