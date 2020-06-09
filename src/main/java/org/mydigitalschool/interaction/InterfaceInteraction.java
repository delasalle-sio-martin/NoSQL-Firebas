package org.mydigitalschool.interaction;

import java.util.Scanner;

import org.mydigitalschool.manager.FirebaseManager;
import org.mydigitalschool.user.User;

public class InterfaceInteraction {
	
	private User currentUser;
	private Scanner sc;
	private FirebaseManager dbManager;
	
	public InterfaceInteraction(FirebaseManager dbManager) {
		this.sc = new Scanner(System.in);
		this.dbManager = dbManager;
	}
	
	public void execute() {
		while(true) {
			readUserLine();
		}
	}
	
	public void readUserLine() {
		String insertLine = this.sc.nextLine();
		
		try {
			switch(insertLine.split(" ")[0]) {
				case "/log":
					this.connection(insertLine);
					break;
				case "/show_users":
					this.showUsers();
					break;
				default:
					this.help();
					break;
			}
		} catch(IllegalArgumentException e) {
			this.help();
		}
	}
	
	private void connection(String insertLine) {
		String[] args = insertLine.split(" ");
		if(args.length < 2) {
			throw new IllegalArgumentException();
		}
		
		if(this.currentUser != null) {
			System.out.println("Vous êtes déjà connecté");
			return;
		}
		
		String name = args[1];
		this.currentUser = new User(name);
		String key = this.dbManager.createUser(this.currentUser);
		this.currentUser.setKey(key);
		
		System.out.println("Vous êtes désormais connecté sous le nom : " + this.currentUser);
	}
	private void showUsers() {
		for(User user : dbManager.getDatabase().get)
	}
	private void help() {
		System.out.println("Liste des commandes que vous pouvez taper");
		System.out.println(" /log [name] ==> permet de se connecter avec le pseudo [name] (à remplacer par le pseudo souhaité)");
		System.out.println(" /show_users ==> permet de voir la liste de tous les utilisateurs");
	}
}
