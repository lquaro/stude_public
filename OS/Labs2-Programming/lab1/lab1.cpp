#include <limits.h>
#include <pwd.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/utsname.h>
#include <time.h>
#include <unistd.h>

static void die(const char *m) { perror(m); exit(EXIT_FAILURE); }
static long lget(int n) { long v = sysconf(n); if (v == -1) die("sysconf"); return v; }

static void print_match(const char *path, const char *key) {
    FILE *f = fopen(path, "r"); if (!f) { perror(path); return; }
    char *line = NULL; size_t cap = 0;
    while (getline(&line, &cap, f) != -1) {
        if (!key || strstr(line, key)) { line[strcspn(line, "\n")] = '\0'; puts(line); break; }
    }
    free(line); fclose(f);
}

int main() {
    puts("\nИмя хоста и пользователя");
    char host[256]; if (gethostname(host, sizeof host)) die("gethostname");
    uid_t uid = geteuid(); struct passwd *pw = getpwuid(uid);
    printf("hostname: %s\nuser:     %s (uid %u)\n",
           host, (pw && pw->pw_name) ? pw->pw_name : "<unknown>", (unsigned)uid);

    puts("\nВерсия ОС и архитектура");
    struct utsname u; if (uname(&u)) die("uname");
    printf("Система:  %s\nВерсия:  %s\nАрхитектура:  %s\n", u.sysname, u.release, u.machine);

    puts("\nСистемные метрики");
    long pagesize = lget(_SC_PAGESIZE), nproc = lget(_SC_NPROCESSORS_ONLN), physpg = lget(_SC_PHYS_PAGES);
    printf("page size:   %ld\nprocessors:  %ld\ntotal memory: %.2f GiB\n",
           pagesize, nproc, (double)physpg * (double)pagesize / (1024.0 * 1024.0 * 1024.0));

    puts("\nВремя");
    time_t now = time(NULL); if (now == (time_t)-1) die("time");
    struct tm tl, tu;
    char buf[64];
    localtime_r(&now, &tl); strftime(buf, sizeof buf, "%Y-%m-%d %H:%M:%S %Z", &tl); printf("%s\n", buf);
    gmtime_r(&now,  &tu);  strftime(buf, sizeof buf, "%Y-%m-%d %H:%M:%S UTC", &tu); printf("%s\n", buf);

    puts("\nДополнительные API");
    char cwd[PATH_MAX]; if (!getcwd(cwd, sizeof cwd)) die("getcwd");
    printf("pid %d, ppid %d\ncwd: %s\n", getpid(), getppid(), cwd);

    printf("/proc/meminfo: "); { FILE *f = fopen("/proc/meminfo", "r"); long kb = 0;
        if (f) { fscanf(f, "MemTotal: %ld kB", &kb); fclose(f); }
        printf("MemTotal: %.2f GiB\n", kb / 1048576.0); }

    printf("/proc/loadavg: "); print_match("/proc/loadavg", NULL);
    return 0;
}