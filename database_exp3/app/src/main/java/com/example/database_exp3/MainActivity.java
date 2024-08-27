package com.example.database_exp3;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.database_exp3.R;


public class MainActivity extends AppCompatActivity {

    private com.example.database_exp3.DatabaseHelper dbHelper;
    private EditText name, rollNo, marks, age, registerNo, address, searchRegisterNo;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new com.example.database_exp3.DatabaseHelper(this);
        name = findViewById(R.id.name);
        rollNo = findViewById(R.id.roll_no);
        marks = findViewById(R.id.marks);
        age = findViewById(R.id.age);
        registerNo = findViewById(R.id.register_no);
        address = findViewById(R.id.address);
        searchRegisterNo = findViewById(R.id.search_register_no);
        tableLayout = findViewById(R.id.tableLayout);

        Button insertButton = findViewById(R.id.insert_button);
        Button updateButton = findViewById(R.id.update_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button viewButton = findViewById(R.id.view_button);
        Button searchButton = findViewById(R.id.search_button);

        insertButton.setOnClickListener(v -> {
            dbHelper.insertStudent(name.getText().toString(), Integer.parseInt(marks.getText().toString()),
                    Integer.parseInt(age.getText().toString()), registerNo.getText().toString(), address.getText().toString());
            Toast.makeText(MainActivity.this, "Inserted!", Toast.LENGTH_SHORT).show();
            clearInputFields();
        });

        updateButton.setOnClickListener(v -> {
            dbHelper.updateStudent(Integer.parseInt(rollNo.getText().toString()), name.getText().toString(),
                    Integer.parseInt(marks.getText().toString()), Integer.parseInt(age.getText().toString()),
                    registerNo.getText().toString(), address.getText().toString());
            Toast.makeText(MainActivity.this, "Updated!", Toast.LENGTH_SHORT).show();
            clearInputFields();
        });

        deleteButton.setOnClickListener(v -> {
            dbHelper.deleteStudent(Integer.parseInt(rollNo.getText().toString()));
            Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
            clearInputFields();
        });

        viewButton.setOnClickListener(v -> displayStudentData());

        searchButton.setOnClickListener(v -> displayStudentDataByRegisterNo(searchRegisterNo.getText().toString()));
    }

    private void clearInputFields() {
        name.setText("");
        rollNo.setText("");
        marks.setText("");
        age.setText("");
        registerNo.setText("");
        address.setText("");
        searchRegisterNo.setText("");
    }

    private void displayStudentData() {
        tableLayout.removeAllViews();

        // Add table header
        TableRow header = new TableRow(this);
        header.addView(createTextView("Roll No", true));
        header.addView(createTextView("Name", true));
        header.addView(createTextView("Marks", true));
        header.addView(createTextView("Age", true));
        header.addView(createTextView("Register No", true));
        header.addView(createTextView("Address", true));
        tableLayout.addView(header);

        Cursor cursor = dbHelper.getStudentDetails();
        if (cursor.getCount() == 0) {
            TableRow noDataRow = new TableRow(this);
            TextView noDataText = createTextView("No Records Found", false);
            noDataText.setTop(3);
            noDataRow.addView(noDataText);
            tableLayout.addView(noDataRow);
            return;
        }

        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);
            row.addView(createTextView(String.valueOf(cursor.getInt(0)), false));
            row.addView(createTextView(cursor.getString(1), false));
            row.addView(createTextView(String.valueOf(cursor.getInt(2)), false));
            row.addView(createTextView(String.valueOf(cursor.getInt(3)), false));
            row.addView(createTextView(cursor.getString(4), false));
            row.addView(createTextView(cursor.getString(5), false));
            tableLayout.addView(row);
        }
    }

    private void displayStudentDataByRegisterNo(String registerNo) {
        tableLayout.removeAllViews();

        // Add table header
        TableRow header = new TableRow(this);
        header.addView(createTextView("Roll No", true));
        header.addView(createTextView("Name", true));
        header.addView(createTextView("Marks", true));
        header.addView(createTextView("Age", true));
        header.addView(createTextView("Register No", true));
        header.addView(createTextView("Address", true));
        tableLayout.addView(header);

        Cursor cursor = dbHelper.getStudentByRegisterNo(registerNo);
        if (cursor.getCount() == 0) {
            TableRow noDataRow = new TableRow(this);
            TextView noDataText = createTextView("No Records Found", false);
            noDataText.setTop(3);
            noDataRow.addView(noDataText);
            tableLayout.addView(noDataRow);
            return;
        }

        while (cursor.moveToNext()) {
            TableRow row = new TableRow(this);
            row.addView(createTextView(String.valueOf(cursor.getInt(0)), false));
            row.addView(createTextView(cursor.getString(1), false));
            row.addView(createTextView(String.valueOf(cursor.getInt(2)), false));
            row.addView(createTextView(String.valueOf(cursor.getInt(3)), false));
            row.addView(createTextView(cursor.getString(4), false));
            row.addView(createTextView(cursor.getString(5), false));
            tableLayout.addView(row);
        }
    }

    private TextView createTextView(String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(8, 8, 8, 8);
        if (isHeader) {
            textView.setTextAppearance(android.R.style.TextAppearance_Material_Small);
            textView.setTypeface(null, android.graphics.Typeface.BOLD);
        }
        return textView;
    }
}
