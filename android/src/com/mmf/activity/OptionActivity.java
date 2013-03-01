package com.mmf.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.mmf.R;
import com.mmf.prefs.CredentialsPrefs;
import com.mmf.service.BusinessLayerException;
import com.mmf.util.Logger;

/**
 * @author svetlana.voyteh
 * @date: 2/3/12
 */
public class OptionActivity extends Activity {
    
    private Spinner courseSpinner;
    private Spinner groupSpinner;
    private Spinner subgroupSpinner;
    private Spinner lecturerSpinner;
    private Spinner departmentSpinner;
    private TabHost tabHost;

    private ArrayAdapter<CharSequence> courseAdapter;
    private ArrayAdapter<CharSequence> groupAdapter;
    private ArrayAdapter<CharSequence> subgroupAdapter;
    private ArrayAdapter<CharSequence> lecturerAdapter;
    private ArrayAdapter<CharSequence> departmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.option);

        try {
            initSpinners();

            Button button = (Button) findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CredentialsPrefs.Course.put(courseSpinner.getSelectedItem().toString());
                    CredentialsPrefs.Group.put(groupSpinner.getSelectedItem().toString());
                    CredentialsPrefs.Subgroup.put(subgroupSpinner.getSelectedItem().toString());
                    startActivity(new Intent(OptionActivity.this, LessonActivity.class));
                }
            });


            TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
            tabHost.setup();

            TabHost.TabSpec studentSpec = tabHost.newTabSpec("Student");
            studentSpec.setContent(R.id.studentTab);
            studentSpec.setIndicator("Student");

            TabHost.TabSpec lecturerSpec = tabHost.newTabSpec("Lecturer");
            lecturerSpec.setIndicator("Lecturer");
            lecturerSpec.setContent(R.id.lecturerTab);

            tabHost.addTab(studentSpec);
            tabHost.addTab(lecturerSpec);

        } catch (BusinessLayerException ble) {
            Logger.getInstance().error(ble);
        }
    }

    private void initSpinners() throws BusinessLayerException {

        courseSpinner = (Spinner) findViewById(R.id.course_spinner);
        courseAdapter = ArrayAdapter.createFromResource(this, R.array.course_array, android.R.layout.simple_spinner_item);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        groupSpinner = (Spinner) findViewById(R.id.group_spinner);
        groupAdapter = ArrayAdapter.createFromResource(this, R.array.group_array, android.R.layout.simple_spinner_item);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groupSpinner.setAdapter(groupAdapter);

        subgroupSpinner = (Spinner) findViewById(R.id.subgroup_spinner);
        subgroupAdapter = ArrayAdapter.createFromResource(this, R.array.subgroup_array, android.R.layout.simple_spinner_item);
        subgroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subgroupSpinner.setAdapter(subgroupAdapter);

        departmentSpinner = (Spinner) findViewById(R.id.department_spinner);
        lecturerSpinner = (Spinner) findViewById(R.id.lecturer_spinner);
    }

}