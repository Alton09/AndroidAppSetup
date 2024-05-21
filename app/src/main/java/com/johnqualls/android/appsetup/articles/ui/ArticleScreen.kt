package com.johnqualls.android.appsetup.articles.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.johnqualls.android.appsetup.core.ui.Presenter
import com.johnqualls.android.appsetup.core.ui.Screen
import com.johnqualls.android.appsetup.core.ui.theme.smallSpacing

@Preview
@Composable
private fun ArticleScreenLoadingPreview() {
    ArticleScreen(ArticlePresenterFake()).Render(state = ArticleUiState(isLoading = true))
}

@Preview
@Composable
private fun ArticleScreenContentPreview() {
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
            ),
        ),
    )
}

class ArticleScreen(override val presenter: Presenter<ArticleUiEvent, ArticleUiState>) :
    Screen<ArticleUiEvent, ArticleUiState> {
    @Composable
    override fun Render(state: ArticleUiState) {
        if (state.isLoading) {
            LoadingIndicator()
        } else {
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
                    }
                }
            }
        } else {
            // TODO Show empty article state
        }
    }

    @Composable
    private fun ArticleCard(article: Article) {
        Card {
            Text(
                modifier = Modifier.padding(smallSpacing),
                text = article.title,
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                modifier = Modifier.padding(smallSpacing),
                text = article.author,
                style = MaterialTheme.typography.titleLarge,
            )
            Text(
                modifier = Modifier.padding(smallSpacing),
                text = article.description,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}
