groupmates = [
    {
        "name": "Илья",
        "surname": "Автономов",
        "exams": ["ТВИМС", "УИТП", "Пв1С"],
        "marks": [3, 5, 3]
    },
    {
        "name": "Дмитрий",
        "surname": "Акользин",
        "exams": ["ПИС", "ОС", "КТП"],
        "marks": [4, 3, 4]
    },
    {
        "name": "Данила",
        "surname": "Алюлин",
        "exams": ["АИС", "ТВиМС", "СИИ"],
        "marks": [5, 3, 5]
    }
]

def filter_students(students_list, min_average):

    filtered_students = []
    for student in students_list:
        if student["marks"]:
            average_grade = sum(student["marks"]) / len(student["marks"])
            if average_grade > min_average:
                filtered_students.append(student)
    return filtered_students

min_score = 4.0
top_students = filter_students(groupmates, min_score)

print(f"Список студентов со средним баллом выше {min_score}:")
if top_students:
    for student in top_students:
        average = sum(student["marks"]) / len(student["marks"])
        print(f"- {student["name"]} {student["surname"]} (средний балл: {average:.2f})")
else:
    print("Таких студентов нет.")