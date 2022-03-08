package ru.job4j.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Orders.of("name1", "description1"));

        List<Orders> all = (List<Orders>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenUpdateOrder() {
        OrdersStore store = new OrdersStore(pool);
        Orders orders = new Orders(1, "First Order", "",
                new Timestamp(System.currentTimeMillis()));
        Orders ordersNew = new Orders(1, "Second Order", "",
                new Timestamp(System.currentTimeMillis()));
        store.save(orders);
        store.update(ordersNew);
        Orders storeById = store.findById(ordersNew.getId());
        assertThat(storeById.getName(), is(ordersNew.getName()));
    }

    @Test
    public void whenFindOrderByName() {
        OrdersStore store = new OrdersStore(pool);
        Orders orders = new Orders(1, "First Order", "something",
                new Timestamp(System.currentTimeMillis()));
        store.save(orders);
        Orders storeById = store.findOrderByName(orders.getName());
        assertThat(storeById.getDescription(), is(orders.getDescription()));
    }

    @After
    public void delTable() throws SQLException {
        pool.getConnection().prepareStatement("DROP TABLE orders").executeUpdate();
    }
}