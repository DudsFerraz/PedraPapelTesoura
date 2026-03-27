package br.edu.ifsp.scl.sc3043959.pedrapapeltesoura

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.edu.ifsp.scl.sc3043959.pedrapapeltesoura.ui.theme.PedraPapelTesouraTheme

class MainActivity : ComponentActivity() {

    private var lifecycleMessage by mutableStateOf("Activity criada")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleMessage = savedInstanceState?.getString(KEY_LIFECYCLE_MESSAGE) ?: "onCreate: Activity criada"

        enableEdgeToEdge()
        setContent {
            PedraPapelTesouraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JoKenPoScreen(
                        lifecycleMessage = lifecycleMessage,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        updateLifecycle("onCreate: componentes iniciais carregados")
    }

    override fun onStart() {
        super.onStart()
        updateLifecycle("onStart: Activity visivel")
    }

    override fun onResume() {
        super.onResume()
        updateLifecycle("onResume: Activity em primeiro plano")
    }

    override fun onPause() {
        super.onPause()
        updateLifecycle("onPause: Activity perdeu foco")
    }

    override fun onStop() {
        super.onStop()
        updateLifecycle("onStop: Activity em segundo plano")
    }

    override fun onRestart() {
        super.onRestart()
        updateLifecycle("onRestart: Activity retornando")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_LIFECYCLE_MESSAGE, lifecycleMessage)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lifecycleMessage = savedInstanceState.getString(KEY_LIFECYCLE_MESSAGE) ?: lifecycleMessage
        updateLifecycle("onRestoreInstanceState: estado restaurado")
    }

    override fun onDestroy() {
        updateLifecycle("onDestroy: Activity sera removida")
        super.onDestroy()
    }

    private fun updateLifecycle(message: String) {
        lifecycleMessage = message
        Log.d(TAG, message)
    }

    companion object {
        private const val TAG = "CicloDeVida"
        private const val KEY_LIFECYCLE_MESSAGE = "key_lifecycle_message"
    }
}
