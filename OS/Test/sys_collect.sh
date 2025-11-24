set -euo pipefail
ts=$(date '+%F %T')
log="$HOME/syslogs/${ts}_syslog"
{
  echo "mpstat ALL @ $ts"
  mpstat -P ALL 1 1
  echo
  echo "pidstat (cpu) @ $ts"
  pidstat -u -P ALL 1 1
} > "$log" 2>&1