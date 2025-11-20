import random

def generate_array(n, lower=0, upper=None):
    if upper is None:
        upper = n
    return [random.randint(lower, upper) for _ in range(n)]

def py_sort(arr):
    arr.sort()
    return arr

def fibonacci_search(arr, key):
    n = len(arr)

    f2 = 0
    f1 = 1
    fM = f1 + f2

    while fM < n:
        f2 = f1
        f1 = fM
        fM = f1 + f2

    offset = -1

    while fM > 1:
        i = min(offset + f2, n - 1)

        if arr[i] < key:
            offset = i
            fM = f1
            f1 = f2
            f2 = fM - f1
        elif arr[i] > key:
            fM = f2
            f1 = f1 - f2
            f2 = fM - f1
        else:
            return i
    if f1 and offset + 1 < n and arr[offset + 1] == key:
        return offset + 1

    return -1

def py_search(arr, key):
    try:
        return arr.index(key)
    except ValueError:
        return -1