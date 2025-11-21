from core import *
from actions import *
from hashtable import *

def main():
    n = 50000
    arr = generate_array(n)
    py_sort(arr)
    table = build_table(arr)

    while True:
        print("\nВыбор: 1 - поиск; 2 - добавление; 3 - удалить; 4 - выход")
        choice = input("введите выбор: ")

        if choice == "1":
            key = int(input("число для поиска: "))
            idx_fib, idx_py = both_searches(arr, key)
            if idx_fib == -1 and idx_py == -1:
                print(f"{key} не найден")
            else:
                print(f"{key} найден")
                print(f"idx Fibonacci: {idx_fib if idx_fib != -1 else 'не нашел'}")
                print(f"idx Python: {idx_py if idx_py != -1 else 'не нашел'}")

        elif choice == "2":
            value = int(input("число для добавления: "))
            arr.append(value)
            py_sort(arr)
            ht_insert(table, value)
            print(f"{value} добавлен")

        elif choice == "3":
            value = int(input("число для удаления: "))
            idx_fib = fibonacci_search(arr, value)
            if idx_fib == -1:
                print(f"{value} не найден")
            else:
                arr.pop(idx_fib)
                ht_dlt(table, value)
                print(f"{value} удален")

        elif choice == "4":
            print("завершение работы")
            break
        else:
            print("неверный выбор")

if __name__ == "__main__":
    main()