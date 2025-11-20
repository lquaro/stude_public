from cipher import *

def main():
    dq = build_deque(ALPHABET)

    with open("input.txt", "r", encoding="utf-8") as f:
        text = f.read()

    encrypted = encrypt_text(text, dq)

    with open("output.txt", "w", encoding="utf-8") as f:
        f.write(encrypted)

    print("исходный текст: ")
    print(text)
    print("\nзашифрованный текст:")
    print(encrypted)

if __name__ == "__main__":
    main()