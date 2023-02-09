package com.mo.exercise.graph;

import java.io.FileNotFoundException;


public class FamilyGraph {

	public static void main(String[] args) throws FileNotFoundException {

		ListPerson listOfPerson =new ListPerson();
		ListRelationship listOfRelation=new ListRelationship();


		//1  All relationships

		for(String allRelationships: listOfRelation.allRelations()) {
			System.out.println(allRelationships);
		}        



		//2  Number of People

		System.out.println((listOfPerson.listPersons()).size()); 



		//3       Total relations by Id

		System.out.println(listOfRelation.findtotalRelationsById("bob@bob.com"));
		System.out.println(listOfRelation.findtotalRelationsById("jenny@toys.com"));
		System.out.println(listOfRelation.findtotalRelationsById("nigel@marketing.com"));
		System.out.println(listOfRelation.findtotalRelationsById("alan@lonely.org"));                



		//4         Extended Family
		System.out.println(listOfRelation.calculateExtendedFamily("nigel@marketing.com"));                

	}

}
