package linKav20.github.com.entities.temp

import linKav20.github.com.entities.answer.models.AnswerModel
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.test.TestState
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.user.models.UserModel


val answer1 = AnswerModel("pink", 5)
val answer2 = AnswerModel("blue", 4)
val answer3 = AnswerModel("orange", 3)
val answer4 = AnswerModel("green", 2)
val answer5 = AnswerModel("black", 1)

val answer6 = AnswerModel("water", 5)
val answer7 = AnswerModel("tea", 4)
val answer8 = AnswerModel("milk", 3)
val answer9 = AnswerModel("coffee", 2)
val answer10 = AnswerModel("mineral", 1)

val answer11 = AnswerModel("donat", 5)
val answer12 = AnswerModel("salad", 4)
val answer13 = AnswerModel("egg", 3)
val answer14 = AnswerModel("chocolate", 2)
val answer15 = AnswerModel("cake", 1)

val question1 = QuestionModel("What is your fav color?", listOf(answer1, answer3, answer5))
val question2 = QuestionModel("What is most popular color?", listOf(answer2, answer5, answer1))
val question3 = QuestionModel("Which color is cooler?", listOf(answer4, answer3, answer2))
val question4 = QuestionModel("Who is not in rainbow?", listOf(answer1, answer2, answer3, answer4))
val question5 = QuestionModel("What color is darker?", listOf(answer4, answer5))

val question6 = QuestionModel("What is your fav drink?", listOf(answer6, answer8, answer10))
val question7 = QuestionModel("What is most popular drink?", listOf(answer7, answer10, answer6))
val question8 = QuestionModel("Which drink is cooler?", listOf(answer9, answer8, answer7))
val question9 = QuestionModel("Who is not in shop?", listOf(answer6, answer7, answer8, answer9))
val question10 = QuestionModel("What drink is healthier?", listOf(answer9, answer10))

val question11 = QuestionModel("What is your fav food?", listOf(answer11, answer13, answer15))
val question12 = QuestionModel("What is most popular food?", listOf(answer12, answer15, answer11))
val question13 = QuestionModel("Which food is cooler?", listOf(answer14, answer13, answer12))
val question14 = QuestionModel("Who is not in restraint?", listOf(answer11, answer12, answer13, answer14))
val question15 = QuestionModel("What food is more junk?", listOf(answer14, answer15))

val category1 = CategoryModel(1, "Colors", null, listOf(question1, question2, question3, question4, question5))
val category2 = CategoryModel(2, "Food", null, listOf(question6, question7, question8, question9, question10))
val category3 = CategoryModel(3, "Drink", null, listOf(question11, question12, question13, question14, question15))
val category4 = CategoryModel(4, "Food", listOf(category2, category3), null)

val user = UserModel("Lina", "lina")
val user2 = UserModel("Kosta", "kosta")

fun getTest1(): TestModel {
    val test = TestModel(
        "MyTest",
        "First try",
        user,
        user,
        listOf(user, user),
        listOf(category1, category4),
        "01.02.2022",
        "01.02.2022",
        user,
        TestState.APPROVED,
        listOf("user1@mail.ru", "user1@mail.ru", "user1@mail.ru", "user1@mail.ru")
    )

    return test
}

fun getTest2(): TestModel {
    val question = QuestionModel("What?", listOf(AnswerModel("Ans", 5), AnswerModel("Ans", 5)))
    val category = CategoryModel(
        5,
        "Category1",
        null,
        listOf(question, question)
    )

    val test = TestModel(
        "MyTest",
        "First try",
        user,
        user,
        listOf(user, user),
        listOf(category1, category4),
        "01.02.2022",
        "01.02.2022",
        user,
        TestState.CLOSED,
        listOf("user1@mail.ru", "user1@mail.ru", "user1@mail.ru", "user1@mail.ru")
    )

    return test
}