package linKav20.github.com.entities.question.models

import linKav20.github.com.entities.answer.models.AnswerModel

data class QuestionModel(val id:Int,val question:String, val answers:List<AnswerModel>)