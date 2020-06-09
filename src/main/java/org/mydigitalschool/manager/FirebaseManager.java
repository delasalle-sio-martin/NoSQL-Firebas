package org.mydigitalschool.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.mydigitalschool.user.User;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseManager {
	
	public FirebaseManager() {
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("firemessenger.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

  			FirebaseOptions options = null;
			try {
				options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://firemessenger-1b6d8.firebaseio.com")
				  .build();
			} catch (IOException e) {
				e.printStackTrace();
			}

  			FirebaseApp.initializeApp(options);
	}
	public DatabaseReference getDatabase() {
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference("");
		return ref;
	}
	
	public String createUser(User user) {
		DatabaseReference usersRef = this.getDatabase().child("currentUser");
		DatabaseReference pushedUserRef = usersRef.push();
		pushedUserRef.setValueAsync(user);
		return pushedUserRef.getKey();
	}
	
	public List<User> getUsers() {
		
	}
	
	
}
