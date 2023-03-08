package prime.projects.mysafefly.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel

class MySafeFlyViewModel: ViewModel() {
    fun makingLongToastMessages(context: Context, messages: String) {
        Toast.makeText(context, messages, Toast.LENGTH_LONG).show()
    }
    fun makingShortToastMessages(context: Context, messages: String){
        Toast.makeText(context, messages, Toast.LENGTH_SHORT).show()
    }
    }