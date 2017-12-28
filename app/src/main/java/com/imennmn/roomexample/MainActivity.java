package com.imennmn.roomexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.imennmn.roomexample.db.AppDatabase;
import com.imennmn.roomexample.model.Book;
import com.imennmn.roomexample.utils.DatabaseInitializer;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppDatabase mDb;

    private TextView textView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text) ;
        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        populateDb();

        fetchData();
    }

    private void populateDb() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        // Note: this kind of logic should not be in an activity.
        StringBuilder sb = new StringBuilder();
//        List<User> youngUsers = mDb.userModel().findUsersYoungerThan(35);

        List<Book> contents = mDb.bookModel().findAllBooks();
//        for (User youngUser : youngUsers) {
//            sb.append(String.format(Locale.US,
//                    "%s, %s (%d)\n", youngUser.lastName, youngUser.name, youngUser.age));
//        }

        for (Book loanWithUserAndBook : contents){
            sb.append(String.format(Locale.US,
                    "%s, %s \n", loanWithUserAndBook.id+"", loanWithUserAndBook.title));
        }

        textView.setText(sb+"");
        Log.i("fetchData", " result = "+sb) ;
    }
}
