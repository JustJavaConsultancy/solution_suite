module book.keeping {


/*    requires  jakarta.persistence ;
    requires    org.hibernate.orm.core;
    requires    lombok;
    requires    spring.boot;
    requires    spring.boot.autoconfigure;
    requires    spring.data.jpa;
    requires    spring.data.commons;
    requires    com.fasterxml.jackson.annotation;
    requires    spring.web;
    requires   spring.core;*/
    requires   spring.core;
    requires org.junit.jupiter;
    requires spring.boot.test;
    opens ng.com.justjava.bookkeeping;
}