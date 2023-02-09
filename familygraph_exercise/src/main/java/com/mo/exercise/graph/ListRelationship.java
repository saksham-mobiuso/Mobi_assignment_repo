package com.mo.exercise.graph;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.mo.exercise.graph.DAO.List_persons_DAO;


public class ListRelationship implements List_persons_DAO{

	public ArrayList<String> allRelations() throws FileNotFoundException {
		
		ArrayList<String> listOfAllRelations = new ArrayList<>();

		ListRelationship list_of_relation= new ListRelationship();

		List_persons_DAO list_of_person= new ListPerson();

		int no_of_relations=list_of_relation.populateListOfRelationships().size();

		String person_name="";
		String relative_name="";

		for(int i=0;i<no_of_relations;i++) {

			person_name=list_of_relation.populateListOfRelationships().get(i).getRelated_person();
			relative_name=list_of_relation.populateListOfRelationships().get(i).getRelative();

			if(list_of_person.listPersons().containsKey(person_name)) {
				person_name = list_of_person.listPersons().get(person_name);
			}

			if(list_of_person.listPersons().containsKey(relative_name)) {
				relative_name = list_of_person.listPersons().get(relative_name);
			}

			listOfAllRelations.add(person_name+" and "+relative_name +" are "+ list_of_relation.populateListOfRelationships().get(i).getRelation());

		}
		return listOfAllRelations;

	}



	public ArrayList<Relationship> populateListOfRelationships() throws FileNotFoundException{
		
		ArrayList<Relationship> relation = new ArrayList<>();

		Scanner scan = new Scanner(new File("/Users/sakshamsharma/Downloads/familygraph_exercise/src/test/resources/relationships.csv"));


		while(scan.hasNext()) {
			String[] s = scan.nextLine().split(",");

			Relationship r =new Relationship();
			r.setRelated_person(s[0]);
			r.setRelation(s[1]);
			r.setRelative(s[2]);
			relation.add(r);
		}
		scan.close();


		return relation;
	}




	public int findtotalRelationsById(String id) throws FileNotFoundException {

		int count=0;
		ListRelationship list_of_relation=new ListRelationship();


		int size_of_relation_array=list_of_relation.populateListOfRelationships().size();


		for(int rp=0; rp<size_of_relation_array ;rp++) {

			if(id.equals(list_of_relation.populateListOfRelationships().get(rp).getRelated_person()) || id.equals(list_of_relation.populateListOfRelationships().get(rp).getRelative())){
				count++;
			}
		}
		return count;	

	}


	public List<String> extended_family_Recursive_function(Object object, List<String> relatives_of_perticular_person) throws FileNotFoundException {

		ListRelationship listOfRelation=new ListRelationship();

		int sizeOfRelationArray=listOfRelation.populateListOfRelationships().size();


		for(int rp=0; rp<sizeOfRelationArray ;rp++) {

			if(object.equals(listOfRelation.populateListOfRelationships().get(rp).getRelated_person())
					&& listOfRelation.populateListOfRelationships().get(rp).getRelation().equals("FAMILY") && !relatives_of_perticular_person.contains(listOfRelation.populateListOfRelationships().get(rp).getRelative())){
				relatives_of_perticular_person.add(listOfRelation.populateListOfRelationships().get(rp).getRelative());
			}

			if(object.equals(listOfRelation.populateListOfRelationships().get(rp).getRelative())
					&& listOfRelation.populateListOfRelationships().get(rp).getRelation().equals("FAMILY") && !relatives_of_perticular_person.contains(listOfRelation.populateListOfRelationships().get(rp).getRelated_person())) {
				relatives_of_perticular_person.add(listOfRelation.populateListOfRelationships().get(rp).getRelated_person());
			}

		}

		return relatives_of_perticular_person;	
	}

	public int calculateExtendedFamily(String id) throws FileNotFoundException {
		
		List<String> relatives_of_persticular_person = new ArrayList<String>();
	
		int count=0;
		if(relatives_of_persticular_person.isEmpty()) {
			count=extended_family_Recursive_function(id, relatives_of_persticular_person).size();
		}
		for(int i=0;i<relatives_of_persticular_person.size();i++) {
			count=extended_family_Recursive_function(relatives_of_persticular_person.toArray()[i], relatives_of_persticular_person).size();
		}

		System.out.println(relatives_of_persticular_person);
		return count;

	}

	@Override
	public HashMap<String, String> listPersons() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


}
