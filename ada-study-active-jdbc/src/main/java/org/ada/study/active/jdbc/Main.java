package org.ada.study.active.jdbc;

import java.util.List;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
/**
 *  未解决
 *  It is expected that you have a file activejdbc_models.properties on classpath
 *
 */
public class Main {

    private static DB	db;

	public static void main(String[] args) {
    	db = new DB("movies");
    	db.open("com.mysql.jdbc.Driver","jdbc:mysql://localhost/movies","root","cuizhida");

        Person director  = new Person("Stephen Spielberg");
        director.saveIt();

        director.add(new Movie("Saving private Ryan", 1998));
        director.add(new Movie("Jaws", 1982));
        List<Movie> list = director.getAll(Movie.class);
        for(Movie m:list){
        	System.out.println(m.toString());
        }
        Base.close();
    }
}
