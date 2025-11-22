set -euo pipefail

E_ARGC=64
E_NOENT=2
E_EMPTY=68

if (( $# != 1 )); then
  echo "usage: $0 <dir>" >&2
  exit $E_ARGC
fi

dir=$1
if [[ ! -d $dir ]]; then
  echo "mistake: dir '$dir' doesnt exist" >&2
  exit $E_NOENT
fi

total=0
count=0
while IFS= read -r -d '' f; do
  size=$(stat -c '%s' -- "$f")
  (( total += size, count += 1 ))
done < <(find "$dir" -maxdepth 1 -type f -print0)

if (( count == 0 )); then
  echo "there are no files to count." >&2
  exit $E_EMPTY
fi

echo $(( total / count ))