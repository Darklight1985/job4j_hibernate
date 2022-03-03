package ru.job4j.hiber;

import org.hibernate.Session;
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

            ModelAvto logan = ModelAvto.of("Logan");
            session.save(logan);
            ModelAvto megan = ModelAvto.of("Megan");
            session.save(logan);
            ModelAvto laguna = ModelAvto.of("Laguna");
            session.save(logan);
            ModelAvto sandero = ModelAvto.of("Sandero");
            session.save(logan);
            ModelAvto duster = ModelAvto.of("Duster");
            session.save(logan);
            session.save(megan);
            session.save(laguna);
            session.save(sandero);
            session.save(duster);

            MarkAvto renau = MarkAvto.of("Renau");
            renau.addModel(session.load(ModelAvto.class, 1));
            renau.addModel(session.load(ModelAvto.class, 2));
            renau.addModel(session.load(ModelAvto.class, 3));
            renau.addModel(session.load(ModelAvto.class, 4));
            renau.addModel(session.load(ModelAvto.class, 5));

            session.save(renau);

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
