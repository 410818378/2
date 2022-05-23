package com.example.mydb
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
data class UserModel( val name: String , val age: String)

class DBHelper(
    context: Context?,
) : SQLiteOpenHelper(context, "mydb", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        // TODO("Not yet implemented")
        p0?.execSQL(" create table if not exists usermodel(id integer PRIMARY KEY autoincrement, name text, age text)")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //TODO("Not yet implemented")
        p0?.execSQL("drop table usermodel")
        onCreate(p0)
    }
    fun showAllUser(): ArrayList<UserModel>{
        var db = writableDatabase
        var users = ArrayList<UserModel>()
        var c1=db.rawQuery("select * from usermodel",null)
        while(c1.moveToNext()){
            var nameCol =c1.getColumnIndex("name")
            var name=c1.getString(nameCol)
            var ageCol =c1.getColumnIndex("age")
            var age=c1.getString(ageCol)
            var user= UserModel(name,age)
            users.add(user)
        }
        return  users
    }
    fun delUser( name:String){
        var db = writableDatabase
        var selstr = "name LIKE '$name%'"
        db.delete("usermodel",selstr, arrayOf())
    }
    fun insertUser( user: UserModel){
        var db= writableDatabase

        var sqlstr : String ="insert into usermodel(name, age) values ('"+ user.name +"','"+ user.age +"' )"
        db.execSQL(sqlstr)
    }

}

