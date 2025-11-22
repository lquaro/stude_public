set -euo pipefail

E_ARGC=64
E_NAN=65
E_RANGE=66

if (( $# != 1 )); then
  echo "Mistake: enter exactly one number N" >&2
  exit $E_ARGC
fi

N=$1

if [[ ! $N =~ ^[0-9]+$ ]]; then
  echo "Mistake: N must be a non-negative integer" >&2
  exit $E_NAN
fi

if (( N < 1 )); then
  echo "Mistake: N must be >=1" >&2
  exit $E_RANGE
fi

for (( i=1; i<=N; i+=2 )); do
  echo "$i"
done