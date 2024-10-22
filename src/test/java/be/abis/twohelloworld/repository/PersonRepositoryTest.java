package be.abis.twohelloworld.repository;

import be.abis.twohelloworld.repository.FilePersonRepository;
import be.abis.twohelloworld.repository.PersonRepository;

public class PersonRepositoryTest {

	public static void main(String[] args) {
		PersonRepository pr=new FilePersonRepository();;

		System.out.println("-- all persons --");
		pr.getAllPersons().forEach(System.out::println);

		System.out.println("-- person by id --");
		System.out.println(pr.findPerson(1));

		System.out.println("-- person by mail and pwd --");
		System.out.println(pr.findPerson("mjones@abis.be","abc123"));

	}

}
