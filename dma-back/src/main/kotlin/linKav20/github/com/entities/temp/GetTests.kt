package linKav20.github.com.entities.temp

import linKav20.github.com.entities.answer.models.AnswerModel
import linKav20.github.com.entities.category.models.CategoryModel
import linKav20.github.com.entities.question.models.QuestionModel
import linKav20.github.com.entities.test.TestState
import linKav20.github.com.entities.test.models.TestModel
import linKav20.github.com.entities.user.models.UserModel


val answer1 = AnswerModel(1, "pink", 5)
val answer2 = AnswerModel(2, "blue", 4)
val answer3 = AnswerModel(3, "orange", 3)
val answer4 = AnswerModel(4, "green", 2)
val answer5 = AnswerModel(5, "black", 1)

val answer6 = AnswerModel(1, "water", 5)
val answer7 = AnswerModel(2, "tea", 4)
val answer8 = AnswerModel(3, "milk", 3)
val answer9 = AnswerModel(4, "coffee", 2)
val answer10 = AnswerModel(5, "mineral", 1)

val answer11 = AnswerModel(6, "donat", 5)
val answer12 = AnswerModel(7, "salad", 4)
val answer13 = AnswerModel(8, "egg", 3)
val answer14 = AnswerModel(9, "chocolate", 2)
val answer15 = AnswerModel(10, "cake", 1)

val question1 = QuestionModel(1, "What is your fav color?", listOf(answer1, answer3, answer5))
val question2 = QuestionModel(2, "What is most popular color?", listOf(answer2, answer5, answer1))
val question3 = QuestionModel(3, "Which color is cooler?", listOf(answer4, answer3, answer2))
val question4 = QuestionModel(4, "Who is not in rainbow?", listOf(answer1, answer2, answer3, answer4))
val question5 = QuestionModel(5, "What color is darker?", listOf(answer4, answer5))

val question6 = QuestionModel(6, "What is your fav drink?", listOf(answer6, answer8, answer10))
val question7 = QuestionModel(7, "What is most popular drink?", listOf(answer7, answer10, answer6))
val question8 = QuestionModel(8, "Which drink is cooler?", listOf(answer9, answer8, answer7))
val question9 = QuestionModel(9, "Who is not in shop?", listOf(answer6, answer7, answer8, answer9))
val question10 = QuestionModel(10, "What drink is healthier?", listOf(answer9, answer10))

val question11 = QuestionModel(11, "What is your fav food?", listOf(answer11, answer13, answer15))
val question12 = QuestionModel(12, "What is most popular food?", listOf(answer12, answer15, answer11))
val question13 = QuestionModel(13, "Which food is cooler?", listOf(answer14, answer13, answer12))
val question14 = QuestionModel(14, "Who is not in restraint?", listOf(answer11, answer12, answer13, answer14))
val question15 = QuestionModel(15, "What food is more junk?", listOf(answer14, answer15))

val category1 = CategoryModel(1, "Colors", null, listOf(question1, question2, question3, question4, question5))
val category2 = CategoryModel(2, "Food", null, listOf(question6, question7, question8, question9, question10))
val category3 = CategoryModel(3, "Drink", null, listOf(question11, question12, question13, question14, question15))
val category4 = CategoryModel(4, "Food", listOf(category2, category3), null)

val user = UserModel("Lina", "lina")
val user2 = UserModel("Kosta", "kosta")

fun getTest1(): TestModel {
    val test = TestModel(
        1,
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
    val question = QuestionModel(16, "What?", listOf(AnswerModel(16, "Ans", 5), AnswerModel(17, "Ans", 5)))
    val category = CategoryModel(
        5,
        "Category1",
        null,
        listOf(question, question)
    )

    val test = TestModel(
        2,
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