program SqrtApprox;
var
  n, x, step: real;
begin
  readln(n);
  if n < 0 then
    writeln('Error: n < 0')
  else
  begin
    x := 0.0;
    step := 1.0;
    while (x + step) * (x + step) <= n do
      x := x + step;

    step := 0.1;
    while (x + step) * (x + step) <= n do
      x := x + step;

    step := 0.01;
    while (x + step) * (x + step) <= n do
      x := x + step;

    step := 0.001;
    while (x + step) * (x + step) <= n do
      x := x + step;

    step := 0.0001;
    while (x + step) * (x + step) <= n do
      x := x + step;

    writeln(x:0:4);
  end;
end.