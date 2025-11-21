import turtle
import time

calls = 0

screen = turtle.Screen()
screen.title("Sierpinski arrowhead curve")

t = turtle.Turtle()
t.speed(0)
t.hideturtle()
t.penup()
t.goto(-200, 0)
t.pendown()

def sierpinski(depth, length, sign):
    global calls
    calls += 1

    if depth == 0:
        t.forward(length)
    else:
        sierpinski(depth -1, length / 2, sign)
        t.left(60 * sign)
        sierpinski(depth - 1, length / 2, sign)
        t.right(60 * sign)
        sierpinski(depth - 1, length / 2, sign)

def main():
    global calls
    depth = 7
    length = 400

    calls = 0
    start = time.perf_counter()
    sierpinski(depth, length, 1)
    end = time.perf_counter()

    elapsed_ms = (end - start) * 1000
    print(f"depth: {depth}, time: {elapsed_ms:.0f} msc, calls: {calls}")

if __name__ == "__main__":
    main()
    screen.mainloop()