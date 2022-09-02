package hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import hu.gyuriczaadam.sprintformteszt.presentation.common.LocalSpacing
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen.components.CategoryChartComponent
import hu.gyuriczaadam.sprintformteszt.presentation.transaction_charts_screen.components.TransactionMaxValuesHeader

@Composable
fun TransactionChartScreen(
    navController: NavHostController,
    viewModel:TransactionChartsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val localSpacing = LocalSpacing.current
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = localSpacing.spaceMedium)
        ) {
            item {
                TransactionMaxValuesHeader(
                    moneySpentSum = state.sumOfTransactions,
                    maxTransactionSum = state.maxTransactionSum,
                    maxTransactionName = state.maxTransactionName
                )
                state.transactionsSumValueByCategory.forEach { category->
                    Spacer(modifier = Modifier.height(localSpacing.spaceMedium))
                    CategoryChartComponent(
                        transactionAmount = category.sumOfCategoryAmount,
                        maxAmount = state.sumOfTransactions,
                        categoryName = category.categoryName
                    )
                }
                }
            }
        }
    }
