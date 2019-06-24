package it.polito.tdp.music.model;

import java.time.Month;
import java.time.Year;

public class TestModel {

	public void run() {
		Model model = new Model();
		model.creaGrafo(Month.of(2));
	}
	
	public static void main(String[] args) {
		TestModel main = new TestModel();
		main.run();

	}

}
