package com.example.tablesmanagement.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tablesmanagement.R
import com.example.tablesmanagement.ui.theme.PoppinsFontFamily
import com.example.tablesmanagement.viewModel.TablesViewModel


@Composable
fun FindBar(modifier: Modifier = Modifier, viewModel: TablesViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
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

        EnterField(
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery ->
                viewModel.updateSearchQuery(newQuery)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterField(searchQuery: String, onSearchQueryChange: (String) -> Unit) {


    Column {
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                errorBorderColor = Color.Transparent,

                ),
            value = searchQuery,
            onValueChange = { novoTexto -> onSearchQueryChange(novoTexto) },
            placeholder = {
                Text(
                    "Cliente,mesa,comanda e atendente",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
            },
            singleLine = true
        )
    }
}