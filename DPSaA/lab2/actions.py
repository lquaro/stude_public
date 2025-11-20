import time
from core import *

def measure_search(search_func, arr, key, name):
    start = time.perf_counter()
    idx = search_func(arr, key)
    end = time.perf_counter()

    elapsed_us = int((end - start) * 1_000_000)
    print(f"{name}: {idx}, {elapsed_us:} msc")
    return idx

def both_searches(arr, key):
    idx_fib = measure_search(fibonacci_search, arr, key, "Fibonacci search")
    idx_py = measure_search(py_search, arr, key, "Python search")
    return idx_fib, idx_py