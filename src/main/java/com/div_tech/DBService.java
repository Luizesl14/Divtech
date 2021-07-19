package com.div_tech.service;

import ch.qos.logback.core.db.DriverManagerConnectionSource;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DBService {

    public DBService(){
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3303/apitech");
        dataSource.setUsername("root");
        dataSource.setPassword("12345");
        return dataSource;
    }

    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        //mostrar SQL
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);

        // dialeto utilizado
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        adapter.setPrepareConnection(true);
        return adapter;

    }

}
