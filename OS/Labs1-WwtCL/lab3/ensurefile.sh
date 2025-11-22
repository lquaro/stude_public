set -euo pipefail
E_ARGC=64
E_IO=74

if (( $# != 1 )); then
  echo "Mistake: provide a path to file" >&2
  exit $E_ARGC
fi

path=$1

if [[ -d $path ]]; then
  echo "This is a dir, not a file: $path" >&2
  exit 1
fi

if [[ -e $path ]]; then
  echo "File exists: $path"
  exit 0
fi

printf "File not found. Create it? [y/N]: "
read -r ans
case ${ans,,} in
  y|yes)
    dir=$(dirname -- "$path")
    mkdir -p -- "$dir"
    : > "$path" || { echo "Create failed: $path" >&2; exit $E_IO; }
    echo "Created: $path"
    ;;
  *)
    echo "Not created"
    ;;
esac