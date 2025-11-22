set -euo pipefail

E_NAN=65
E_OP=67
E_DIV0=66

read -rp "Enter first number: "  a
read -rp "enter second number: " b
read -rp "operation (+ - * /): " op

a=${a//,/.}
b=${b//,/.}

num_re='^-?[0-9]+([.][0-9]+)?$'

if [[ ! $a =~ $num_re || ! $b =~ $num_re ]]; then
  echo "mistake: enter number (whole or decimal .)" >&2
  exit $E_NAN
fi

case "$op" in
  +)  awk -v a="$a" -v b="$b" 'BEGIN{print a+b}' ;;
  -)  awk -v a="$a" -v b="$b" 'BEGIN{print a-b}' ;;
  '*') awk -v a="$a" -v b="$b" 'BEGIN{print a*b}' ;;
  /)
      if awk -v b="$b" 'BEGIN{exit (b==0)}'; then
        awk -v a="$a" -v b="$b" 'BEGIN{print a/b}'
      else
        echo "mistake: devision by zero" >&2
        exit $E_DIV0
      fi
      ;;
  *)
      echo "unknown operation: $op" >&2
      exit $E_OP ;;
esac