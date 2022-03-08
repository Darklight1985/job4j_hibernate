package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HbmRunLazy {
    public static void main(String[] args) {
        Candidate rsl = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
//            Candidate candidateOne = Candidate.of("Lena", 2, 1000);
//            BaseVac baseVac = BaseVac.of("База вакансий");
//            Vacancy vacancyOne = Vacancy.of("Строитель");
//            Vacancy vacancyTwo = Vacancy.of("Программист");
//            baseVac.addVac(vacancyOne);
//            baseVac.addVac(vacancyTwo);
//
//            session.save(baseVac);
//
//            candidateOne.setBaseVac(baseVac);
//
//            session.save(candidateOne);
            rsl = session.createQuery(
                    "select distinct c from Candidate c "
                            + "join fetch c.baseVac b "
                            + "join fetch b.vacancy v "
                            + "where c.id = :cId", Candidate.class
            ).setParameter("cId", 1).uniqueResult();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        System.out.println(rsl);
    }
}
