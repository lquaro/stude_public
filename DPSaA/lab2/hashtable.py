def create_table(size):
    return [[] for _ in range(size)]

def hash_func(key, size):
    return key % size

def build_table(arr):
    size = len(arr)
    table = create_table(size)
    for value in arr:
        ht_insert(table, value)
    return table

def ht_search(table, key):
    index = hash_func(key, len(table))
    bucket = table[index]
    return key in bucket

def ht_insert(table, key):
    index = hash_func(key, len(table))
    bucket = table[index]
    if key not in bucket:
        bucket.append(key)

def ht_dlt(table, key):
    index = hash_func(key, len(table))
    bucket = table[index]
    if key in bucket:
        bucket.remove(key)
        return True
    return False