package ru.job4j.hiber.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hiber.model.Author;
import ru.job4j.hiber.model.Book;

public class HbmBookRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Book one = Book.of("English");
            Book two = Book.of("French");
            Book three = Book.of("Italian");
            Book four = Book.of("Spanish");

            Author first = Author.of("Ivanov");
            first.addBokk(one);
            first.addBokk(two);
            first.addBokk(three);

            Author second = Author.of("Petrov");
            second.addBokk(three);
            second.addBokk(four);

            session.persist(first);
            session.persist(second);

            session.getTransaction().commit();
            session.close();

            session = sf.openSession();
            session.beginTransaction();

            Author author = session.get(Author.class, 1);
            session.remove(author);

            session.getTransaction().commit();
            session.close();

        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
