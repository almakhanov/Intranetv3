package kz.batana.intranet_v3.ui.admin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.item_all_users_adapter_admins.view.*
import kotlinx.android.synthetic.main.item_all_users_adapter_header.view.*
import kotlinx.android.synthetic.main.item_all_users_adapter_students.view.*
import kotlinx.android.synthetic.main.item_all_users_adapter_teachers.view.*
import kz.batana.intranet_v3.R
import kz.batana.intranet_v3.data.localDB.database.admin_room.AdminEntity
import kz.batana.intranet_v3.data.localDB.database.student_room.StudentEntity
import kz.batana.intranet_v3.data.localDB.database.teacher_room.TeacherEntity

class AllUsersAdapter(private var dataset: ArrayList<Any>,
                      private val listener: OnItemClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var usersQuery = UsersQueryFilter(dataset)

    fun filter(query: String){
        dataset = usersQuery.findByFilter(query)
        notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int = when(dataset[position]){
        is StudentEntity -> HolderTypes.STUDENTS
        is TeacherEntity -> HolderTypes.TEACHERS
        is AdminEntity -> HolderTypes.ADMINS
        is Button -> HolderTypes.BUTTON
        else -> {
            HolderTypes.HEADER
        }
    }

    object HolderTypes{
        const val STUDENTS = 0
        const val TEACHERS = 1
        const val ADMINS = 2
        const val HEADER = 3
        const val BUTTON = 4
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = run {
        //Log.d(TAG,"AllUsersAdapter Opened")
        when(viewType){
            HolderTypes.STUDENTS -> StudentListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_students, parent, false))

            HolderTypes.TEACHERS -> TeacherListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_teachers, parent, false))

            HolderTypes.ADMINS -> AdminListViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_admins, parent, false))

            HolderTypes.BUTTON -> ButtonViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_all_users_adapter_add_btn, parent, false))

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
            is StudentListViewHolder ->{
                val obj = dataset[position] as StudentEntity
                //Log.d(TAG, "==> $obj")
                holder.itemView.studentFirstNameCardViewTextView.text = obj.firstname
                holder.itemView.studentLastNameCardViewTextView.text = obj.lastname
                holder.itemView.setOnClickListener{
                    listener.onStudentClicked(obj)
                }
            }
            is TeacherListViewHolder ->{
                val obj = dataset[position] as TeacherEntity
                //Log.d(TAG, "==> $obj")
                holder.itemView.teacherFirstNameCardViewTextView.text = obj.firstname
                holder.itemView.teacherLastNameCardViewTextView.text = obj.lastname
                holder.itemView.setOnClickListener{
                    listener.onTeacherClicked(obj)
                }
            }
            is AdminListViewHolder ->{
                val obj = dataset[position] as AdminEntity
                //Log.d(TAG, "==> $obj")
                holder.itemView.adminFirstNameCardViewTextView.text = obj.firstname
                holder.itemView.adminLastNameCardViewTextView.text = obj.lastname
                holder.itemView.setOnClickListener{
                    listener.onAdminClicked(obj)
                }
            }
            is HeaderViewHolder ->{
                val obj = dataset[position] as String
                //Log.d(TAG, "==> $obj")
                holder.itemView.headerCardViewTextView.text = obj
                holder.itemView.setOnClickListener{
                    listener.onHeaderClicked(obj)
                }
            }
            is ButtonViewHolder ->{
                val obj = dataset[position] as Button
                holder.itemView.setOnClickListener{
                    listener.onAddBtnClicked(obj.hint.toString())
                }
            }
        }
    }

    inner class StudentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class TeacherListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class AdminListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    interface OnItemClickListener{
        fun onHeaderClicked(h: String)
        fun onStudentClicked(s: StudentEntity)
        fun onTeacherClicked(t: TeacherEntity)
        fun onAdminClicked(a: AdminEntity)
        fun onAddBtnClicked(b: String)
    }


}

