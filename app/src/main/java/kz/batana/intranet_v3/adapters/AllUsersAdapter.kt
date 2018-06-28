package kz.batana.intranet_v3.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_all_users_adapter_admins.view.*
import kotlinx.android.synthetic.main.item_all_users_adapter_header.view.*
import kotlinx.android.synthetic.main.item_all_users_adapter_students.view.*
import kotlinx.android.synthetic.main.item_all_users_adapter_teachers.view.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.activities.SplashActivity.Companion.asd
import kz.batana.intranet_v3.database.AdminEntity
import kz.batana.intranet_v3.database.StudentEntity
import kz.batana.intranet_v3.database.TeacherEntity

class AllUsersAdapter(private val dataset: ArrayList<Any>,
                      private val listener: OnItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemViewType(position: Int): Int = when(dataset[position]){
        is StudentEntity -> HolderTypes.STUDENTS
        is TeacherEntity -> HolderTypes.TEACHERS
        is AdminEntity -> HolderTypes.ADMINS
        else -> {
            HolderTypes.HEADER
        }
    }

    object HolderTypes{
        const val STUDENTS = 0
        const val TEACHERS = 1
        const val ADMINS = 2
        const val HEADER = 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = run {
        Log.d(asd,"AllUsersAdapter Opened")
        when(viewType){
            HolderTypes.STUDENTS -> StudentListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_students, parent, false))

            HolderTypes.TEACHERS -> TeacherListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_teachers, parent, false))

            HolderTypes.ADMINS -> AdminListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_admins, parent, false))

            else->{
                HeaderViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_all_users_adapter_header, parent, false))
            }

        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is StudentListViewHolder->{
                val obj = dataset[position] as StudentEntity
                Log.d(asd, "==> $obj")
                holder.itemView.studentFirstNameCardViewTextView.text = obj.firstname
                holder.itemView.studentLastNameCardViewTextView.text = obj.lastname
                holder.itemView.setOnClickListener{
                    listener.onStudentClicked(obj)
                }
            }
            is TeacherListViewHolder->{
                val obj = dataset[position] as TeacherEntity
                Log.d(asd, "==> $obj")
                holder.itemView.teacherFirstNameCardViewTextView.text = obj.firstname
                holder.itemView.teacherLastNameCardViewTextView.text = obj.lastname
                holder.itemView.setOnClickListener{
                    listener.onTeacherClicked(obj)
                }
            }
            is AdminListViewHolder->{
                val obj = dataset[position] as AdminEntity
                Log.d(asd, "==> $obj")
                holder.itemView.adminFirstNameCardViewTextView.text = obj.firstname
                holder.itemView.adminLastNameCardViewTextView.text = obj.lastname
                holder.itemView.setOnClickListener{
                    listener.onAdminClicked(obj)
                }
            }
            is HeaderViewHolder->{
                val obj = dataset[position] as String
                Log.d(asd, "==> $obj")
                holder.itemView.headerCardViewTextView.text = obj
                holder.itemView.setOnClickListener{
                    listener.onHeaderClicked(obj)
                }
            }
        }
    }

    inner class StudentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class TeacherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class AdminListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    interface OnItemClickListener{
        fun onHeaderClicked(h: String)
        fun onStudentClicked(s: StudentEntity)
        fun onTeacherClicked(t: TeacherEntity)
        fun onAdminClicked(a: AdminEntity)
    }
}




const val HEADER = "HEADER"
const val STUDENTS = "STUDENTS"
const val TEACHERS =  "TEACHERS"
const val ADMINS = "ADMINS"