set -u

safe-run() {
  if command -v "$1" >/dev/null 2>&1; then
    shift; "$@"
  else
    echo "Command denied: $1"
  fi
}

show_menu() {
cat <<'MENU'
1) username ($USER)
2) Home catalog ($HOME)
3) current dir ($PWD)
4) PID proccess ($$)
5) Script name ($0)
6) Number args n list ($#, $@)
0) exit
MENU
}

while :; do
  show_menu
  read -rp "Select: " ch
  echo " - - "
  case "$ch" in
    1) echo "$USER" ;;
    2) echo "$HOME" ;;
    3) echo "$PWD" ;;
    4) echo "$$" ;;
    5) echo "$0" ;;
    6) echo "argc: $#" ; printf 'argv: %s\n' "$@" ;;
    0) echo "exit"; exit 0 ;;
    *) echo "there is no item" ;;
  esac
done