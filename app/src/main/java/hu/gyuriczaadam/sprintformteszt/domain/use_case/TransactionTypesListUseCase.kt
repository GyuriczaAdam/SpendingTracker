package hu.gyuriczaadam.sprintformteszt.domain.use_case

import hu.gyuriczaadam.sprintformteszt.domain.repositories.TransactionRepository
import hu.gyuriczaadam.sprintformteszt.util.Constants
import javax.inject.Inject

class TransactionTypesListUseCase
     {
    operator fun invoke():List<String>{
        val transactionTypes:List<String> = mutableListOf(
            Constants.FOOD_TYPE,
            Constants.HOUSING_TYPE,
            Constants.TRAVEL_TYPE,
            Constants.UTILITIES_TYPE,
            Constants.INSURANCE_TYPE,
            Constants.HEALTHCARE_TYPE,
            Constants.FINANCIAL_TYPE,
            Constants.LIFESTYLE_TYPE,
            Constants.ENTERTAINMENT_TYPE,
            Constants.MISCELLANEOUS_TYPE)
        return transactionTypes
    }
}