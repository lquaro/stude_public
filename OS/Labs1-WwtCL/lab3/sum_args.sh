set -euo pipefail

E_ARGC=64
E_NAN=65

if (( $# < 2 || $# > 5 )); then
  echo "Mistake: need 2..5 args, recieved: $#" >&2
  exit $E_ARGC
fi

sum=0
for a in "$@"; do
  if [[ $a =~ ^-?[0-9]+$ ]]; then
    (( sum += a ))
  else
    echo "Mistake: arg '$a' isnt an integer" >&2
    exit $E_NAN
  fi
done

echo "$sum"