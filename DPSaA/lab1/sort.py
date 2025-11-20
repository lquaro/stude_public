import random
import time

def generate_array(n, lower=0, upper=10**3):
    return [random.randint(lower, upper) for _ in range(n)]

def exchange_sort(arr):
    n = len(arr)
    for i in range(n - 1):
        for j in range(0, n - 1):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
    return arr

def py_sort(arr):
    arr.sort()
    return arr

def quick_sort(arr):
    if len(arr) <= 1:
        return arr

    pivot = arr[len(arr) // 2]
    less = [x for x in arr if x < pivot]
    equal = [x for x in arr if x == pivot]
    greater = [x for x in arr if x > pivot]

    return quick_sort(less) + equal + quick_sort(greater)

def quick_sort_wrapper(arr):
    result = quick_sort(arr)
    return result

def measure_sort(sort_func, data, name):
    arr = data.copy()
    start = time.perf_counter()
    result = sort_func(arr)
    end = time.perf_counter()

    elapsed_ms = (end - start) * 1000

    if result is None:
        sorted_arr = arr
    else:
        sorted_arr = result

    print(f"{name}: отсортировано {len(sorted_arr)} элементов за {elapsed_ms:.3f} ms")

def main():
    sizes = [10000]

    for n in sizes:
        print(f"\nРазмер массива: {n}")
        data = generate_array(n)

        measure_sort(exchange_sort, data, "exchange_sort")
        measure_sort(py_sort, data, "py_sort")
        measure_sort(quick_sort_wrapper, data, "quick_sort")

if __name__ == "__main__":
    main()