class Quiz
{
    constructor(type, questions, results)
    {
        //Тип теста: 1 - классический тест с правильными ответами, 2 - тест без правильных ответов
        this.type = type;

        //Массив с вопросами
        this.questions = questions;

        //Массив с возможными результатами
        this.results = results;

        //Количество набранных очков
        this.score = 0;

        //Номер результата из массива
        this.result = 0;

        //Номер текущего вопроса
        this.current = 0;
    }

    Click(index)
    {
        //Добавляем очки
        let value = this.questions[this.current].Click(index);
        this.score += value;

        let correct = -1;

        //Если было добавлено хотя бы одно очко, то считаем, что ответ верный
        if(value >= 1)
        {
            correct = index;
        }
        else
        {
            //Иначе ищем, какой ответ может быть правильным
            for(let i = 0; i < this.questions[this.current].answers.length; i++)
            {
                if(this.questions[this.current].answers[i].value >= 1)
                {
                    correct = i;
                    break;
                }
            }
        }

        this.Next();

        return correct;
    }

    //Переход к следующему вопросу
    Next()
    {
        this.current++;

        if(this.current >= this.questions.length)
        {
            this.End();
        }
    }

    //Если вопросы кончились, этот метод проверит, какой результат получил пользователь
    End()
    {
        for(let i = 0; i < this.results.length; i++)
        {
            if(this.results[i].Check(this.score))
            {
                this.result = i;
            }
        }
    }
}

//Класс, представляющий вопрос
class Question
{
    constructor(text, answers)
    {
        this.text = text;
        this.answers = answers;
    }

    Click(index)
    {
        return this.answers[index].value;
    }
}

//Класс, представляющий ответ
class Answer
{
    constructor(text, value)
    {
        this.text = text;
        this.value = value;
    }
}

//Класс, представляющий результат
class Result
{
    constructor(text, value)
    {
        this.text = text;
        this.value = value;
    }

    //Этот метод проверяет, достаточно ли очков набрал пользователь
    Check(value)
    {
        if(this.value <= value)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}