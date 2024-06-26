package com.johnqualls.android.appsetup.articles.ui

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.johnqualls.android.appsetup.core.ui.Presenter
import com.johnqualls.android.appsetup.core.ui.Screen
import com.johnqualls.android.appsetup.core.ui.theme.AppSetupTheme
import com.johnqualls.android.appsetup.core.ui.theme.mediumSpacing

@Preview(showBackground = true)
@Composable
private fun ArticleScreenLoadingPreview() {
    AppSetupTheme {
        ArticleScreen(ArticlePresenterFake()).Render(state = ArticleUiState(isLoading = true))
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticleScreenContentPreview() {
    AppSetupTheme {
        ArticleScreen(ArticlePresenterFake()).Render(
            state = ArticleUiState(
                isLoading = false,
                articles = listOf(
                    Article(
                        "Everything to Know About Bitcoin Pizza Day",
                        "Vinamrata Chaturvedi, Quartz",
                        "On May 22, 2010, a man in Florida paid 10,000 Bitcoin for pizza.Read more...",
                        "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/98aec6479bad523f5c89763f4acf0cf9.jpg\"",
                    ),
                    Article(
                        "Everything to Know About Bitcoin Pizza Day",
                        "Vinamrata Chaturvedi, Quartz",
                        "On May 22, 2010, a man in Florida paid 10,000 Bitcoin for pizza.Read more...",
                        "https://i.kinja-img.com/image/upload/c_fill,h_675,pg_1,q_80,w_1200/98aec6479bad523f5c89763f4acf0cf9.jpg\"",
                    ),
                ),
            ),
        )
    }
}

class ArticleScreen(override val presenter: Presenter<ArticleUiEvent, ArticleUiState>) :
    Screen<ArticleUiEvent, ArticleUiState> {
    @Composable
    override fun Render(state: ArticleUiState) {
        AnimatedVisibility(
            visible = state.isLoading,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500)),
        ) {
            LoadingIndicator()
        }
        AnimatedVisibility(
            visible = !state.isLoading,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1000)),
        ) {
            Content(state.articles)
        }
    }

    @Composable
    private fun LoadingIndicator() {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    private fun Content(articles: List<Article>) {
        if (articles.isNotEmpty()) {
            LazyColumn {
                articles.forEach { article ->
                    item {
                        ArticleCard(article)
                        Spacer(
                            modifier = Modifier
                                .height(height = mediumSpacing),
                        )
                    }
                }
            }
        } else {
            // TODO Show empty article state
        }
    }

    @Composable
    private fun ArticleCard(article: Article) {
        article.urlToImage?.let { image ->
            AsyncImage(
                model = image,
                contentDescription = "Article Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(mediumSpacing)
                    .clip(RoundedCornerShape(16.dp)),
                onError = {
                    Log.e(
                        "ArticleScreen",
                        "Error loading image",
                        it.result.throwable,
                    )
                },
            )
        }
        Text(
            modifier = Modifier.padding(horizontal = mediumSpacing),
            text = article.title ?: "No Title Found",
            style = MaterialTheme.typography.headlineLarge,
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.padding(horizontal = mediumSpacing, vertical = mediumSpacing),
                text = article.author ?: "No Author Name Found",
                style = MaterialTheme.typography.titleLarge,
            )

            FloatingActionButton(
                modifier = Modifier
                    .padding(mediumSpacing)
                    .size(45.dp),
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = mediumSpacing, vertical = mediumSpacing),
            text = article.description ?: "No Description Found",
            style = MaterialTheme.typography.bodyLarge,
        )
        Divider(modifier = Modifier.padding(horizontal = mediumSpacing))
    }
}
