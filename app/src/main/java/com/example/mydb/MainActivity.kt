package com.example.mydb
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mydb.ui.theme.MyDBTheme
import java.lang.Exception
class MainActivity : ComponentActivity() {
    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyDBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
        try {
            db = this.openOrCreateDatabase("mydb", MODE_PRIVATE, null)
            db.execSQL(
                "create table tblAMIGO(recID integer PRIMARY KEY autoincrement," +
                        "name text," +
                        "phone text )"
            )
        }catch (e: Exception){
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
        }
        db.execSQL("insert into tblAMIGO(name,phone) values ('James','0922')")
        db.execSQL("insert into tblAMIGO(name,phone) values ('AAA','12345678')")

        var c1=db.rawQuery("select * from tblAMIGO", null)
        val idCol = c1.getColumnIndex("recID")
        val nameCol = c1.getColumnIndex("name")
        val phoneCol = c1.getColumnIndex("phone")
        var totalText =""
        while (c1.moveToNext() )
        {
            val v1=c1.getInt(idCol).toString()
            val v2=c1.getString(nameCol).toString()
            val v3=c1.getString(phoneCol).toString()
            totalText+= v1+v2+v3+"\n"
        }
        totalStr.value= totalText
        Toast.makeText(applicationContext , totalText, Toast.LENGTH_LONG).show()


    }
}
var totalStr = mutableStateOf("")
@Composable
fun Greeting(name: String) {
    Text(text = "Hello ${totalStr.value}!")
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyDBTheme {
        Greeting("Android")
    }
}
