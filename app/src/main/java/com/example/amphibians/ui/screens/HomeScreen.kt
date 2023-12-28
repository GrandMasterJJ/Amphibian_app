package com.example.amphibians.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.network.AmphibianPhoto

@Composable
fun HomeScreen(
    amphibianUiState: AmphibianUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when(amphibianUiState){
        //is AmphibianUiState.Success -> ResultScreen(amphibianUiState.photos, modifier = modifier.fillMaxWidth())
        //is AmphibianUiState.Success -> AmphibiansPhotoCard(photo = amphibianUiState.photos, modifier = modifier)
        is AmphibianUiState.Success -> PhotosGridScreen(photos = amphibianUiState.photos, modifier.fillMaxWidth())
        is AmphibianUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxWidth())
        is AmphibianUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxWidth())
    }
}

/**
 * ResultScreen displaying number of photos retrieved.
 */
@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.size(200.dp),
        text = "Loading ..."
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.size(200.dp),
        text = "Error !!"
    )
    Button(onClick = retryAction){
        Text("Tap to retry")
    }
}

@Composable
fun AmphibiansPhotoCard(photo: AmphibianPhoto, modifier: Modifier = Modifier)
{
    Card(modifier = modifier,
        shape = RoundedCornerShape(8.dp)
    )
    {
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = photo.name, modifier = Modifier
                .fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start)
            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
                contentDescription = "Amphibian photos",
                modifier = modifier.padding(10.dp)
            )

            Text(text = photo.description,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify)
        }
    }

}

@Composable
fun PhotosGridScreen(photos: List<AmphibianPhoto>, modifier: Modifier = Modifier)
{
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(items = photos, key = { photo -> photo.name }) {
            photo -> AmphibiansPhotoCard(
                photo = photo,
                modifier = modifier
                .padding(4.dp)
                .fillMaxWidth()
            )
        }
    }
}


