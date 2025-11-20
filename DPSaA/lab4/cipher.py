from deque import Deque

ALPHABET = (
    "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    "abcdefghijklmnopqrstuvwxyz"
    "0123456789"
    " !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
)

def build_deque(alphabet: str) -> Deque:
    dq = Deque()
    for ch in alphabet:
        dq.push_back(ch)
    return dq

def encrypt_char(ch: str, dq: Deque) -> str:
    n = len(dq)
    pos = -1
    for i in range(n):
        if dq[i] == ch:
            pos = i
            break

    if pos == -1:
        return ch

    new_index = (pos - 2) % n
    return dq[new_index]

def encrypt_text(text:str, dq: Deque) -> str:
    result_chars = []
    for ch in text:
        result_chars.append(encrypt_char(ch, dq))
    return "".join(result_chars)