package com.example.multiplicacion_de_matrices

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.multiplicacion_de_matrices.navigation.AppNavigation
import com.example.multiplicacion_de_matrices.ui.screens.EqSys
import com.example.multiplicacion_de_matrices.ui.screens.Inverse
import com.example.multiplicacion_de_matrices.ui.theme.MultiplicacionDeMatricesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        // Mostrar el contenido completo cuando el teclado aparece

        var shouldResize = false // false will resize
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(shouldResize)
            shouldResize = shouldResize.not()
        } else {
            if (shouldResize.not()) {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            } else {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MultiplicacionDeMatricesTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MultiplicacionDeMatricesTheme {
        AppNavigation()
    }
}