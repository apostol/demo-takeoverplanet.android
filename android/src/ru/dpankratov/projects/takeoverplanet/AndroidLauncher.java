package ru.dpankratov.projects.takeoverplanet;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.ComponentActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AndroidLauncher extends ComponentActivity {
	static public FirebaseAuth AuthService ;
	private boolean quit = false;
	private boolean gameIsStarted = false;
	private boolean authIsStarted = false;
	private Thread _checkAuth = new CheckAuth();

	public static FirebaseUser getUser() {
		return AuthService.getCurrentUser();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FirebaseApp.initializeApp(this);
		AuthService = FirebaseAuth.getInstance();
		//AuthService.signOut();
		_checkAuth.start();
	}

	class CheckAuth extends Thread {
		@Override
		public void run() {
			while(!quit){
				try {
					if (!gameIsStarted) {
						FirebaseUser _user = getUser();
						if (_user != null && !_user.isAnonymous()) {
							gameIsStarted = true;
							authIsStarted = false;
							startActivity(new Intent(getApplicationContext(), GameLauncher.class));
						} else if (!authIsStarted){
							authIsStarted = true;
							startActivity(new Intent(getApplicationContext(), SignInActivity.class));
						}
						Thread.sleep(1000);
					} else{
						Thread.sleep(10000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
