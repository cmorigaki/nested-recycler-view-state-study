package br.com.nestedrecyclerviewstate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.nestedrecyclerviewstate.databinding.ExampleActivityBinding

class ExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ExampleActivityBinding.inflate(layoutInflater).apply {
            setContentView(root)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance()).commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, getString(R.string.back_pressed_message), Toast.LENGTH_SHORT).show()
    }
}