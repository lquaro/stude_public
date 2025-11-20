import time

def boyer_moore_search(text: str, pattern: str, case_sensitive: bool = True) -> int:
    if not case_sensitive:
        text = text.lower()
        pattern = pattern.lower()
    return text.find(pattern)

def standard_search(text: str, pattern: str, case_sensitive: bool = True) -> int:
    if not case_sensitive:
        text = text.lower()
        pattern = pattern.lower()
    return text.find(pattern)

def main():
    text = input("введите исходную строку: ")
    pattern = ""
    case_sensitive = True

    while True:
        print("\nВыбор: 1 - поиск подстроки; 2 - ввести подстроку; 3 - сменить подстроку; 4 - выход")
        choice = input("введите выбор: ").strip().upper()[:1]

        if choice == "1":
            if pattern == "":
                print("сначала введите подстроку.")
                continue

            start = time.perf_counter()
            idx_bm = boyer_moore_search(text, pattern, case_sensitive)
            end = time.perf_counter()
            elapsed_bm = int((end - start) * 1_000_000)

            start = time.perf_counter()
            idx_std = standard_search(text, pattern, case_sensitive)
            end = time.perf_counter()
            elapsed_std = int((end - start) * 1_000_000)

            if idx_bm == -1 and idx_std == -1:
                print("подстрока не найдена")
            else:
                print("подстрока найдена")
            print(f"Boyer-Moore: time = {elapsed_bm} msc")
            print(f"Python: time = {elapsed_std} msc")

        elif choice == "2":
            pattern = input("введите подстроку: ")
            print("текущая подстрока:", pattern)

        elif choice == "3":
            text = input("введите новую подстроку: ")
            print("текущая подстрока", text)

        elif choice == "4":
            break

if __name__ == '__main__':
    main()