package com.mo.exercise.graph.DAO;

import java.io.FileNotFoundException;
import java.util.HashMap;

public interface List_persons_DAO {
	
	 public HashMap<String, String> listPersons() throws FileNotFoundException;
	
}
