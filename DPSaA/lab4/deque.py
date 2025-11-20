class Deque:
    def __init__(self):
        self._data = []

    def empty(self) -> bool:
        return len(self._data) == 0

    def pop_front(self):
        if self.empty():
            raise IndexError("Deque is empty")
        return self._data.pop(0)

    def pop_back(self):
        if self.empty():
            raise IndexError("Deque is empty")
        return self._data.pop()

    def push_front(self, value):
        self._data.insert(0, value)

    def push_back(self, value):
        self._data.append(value)

    def __len__(self):
        return len(self._data)

    def __getitem__(self, index: int):
        return self._data[index]