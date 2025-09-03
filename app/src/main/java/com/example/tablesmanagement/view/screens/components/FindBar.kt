package com.example.tablesmanagement.view.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R



@Composable
fun FindBar(modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,



    ) {
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Ícone de busca",
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(8.dp))
        EnterField()

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterField() {
    var textoEstado by remember{ mutableStateOf("")}

    Column {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent),
            value = textoEstado,
            onValueChange = {novoTexto -> textoEstado = novoTexto},
            placeholder = {Text("Cliente,mesa,comanda e atendente")},


            )

}}


