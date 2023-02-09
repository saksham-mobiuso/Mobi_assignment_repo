package com.mo.exercise.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.mo.exercise.graph.DAO.List_persons_DAO;

public class ListPerson implements List_persons_DAO{

	public HashMap<String, String> listPersons() throws FileNotFoundException{
		
		ArrayList<Person> people = new ArrayList<>();
		HashMap<String, String> map=new HashMap<>();

		Scanner scan = new Scanner(new File("/Users/sakshamsharma/Downloads/familygraph_exercise/src/test/resources/people.csv"));
		
		while(scan.hasNext()) {
			String[] tempStringArray = scan.next().split(",");

			Person person =new Person();
			person.setName(tempStringArray[0]);
			person.setId(tempStringArray[1]);
			person.setAge(tempStringArray[2]);

			map.put(tempStringArray[1], tempStringArray[0]);

			people.add(person);
		}
		scan.close();


		return map;
	}

}
