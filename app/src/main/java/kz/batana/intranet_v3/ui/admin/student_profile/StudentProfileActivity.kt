package kz.batana.intranet_v3.ui.admin.student_profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kz.batana.intranet_v3.R

class StudentProfileActivity : AppCompatActivity(), StudentProfileMVP.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_profile)
    }
}
